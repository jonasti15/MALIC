import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
    private static final String QUEUE_DATOS = "colaDatos";
    final static String EXCHANGE_NAME = "simuladorAnimales";
    ConnectionFactory factory;
    Random generador;

    public Publisher() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        generador = new Random();
    }

    public void enviarMensaje() {
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);

            BasicProperties properties = null;
            ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.SIZE);
            String mensaje = "Hola";
            channel.basicPublish(EXCHANGE_NAME, "", properties, mensaje.getBytes(StandardCharsets.UTF_8));

            System.out.println("Enviado: " + mensaje);
            channel.close();

        } catch (IOException | TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.enviarMensaje();
    }

}
