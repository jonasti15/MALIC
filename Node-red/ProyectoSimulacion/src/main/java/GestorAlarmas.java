import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class GestorAlarmas {
    private static final String QUEUE_DATOS = "colaAlm";
    private final static String EXCHANGE_DATOS = "alarma";

    ConnectionFactory factory;
    ControllerJSON controllerJSON;

    public GestorAlarmas() {
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

            System.out.println("recibido: " + message);

        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestorAlarmas suscriptor = new GestorAlarmas();

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
