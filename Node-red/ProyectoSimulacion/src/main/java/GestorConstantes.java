import com.rabbitmq.client.*;

import javax.management.monitor.Monitor;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class GestorConstantes {
    private static final String QUEUE_DATOS = "colaDatos";
    private final static String EXCHANGE_DATOS = "simuladorAnimales";

    List<Constantes> constantesList;
    ConnectionFactory factory;
    ControllerJSON controllerJSON;

    public GestorConstantes() {
        constantesList = new ArrayList<>();
        factory = new ConnectionFactory();
        controllerJSON = new ControllerJSON();
        factory.setHost("localhost");
    }

    public void suscribe() {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
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
            Constantes c = controllerJSON.generateConstantes(message);
            constantesList.add(c);
            System.out.println("Lista de animales");
            for (Constantes cs : constantesList){
                System.out.println(cs);
            }
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestorConstantes suscriptor = new GestorConstantes();
        Thread hilo = new Thread(() -> {
            teclado.nextLine();
            synchronized (suscriptor) {
                suscriptor.notify();
            }
        });
        hilo.start();
        suscriptor.suscribe();
    }
}
