package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import elementos.*;
import interfaz.MUsker;

import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControladorAlertas {
    MUsker ventana;
    List<Alerta>listaAlertas;
    List<Avistamiento>listaAvistamientos;
    public ControladorAlertas(MUsker mUsker) {
        this.ventana=mUsker;
        this.listaAlertas=new ArrayList<>();
        this.listaAvistamientos=new ArrayList<>();
    }


    public List<Avistamiento> getAvistamientos(){
        return listaAvistamientos;

    }


    public Animal getAnimal(Long id) {
        return RestController.RESTgetRequest("/animals/animal/"+String.valueOf(id), new HashMap<>(), Animal.class);

    }
    public Especie getEspecie(int id) {
        return RestController.RESTgetRequest("/especies/especie/"+String.valueOf(id), new HashMap<>(), Especie.class);

    }

    public List<Alerta> getListaAlertas() {
        return listaAlertas;
    }

    public void alertaHandler(Alerta alerta) {
        this.listaAlertas.add(alerta);
        if(!this.ventana.isAlertado()){
            this.ventana.alertar();
        }

    }

    public void quitarAlerta(Alerta alerta) {
        this.listaAlertas.remove(alerta);
        if(this.listaAvistamientos.size()+this.listaAlertas.size()==0){
            this.ventana.desAlertar();
        }
    }
    public void quitarAvistamiento(Avistamiento avistamiento) {
        this.listaAvistamientos.remove(avistamiento);
        if(this.listaAvistamientos.size()+this.listaAlertas.size()==0){
            this.ventana.desAlertar();
        }
    }

    public void avistamientoHandler(Avistamiento avistamiento) {
        this.listaAvistamientos.add(avistamiento);
        if(!this.ventana.isAlertado()){
            this.ventana.alertar();
        }
    }

    public TipoEstado getEstado(Long id) {
       return RestController.RESTgetRequest("/tipoestado/type/"+id, new HashMap<>(), TipoEstado.class);
    }
    public void editarAnimal(Long animal_id, Especie especie, TipoEstado estado, Recinto recinto, TipoEstado estadoIa) {
        Animal animal=new Animal();
        animal.setAnimalId(animal_id);
        animal.setEspecie(especie);
        animal.setEstado(estado);
        animal.setEstadoIa(estadoIa);
        animal.setRecinto_id(recinto);
        RestController.RESTpostRequest("/animals/edit", new HashMap<>(), animal, Animal.class);
    }
}
