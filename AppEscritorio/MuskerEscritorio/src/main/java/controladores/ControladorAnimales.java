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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ControladorAnimales  {
    private static String BASE_PATH = "C:/Users/ibail/OneDrive/Escritorio/LANAK/3 MAILA/EBAL2/PBL6/MALIC/AppEscritorio/MuskerEscritorio";
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
        dialogoEditarAnimal=new DialogoEditarAnimal(ventana, "Editar "+animal.getEspecie().getDescripcion()+" ID: "+ animal.getAnimalId(),true, this, animal);

    }
    public List<Animal> getAnimales(){
        List<Animal> lista=new ArrayList<>();
        List<Estancia>listaEstancias=getEstanciasActivas();
        for(Estancia e:listaEstancias){
            lista.add(e.getAnimal());
        }
        return lista;
    }
    public List<Estancia> getEstanciasActivas(){
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/estancias/shelter");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<List<Estancia>>(){});
        } else {
            return null;
        }
    }


    public void eliminar(Animal animal) {
        Estancia estancia=getEstanciaByAnimalId(animal.getAnimalId());
        java.util.Date hoy =new java.util.Date();
        Date hoySql=new Date(hoy.getTime());
        estancia.setFechaSalida(hoySql);
        WebResource webResource = client.resource(REST_SERVICE_URL).path("/estancias/add");
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, estancia);
        panel.getControladorPantallaPrincipal().recargarAnimales();
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Se ha editado un objeto.");

        } else {
            System.out.println("La llamada no ha sido correcta.");
        }
    }
    public void sendPhoto(String p) throws IOException {
        int[][][] colors = null;

        File f = new File(BASE_PATH + p);
        BufferedImage bimg = ImageIO.read(f);
        int w = bimg.getWidth();
        int h = bimg.getHeight();

        int[] dataBuffInt = bimg.getRGB(0, 0, w, h, null, 0, w);

        colors = new int[h][w][3];

        int i = 0;
        int j = 0;
        int k = 0;

        for (Integer in : dataBuffInt) {
            Color c = new Color(in);
            colors[k][j][0] = c.getRed();
            colors[k][j][1] = c.getGreen();
            colors[k][j][2] = c.getBlue();

            j++;

            if (j == w) {
                j = 0;
                k++;
            }
        }

        WebResource webResource = client.resource(REST_SERVICE_URL).path("/images/save");
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).header("path", p).post(ClientResponse.class, colors);

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
        animal.setAnimalId(animal_id);
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

    public Animal anadirAnimal(Especie especie, TipoEstado estado, Recinto recinto) {
        Animal animal=new Animal();
        animal.setEspecie(especie);
        animal.setEstado(estado);
        animal.setRecinto_id(recinto);
        WebResource webResource = client.resource(REST_SERVICE_URL).path("/animals/add");
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, animal);

        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Se ha creado un objeto nuevo.");
            return clientResponse.getEntity(new GenericType<Animal>(){});
        } else {
            System.out.println("La llamada no ha sido correcta.");
            return null;
        }

    }
    public void anadirEstancia(String motivo, Date fechaEntrada) {
        Estancia estancia=new Estancia();
        Animal animal=getLastAnimal();
        estancia.setAnimal(animal);
        estancia.setFecha_entrada(fechaEntrada);
        estancia.setMotivo_entrada(motivo);
        WebResource webResource = client.resource(REST_SERVICE_URL).path("/estancias/add");
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, estancia);
        panel.getControladorPantallaPrincipal().recargarAnimales();
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Se ha creado un objeto nuevo.");

        } else {
            System.out.println("La llamada no ha sido correcta.");
        }
    }

    private Animal getLastAnimal() {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/animals/last");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<Animal>(){});
        } else {
            return null;
        }

    }
    public Estancia getEstanciaByAnimalId(long id) {
        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("/estancias/animal").path(String.valueOf(id));
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return clientResponse.getEntity(new GenericType<Estancia>(){});
        } else {
            return null;
        }

    }


}
