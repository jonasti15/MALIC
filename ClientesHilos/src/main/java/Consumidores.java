import com.sun.jersey.api.representation.Form;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class Consumidores {
    public static String access_token = "";
    public static String refresh_token = "";
    public static final int NUM_CLIENTE = 0;
    ExecutorService executor;
    List<HiloConsumidor> tareas;
    List<Future<Double>> lista;
    List<Double> maximos;

    public Consumidores() throws InterruptedException {
        login();
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        tareas= new ArrayList<>();
        maximos=new ArrayList<>();
        for (int i = 0; i< NUM_CLIENTE; i++) {
            tareas.add(new HiloConsumidor());
        }

        lista = executor.invokeAll(tareas) ;
        executor.shutdown();
        for (Future<Double> item: lista){

            try {
                maximos.add(item.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.awaitTermination(2000000, TimeUnit.SECONDS);
        System.out.println("Tiempo medio esperado: "+ getAverage(maximos)+" ms");


    }
    private static double getAverage(List<Double> list) {
        return list.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }

    private void login() {
        Form form = new Form();
        form.add("username", "worker");
        form.add("password","worker");

        String jsonToken = RestController.RESTpostRequestLogin("/login", form);
        if (!jsonToken.equals("")) {
            JSONObject obj = null;
            String access_token = "", refresh_token = "";
            try {
                obj = new JSONObject(jsonToken);
                Consumidores.access_token = obj.getString("access_token");
                Consumidores.refresh_token = obj.getString("refresh_token");
                System.out.println("Sesion iniciada");
            } catch (JSONException e) {
                System.out.println("Error Parsing tokens");
            }
        } else {
            System.out.println("Usuario no válido");

        }
    }

    public static void restartProgram() throws IOException {
        System.exit(0);
    }
    public static void main(String[] args) {
        try {
            Consumidores programa=new Consumidores();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
