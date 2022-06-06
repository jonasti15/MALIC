package RabbitMQ;

import com.rabbitmq.client.*;
import controladores.ControladorAlertas;
import elementos.Alerta;
import elementos.Animal;
import elementos.TipoEstado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeoutException;

public class HiloConsumidor extends Thread {
    ConnectionFactory factory;
    ControllerJSON controllerJSON;
    ControladorAlertas controladorAlertas;
    private static final String QUEUE_DATOS = "colaAlm";
    private final static String EXCHANGE_DATOS = "alarma";
    final int MAX_INTENTOS = 3;
    final static String DLX_NAME = "deadLetter";
    final static String DLX_QUEUE = "dlx_alarma";

    public HiloConsumidor(ControladorAlertas controladorAlertas) {
        this.controladorAlertas = controladorAlertas;
        factory = new ConnectionFactory();
        controllerJSON = new ControllerJSON();
        factory.setHost("musker.duckdns.org");
        factory.setPort(5672);
        factory.setUsername("super");
        factory.setPassword("jj7jzYJ9");
    }

    @Override
    public void run() {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_DATOS, "fanout");

            Map<String, Object> arguments = new HashMap<>();
            arguments.put("x-dead-letter-exchange", DLX_NAME);
            channel.queueDeclare(QUEUE_DATOS, false, false, false, arguments);
            channel.queueBind(QUEUE_DATOS, EXCHANGE_DATOS, "");
            channel.basicQos(5);

            MiConsumer consumer = new MiConsumer(channel);
            boolean autoack = false;
            channel.basicConsume(QUEUE_DATOS, autoack, consumer);
            System.out.println(" Waiting for messages. To exit press return");
            synchronized (this) {
                wait();
            }
            System.out.println("Conexion con RabbitMQ cerrada");
            channel.close();
            connection.close();

        } catch (IOException | TimeoutException e) {

            e.printStackTrace();

            if (channel != null) {
                try {
                    channel.close();
                    connection.close();
                } catch (IOException | TimeoutException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public class MiConsumer extends DefaultConsumer {
        Channel channel;
        ConcurrentMap<String, Integer> contadores;
        boolean mensajeCorrecto = true;

        public MiConsumer(Channel channel) {
            super(channel);
            this.channel = channel;
            this.contadores = new ConcurrentHashMap<>();
        }

        public void handleDelivery(String consumerTag, Envelope envelope,
                                   AMQP.BasicProperties properties, byte[] body) throws IOException {
            String message = new String(body, StandardCharsets.UTF_8);
            Alerta alerta;


            if (mensajeCorrecto) {
                //Ba bien
                alerta = controllerJSON.generateAlerta(message, controladorAlertas);
                controladorAlertas.alertaHandler(alerta);
                System.out.println("Alerta: " + message);
                boolean multipleBien = false;
                this.getChannel().basicAck(envelope.getDeliveryTag(), multipleBien);
            } else {
                //Ba mal
                Integer intentos = contadores.get(message);
                if (intentos == null) intentos = 1;
                System.out.println("ERROR: No se ha podido procesar " + message + " intentos: " + intentos);

                if (intentos == MAX_INTENTOS) {//Si ha suoerado el numero de intentos
                    boolean reprocesar = false;
                    boolean multiple = false;
                    contadores.remove(message);
                    //this.getChannel().basicNack(envelope.getDeliveryTag(), multiple, reprocesar);
                    this.getChannel().basicReject(envelope.getDeliveryTag(), reprocesar);
                } else {//Si no ha suoerado el numero de intentos
                    boolean reprocesar = true;
                    boolean multiple = false;
                    contadores.put(message, ++intentos);
                    this.getChannel().basicNack(envelope.getDeliveryTag(), multiple, reprocesar);
                }

            }


        }
    }
}

