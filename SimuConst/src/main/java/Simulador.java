import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Simulador {
    final static String EXCHANGE_NAME = "animal";
    private static final int NUM_ANIMAL = 2;
    Especies especies;
    ExecutorService executor;
    Channel channel;
    ConnectionFactory factory;

    public Simulador() {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        factory = new ConnectionFactory();
        factory.setHost("musker.duckdns.org");
        factory.setPort(5672);
        factory.setUsername("super");
        factory.setPassword("jj7jzYJ9");
    }

    public void publicadorDeConstantes() throws InterruptedException {
        Random r = new Random();
        try (Connection connection = factory.newConnection()){
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout", true);
            for (int i = 0; i< NUM_ANIMAL; i++) {
                int result = r.nextInt(especies.getEspecie().size());
                executor.execute(new Animal(i+1, especies.getEspecie().get(result), channel));
            }
            executor.shutdown();
            executor.awaitTermination(200000, TimeUnit.SECONDS);
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void unmarshaller() {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Especies.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File XMLfile = new File(".\\src\\main\\resources\\Animals.xml");
            especies = (Especies) jaxbUnmarshaller.unmarshal(XMLfile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Simulador simulador = new Simulador();
        simulador.unmarshaller();
        simulador.publicadorDeConstantes();
    }
}
