package RabbitMQ;

import com.rabbitmq.client.*;
import controladores.ControladorAlertas;
import elementos.Alerta;
import elementos.Animal;
import elementos.TipoEstado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class HiloConsumidor extends Thread{
    ConnectionFactory factory;
    ControllerJSON controllerJSON;
    ControladorAlertas controladorAlertas;
    private static final String QUEUE_DATOS = "colaAlm";
    private final static String EXCHANGE_DATOS = "alarma";
    public HiloConsumidor(ControladorAlertas controladorAlertas){
        this.controladorAlertas=controladorAlertas;
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
                factory.setHost("musker.duckdns.org:5672");
                factory.setUsername("super");
                factory.setPassword("jj7jzYJ9");
                channel = connection.createChannel();
                channel.exchangeDeclare(EXCHANGE_DATOS, "direct", true);
                channel.queueDeclare(QUEUE_DATOS, true, false, false, null);
                channel.queueBind(QUEUE_DATOS, EXCHANGE_DATOS, "");

                MiConsumer consumer = new MiConsumer(channel);
                boolean autoack = true;
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

            public MiConsumer(Channel channel) {
                super(channel);
                this.channel = channel;
            }

            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {

                 String message = new String(body, StandardCharsets.UTF_8);
                Alerta alerta=controllerJSON.generateAlerta(message, controladorAlertas);

                controladorAlertas.alertaHandler(alerta);
                System.out.println("Alerta: "+ message);
            }
        }
}

