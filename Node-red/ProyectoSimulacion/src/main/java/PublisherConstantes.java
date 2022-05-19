import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

public class PublisherConstantes {
    final static String EXCHANGE_NAME = "simuladorAnimales";
    ControllerJSON controllerJSON;
    ConnectionFactory factory;
    Constantes constantes;
    Random random;

    public PublisherConstantes() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        constantes = new Constantes();
        controllerJSON = new ControllerJSON();
        random = new Random();
    }

    public void enviarMensaje() {
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);

            AMQP.BasicProperties properties = null;
            Calendar c = Calendar.getInstance(Locale.US);
            Date d = c.getTime();

            int randomNum = ThreadLocalRandom.current().nextInt(30, 100 + 1);
            constantes.setConstanteId(random.nextInt());
            constantes.setAnimalId(random.nextInt());
            constantes.setFecha(d);
            constantes.setFrrespiracion(randomNum);
            randomNum = ThreadLocalRandom.current().nextInt(30, 100 + 1);
            constantes.setLatidos(randomNum);
            randomNum = ThreadLocalRandom.current().nextInt(30, 100 + 1);
            constantes.setPresion(randomNum);
            randomNum = ThreadLocalRandom.current().nextInt(30, 100 + 1);
            constantes.setTemperatura(randomNum);

            String mensaje = controllerJSON.generateConstantesJSON(constantes);
            channel.basicPublish(EXCHANGE_NAME, "", properties, mensaje.getBytes(StandardCharsets.UTF_8));

            System.out.println("Enviado: " + mensaje);
            channel.close();

        } catch (IOException | TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PublisherConstantes publisherConstantes = new PublisherConstantes();
        publisherConstantes.enviarMensaje();
    }
}
