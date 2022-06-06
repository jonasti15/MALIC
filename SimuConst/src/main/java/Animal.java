import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Animal implements Runnable {
    final static String EXCHANGE_NAME = "animal";
    private static final int NUM_MAX_CONSTANTES = 10;
    ConstantesMensaje constantes;
    ConnectionFactory factory;
    Channel channel;
    Especie especie;
    Random random;
    int animalId;
    int cont, end;
    Gson gson;

    public Animal(int animal_id, Especie especie, Channel channel) {
        cont = 0;
        end = 0;
        gson = new Gson();
        random = new Random();
        this.channel = channel;
        this.especie = especie;
        this.animalId = animal_id;
    }

    public void mandarMensaje(String mensaje) throws IOException {
        AMQP.BasicProperties properties = null;
        System.out.println(cont);
        channel.basicPublish(EXCHANGE_NAME, "", properties, mensaje.getBytes(StandardCharsets.UTF_8));
        System.out.println("Enviado: " + mensaje);
    }

    public String crearMensaje() {
        String m;
        float latidos, temperatura, tens_arterial, frrespiracion, saturacionO2;

        if (cont == 3) {
            latidos = random.nextFloat() * (especie.getECP().getMg().getMax() - especie.getECP().getMg().getMin())
                    + especie.getECP().getMg().getMin();
            temperatura = random.nextFloat() * (especie.getTemperatura().getMg().getMax() - especie.getTemperatura().getMg().getMin())
                    + especie.getTemperatura().getMg().getMin();
            tens_arterial = random.nextFloat() * (especie.getTension_arterial().getMg().getMax() - especie.getTension_arterial().getMg().getMin())
                    + especie.getTension_arterial().getMg().getMin();
            frrespiracion = random.nextFloat() * (especie.getFrrespiratoria().getMg().getMax() - especie.getFrrespiratoria().getMg().getMin())
                    + especie.getFrrespiratoria().getMg().getMin();
            saturacionO2 = random.nextFloat() * (especie.getSaturacionO2().getMg().getMax() - especie.getSaturacionO2().getMg().getMin())
                    + especie.getSaturacionO2().getMg().getMin();
        } else {
            latidos = random.nextFloat() * (especie.getECP().getB().getMax() - especie.getECP().getB().getMin())
                    + especie.getECP().getB().getMin();
            temperatura = random.nextFloat() * (especie.getTemperatura().getB().getMax() - especie.getTemperatura().getB().getMin())
                    + especie.getTemperatura().getB().getMin();
            tens_arterial = random.nextFloat() * (especie.getTension_arterial().getB().getMax() - especie.getTension_arterial().getB().getMin())
                    + especie.getTension_arterial().getB().getMin();
            frrespiracion = random.nextFloat() * (especie.getFrrespiratoria().getB().getMax() - especie.getFrrespiratoria().getB().getMin())
                    + especie.getFrrespiratoria().getB().getMin();
            saturacionO2 = random.nextFloat() * (especie.getSaturacionO2().getB().getMax() - especie.getSaturacionO2().getB().getMin())
                    + especie.getSaturacionO2().getB().getMin();
        }
        constantes = new ConstantesMensaje(animalId, especie.getId(), latidos, temperatura, tens_arterial, frrespiracion, saturacionO2);
        m = gson.toJson(constantes);
        return m;
    }


    @Override
    public void run() {
        try {
            while (end < NUM_MAX_CONSTANTES) {
                if (cont > 3) cont = 0;
                String mensaje = crearMensaje();
                mandarMensaje(mensaje);
                System.out.println(mensaje);
                cont++;
                end++;
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
