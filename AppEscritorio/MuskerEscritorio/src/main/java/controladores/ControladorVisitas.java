package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import dialogo.DialogoAnadirVisita;
import dialogo.DialogoEditarVisita;
import elementos.User;
import elementos.Visita;

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
    private static final String REST_SERVICE_URL = "http://localhost:8080/user";
    private final Client client = Client.create(new DefaultClientConfig());
    public ControladorVisitas(JFrame mUsker) {
        this.ventana=mUsker;
    }

    public void anadirVisita() {
        dialogoAnadirVisita=new DialogoAnadirVisita(ventana, "Anadir Visita", true, this);
    }

    public void editar(Visita visita) {
        dialogoEditarVisita=new DialogoEditarVisita(ventana, "Editar "+visita.getFecha()+" ID: "+ visita.getVisita_id(),true, this, visita);

    }

    public void eliminar(Visita visita) {
        //llamar a REST para eliminar visita
    }
    public List<User> getListTrabajadores() {
        List<User> lista = new ArrayList<>();
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/userType/"+2);
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<User>>(){});
        } else {
            return null;
        }
    }


    public User[] getListaUsuarios() {
        User[] lista = new User[0];
        List<User> listTrabajadores=getListTrabajadores();
        lista= (User[]) listTrabajadores.toArray();
        return lista;
    }


    public void editarVisita(Long Visita_id, Date fecha, User guia) {
        //llamar a REST para editar Visita
    }

    public void anadirVisita(Date fecha, User guia) {
        //llamar a REST para anadir Visita
    }




}
