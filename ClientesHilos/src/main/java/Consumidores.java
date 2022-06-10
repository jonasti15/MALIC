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
    public static final int NUM_CLIENTE = 1000;
    ExecutorService executor;
    List<HiloConsumidor> tareas;
    List<Future<String>> lista;
    List<String> maximos;


    public Consumidores() throws InterruptedException {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        tareas= new ArrayList<>();
        maximos=new ArrayList<>();
        for (int i = 0; i< NUM_CLIENTE; i++) {
            tareas.add(new HiloConsumidor());
        }
        Long principio=System.currentTimeMillis();
        lista = executor.invokeAll(tareas) ;
        executor.shutdown();
        for (Future<String> item: lista){

            try {
                maximos.add(item.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        Long fin=System.currentTimeMillis();
        long tiempo= (fin-principio)/1000;
        System.out.println("Tiempo: "+tiempo+" s");
        executor.awaitTermination(900, TimeUnit.SECONDS);


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
