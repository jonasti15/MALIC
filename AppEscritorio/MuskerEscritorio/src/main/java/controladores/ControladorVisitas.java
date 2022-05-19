package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import dialogo.DialogoAnadirVisita;
import dialogo.DialogoEditarVisita;
import elementos.User;
import elementos.Visita;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ControladorVisitas {
    JFrame ventana;
    DialogoAnadirVisita dialogoAnadirVisita;
    DialogoEditarVisita dialogoEditarVisita;
    private static final String REST_SERVICE_URL = "http://localhost:8080";
    private Client client;
    public ControladorVisitas(JFrame mUsker) {
        this.ventana=mUsker;
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);
    }

    public void anadirVisita() {
        dialogoAnadirVisita=new DialogoAnadirVisita(ventana, "Anadir Visita", true, this);
    }

    public void editar(Visita visita) {
        dialogoEditarVisita=new DialogoEditarVisita(ventana, "Editar "+visita.getFecha()+" ID: "+ visita.getVisitaId(),true, this, visita);

    }

    public void eliminar(Visita visita) {
        //llamar a REST para eliminar visita
    }
    public User[] getListaUsuarios() {
        List<User> lista=getListTrabajadores();
        User[] especies = new User[lista.size()];
        for(int i =0;i<lista.size();i++){
            especies[i]=lista.get(i);
        }
        return especies;
    }
    public List<User> getListTrabajadores() {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/user/userType/2");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<User>>(){});
        } else {
            return null;
        }
    }

    public List<Visita> getListVisitas() {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/visitas/all");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<Visita>>(){});
        } else {
            return null;
        }
    }



    public void editarVisita(Long Visita_id, Date fecha, User guia) {
        //llamar a REST para editar Visita
    }

    public void anadirVisita(Date fecha, User guia, String desc) {
        Visita visita=new Visita();
        visita.setFecha();
        WebResource webResource = client.resource(REST_SERVICE_URL).path("crearJson");
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, pelicula2);

        if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("Se ha creado un objeto nuevo.");
        } else {
            System.out.println("La llamada no ha sido correcta.");
        }
    }




}
