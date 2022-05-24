package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import elementos.Alerta;
import elementos.Animal;
import elementos.Avistamiento;
import interfaz.MUsker;

import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ControladorAlertas {
    MUsker ventana;
    List<Alerta>listaAlertas;
    private static final String REST_SERVICE_URL = "http://localhost:8080";
    private Client client;
    public ControladorAlertas(MUsker mUsker) {
        this.ventana=mUsker;
        this.listaAlertas=new ArrayList<>();
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);
    }


    public List<Avistamiento> getAvistamientos(){
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/avistamientos/all");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<Avistamiento>>(){});
        } else {
            return null;
        }

    }


    public Animal getAnimal(int id) {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/animals/animal")
                .path(String.valueOf(id));
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<Animal>(){});
        } else {
            return null;
        }
    }

    public List<Alerta> getListaAlertas() {
        return listaAlertas;
    }

    public void alertaHandler(Alerta alerta) {
        this.listaAlertas.add(alerta);
        String [] palabras =this.ventana.getLabelAlertas().getText().split(" ");
        int num= listaAlertas.size();
        this.ventana.getLabelAlertas().setText(num+" "+palabras[1]);
        this.ventana.alertar();
    }

    public void quitarAlerta(Alerta alerta) {
        this.listaAlertas.remove(alerta);
        String [] palabras =this.ventana.getLabelAlertas().getText().split(" ");
        int num= listaAlertas.size();
        this.ventana.getLabelAlertas().setText(num+" "+palabras[1]);
        if(num==0){
            this.ventana.desAlertar();
        }
    }
}
