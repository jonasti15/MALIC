import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Monitor {
    private static final String QUEUE_DATOS = "colaDatos";
    private final static String EXCHANGE_DATOS = "simuladorAnimales";

    ConnectionFactory factory;

    public Monitor() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
    }

    public void suscribe() {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_DATOS, "direct",true);
            channel.queueBind(QUEUE_DATOS,EXCHANGE_DATOS, "");

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
                    if (connection != null) connection.close();
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
                                   AMQP.BasicProperties properties, byte[] body) throws IOException {

            String message = new String(body, "UTF-8");

            System.out.println("recibido: " + message);


        }

    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Monitor suscriptor = new Monitor();

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

