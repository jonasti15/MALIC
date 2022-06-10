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
import elementos.Animal;
import elementos.User;
import elementos.Visita;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import paneles.PanelPrincipal;

import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorVisitas {
    JFrame ventana;
    DialogoAnadirVisita dialogoAnadirVisita;
    DialogoEditarVisita dialogoEditarVisita;
    PanelPrincipal panel;
    public ControladorVisitas(JFrame mUsker, PanelPrincipal panelPrincipal) {
        this.ventana=mUsker;
        this.panel=panelPrincipal;

    }

    public void anadirVisita() {
        dialogoAnadirVisita=new DialogoAnadirVisita(ventana, "Anadir Visita", true, this);
    }

    public void editar(Visita visita) {
        dialogoEditarVisita=new DialogoEditarVisita(ventana, "Editar "+visita.getFecha()+" ID: "+ visita.getVisitaId(),true, this, visita);

    }

    public void eliminar(Visita visita) {
        RestController.RESTdeleteRequest("/visitas/delete/"+visita.getVisitaId(), new HashMap<>());
        panel.getControladorPantallaPrincipal().recargarVisitas();
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

        return RestController.RESTgetListRequest("/user/userType/2", new HashMap<>(), User.class);
    }

    public List<Visita> getListVisitas() {

        return RestController.RESTgetListRequest("/visitas/editables", new HashMap<>(), Visita.class);
    }



    public void editarVisita(Long Visita_id, Date fecha, User guia, String desc) {
        Visita visita=new Visita();
        visita.setVisitaId(Visita_id);
        visita.setFecha(fecha);
        visita.setDescripcion(desc);
        visita.setGuia(guia);
        RestController.RESTputRequest("/visitas/edit", new HashMap<>(), visita, Visita.class);
        panel.getControladorPantallaPrincipal().recargarVisitas();

    }

    public void anadirVisita(Date fecha, User guia, String desc) {
        Visita visita=new Visita();
        visita.setFecha(fecha);
        visita.setDescripcion(desc);
        visita.setGuia(guia);
        RestController.RESTpostRequest("/visitas/add", new HashMap<>(), visita, Visita.class);
        panel.getControladorPantallaPrincipal().recargarVisitas();
    }




}
