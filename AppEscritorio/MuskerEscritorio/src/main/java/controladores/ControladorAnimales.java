package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import dialogo.DialogoAnadirAnimal;
import dialogo.DialogoEditarAnimal;
import elementos.*;
import paneles.PanelPrincipal;

import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ControladorAnimales  {
    JFrame ventana;
    DialogoAnadirAnimal dialogoAnadirAnimal;
    DialogoEditarAnimal dialogoEditarAnimal;
    private static final String REST_SERVICE_URL = "http://localhost:8080";
    private  Client client;
    PanelPrincipal panel;
    public ControladorAnimales(JFrame mUsker, PanelPrincipal panelPrincipal) {
        this.ventana=mUsker;
        this.panel=panelPrincipal;
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
        WebResource webResource = client.resource(REST_SERVICE_URL).path("/animals/delete").path(String.valueOf(animal.getAnimal_id()));
        ClientResponse clientResponse = webResource.type(MediaType.TEXT_PLAIN_TYPE).delete(ClientResponse.class);
        panel.getControladorPantallaPrincipal().recargarAnimales();
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("El animal ha sido eliminado correctamente.");
        } else {
            System.out.println("La llamada no ha sido correcta.");
        }
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
        Animal animal=new Animal();
        animal.setAnimal_id(animal_id);
        animal.setEspecie(especie);
        animal.setEstado(estado);
        animal.setRecinto_id(recinto);
        WebResource webResource = client.resource(REST_SERVICE_URL).path("/animals/edit");
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, animal);
        panel.getControladorPantallaPrincipal().recargarAnimales();
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Se ha editado un objeto.");

        } else {
            System.out.println("La llamada no ha sido correcta.");
        }
    }

    public void anadirAnimal(Especie especie, TipoEstado estado, Recinto recinto) {
        Animal animal=new Animal();
        animal.setEspecie(especie);
        animal.setEstado(estado);
        animal.setRecinto_id(recinto);
        WebResource webResource = client.resource(REST_SERVICE_URL).path("/animals/add");
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, animal);
        panel.getControladorPantallaPrincipal().recargarAnimales();
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Se ha creado un objeto nuevo.");

        } else {
            System.out.println("La llamada no ha sido correcta.");
        }
    }



}
