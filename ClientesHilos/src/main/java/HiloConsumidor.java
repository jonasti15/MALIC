import elementos.Animal;

import java.util.*;
import java.util.concurrent.Callable;

public class HiloConsumidor implements Callable<Double> {

    List<Long> listaTiempos;

    public HiloConsumidor(){
        this.listaTiempos=new ArrayList<>();
    }

    @Override
    public Double call() {
        for(int i=0;i<10;i++){
            Long principio=System.currentTimeMillis();
            String animal = RestController.RESTgetRequest("/animals/all", new HashMap<>(), String.class);
            Long fin=System.currentTimeMillis();
            Long tiempo= fin-principio;
            listaTiempos.add(tiempo);
            System.out.println("Tiempo: " +tiempo+" ms");
            System.out.println(animal);
        }
        return getAverage(listaTiempos);


    }
    private static double getAverage(List<Long> list) {
        return list.stream()
                .mapToLong(a -> a)
                .average().orElse(0);
    }
}
