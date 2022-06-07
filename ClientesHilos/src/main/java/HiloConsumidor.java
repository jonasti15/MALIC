import elementos.Animal;

import java.util.HashMap;
import java.util.List;

public class HiloConsumidor implements Runnable {

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            Long principio=System.currentTimeMillis();
            String animal = RestController.RESTgetRequest("/animals/all", new HashMap<>(), String.class);
            Long fin=System.currentTimeMillis();
            Long tiempo= fin-principio;
            System.out.println("Tiempo: " +tiempo+" ms");
            System.out.println(animal);
        }

    }
}
