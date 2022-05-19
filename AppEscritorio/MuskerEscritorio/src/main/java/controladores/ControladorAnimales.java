package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import dialogo.DialogoAnadirAnimal;
import dialogo.DialogoEditarAnimal;
import elementos.*;

import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ControladorAnimales  {
    JFrame ventana;
    DialogoAnadirAnimal dialogoAnadirAnimal;
    DialogoEditarAnimal dialogoEditarAnimal;
    private static final String REST_SERVICE_URL = "http://localhost:8080";
    private  Client client;
    public ControladorAnimales(JFrame mUsker) {
        this.ventana=mUsker;
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);
    }

    public void anadirAnimal() {
        dialogoAnadirAnimal=new DialogoAnadirAnimal(ventana, "Anadir animal", true, this);
    }

    public void editar(Animal animal) {
        dialogoEditarAnimal=new DialogoEditarAnimal(ventana, "Editar "+animal.getEspecie().getDescripcion()+" ID: "+ animal.getAnimal_id(),true, this, animal);

    }
    public List<Animal> getAnimales(){
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/animals/all");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<Animal>>(){});
        } else {
            return null;
        }
    }

    public void eliminar(Animal animal) {
        //llamar a REST para eliminar animal
    }


    public Especie[] getListaEspecies() {

        List<Especie> lista=getListEspecies();
        Especie[] especies = new Especie[lista.size()];
        for(int i =0;i<lista.size();i++){
            especies[i]=lista.get(i);
        }
        return especies;
    }
    public List<Especie> getListEspecies() {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/especies/all");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<Especie>>(){});
        } else {
            return null;
        }
    }


    public TipoEstado[] getListaEstados() {

        List<TipoEstado> lista=getListEstados();
        TipoEstado[] estados = new TipoEstado[lista.size()];
        for(int i =0;i<lista.size();i++){
            estados[i]=lista.get(i);
        }
        return estados;
    }
    public List<TipoEstado> getListEstados() {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/tipoestado/all");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<TipoEstado>>(){});
        } else {
            return null;
        }
    }

    public Recinto[] getListaRecintos() {
        List<Recinto> lista=getListRecintos();
        Recinto[] recintos = new Recinto[lista.size()];
        for(int i =0;i<lista.size();i++){
            recintos[i]=lista.get(i);
        }
        return recintos;
    }

    public List<Recinto> getListRecintos() {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/recintos/all");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<Recinto>>(){});
        } else {
            return null;
        }
    }


    public void editarAnimal(Long animal_id, Especie especie, TipoEstado estado, Recinto recinto) {
        //llamar a REST para editar animal
    }

    public void anadirAnimal(Especie especie, TipoEstado estado, Recinto recinto) {
        //llamar a REST para anadir animal
    }



}
