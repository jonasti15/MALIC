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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorAnimales  {
    JFrame ventana;
    DialogoAnadirAnimal dialogoAnadirAnimal;
    DialogoEditarAnimal dialogoEditarAnimal;
    PanelPrincipal panel;
    public ControladorAnimales(JFrame mUsker, PanelPrincipal panelPrincipal) {
        this.ventana=mUsker;
        this.panel=panelPrincipal;
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
        return RestController.RESTgetListRequest("/estancias/shelter", new HashMap<>(),Estancia.class);
    }


    public void eliminar(Animal animal) {
        Estancia estancia=getEstanciaByAnimalId(animal.getAnimalId());
        java.util.Date hoy =new java.util.Date();
        Date hoySql=new Date(hoy.getTime());
        estancia.setFechaSalida(hoySql);
        RestController.RESTpostRequest("/estancias/add", new HashMap<>(),estancia ,Estancia.class );
        panel.getControladorPantallaPrincipal().recargarAnimales();

    }
    public void sendPhoto(String pathRest, String pathReal) throws IOException {
        int[][][] colors = null;

        File f = new File(pathReal);
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
        HashMap<String,Object> headers=new HashMap<>();
        headers.put("path", pathRest);
        RestController.RESTpostRequest("/images/save", headers, colors, String.class);
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
        return RestController.RESTgetListRequest("/especies/all", new HashMap<>(), Especie.class);
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
        return  RestController.RESTgetListRequest("/tipoestado/all", new HashMap<>(), TipoEstado.class);
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
        return  RestController.RESTgetListRequest("/recintos/all", new HashMap<>(), Recinto.class);
    }


    public Animal editarAnimal(Long animal_id, Especie especie, TipoEstado estado, Recinto recinto) {
        Animal animal=RestController.RESTgetRequest("/animals/animal/"+animal_id, new HashMap<>(), Animal.class);
        int ocupados= RestController.RESTgetRequest("/recintos/ocupacion/"+recinto.getRecinto_id(), new HashMap<>(), Integer.class);
        if(ocupados>=recinto.getCantidad_animales()&&animal.getRecinto_id().getRecinto_id()!=recinto.getRecinto_id()){
            JOptionPane.showMessageDialog(null, "El recinto seleccionado esta lleno!","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }else{
            animal.setEspecie(especie);
            animal.setEstado(estado);
            animal.setRecinto_id(recinto);
            panel.getControladorPantallaPrincipal().recargarAnimales();
            return animal;
        }

    }

    public Animal anadirAnimal(Especie especie, TipoEstado estado, Recinto recinto) {
        Animal animal=new Animal();
        animal.setEspecie(especie);
        animal.setEstado(estado);
        int ocupados= RestController.RESTgetRequest("/recintos/ocupacion/"+recinto.getRecinto_id(), new HashMap<>(), Integer.class);
        if(ocupados>=recinto.getCantidad_animales()){
            JOptionPane.showMessageDialog(null, "El recinto seleccionado esta lleno!","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }else{
            animal.setRecinto_id(recinto);
            Animal animalCreado = RestController.RESTpostRequest("/animals/add", new HashMap<>(), animal, Animal.class);
            panel.getControladorPantallaPrincipal().recargarAnimales();
            return animalCreado;
        }



    }
    public void anadirEstancia(String motivo, Date fechaEntrada) {
        Estancia estancia=new Estancia();
        Animal animal=getLastAnimal();
        estancia.setAnimal(animal);
        estancia.setFecha_entrada(fechaEntrada);
        estancia.setMotivo_entrada(motivo);
        RestController.RESTpostRequest("/estancias/add", new HashMap<>(), estancia, Estancia.class);
        panel.getControladorPantallaPrincipal().recargarAnimales();

    }

    private Animal getLastAnimal() {
        return RestController.RESTgetRequest("/animals/last", new HashMap<>(), Animal.class);
    }
    public Estancia getEstanciaByAnimalId(long id) {
        return RestController.RESTgetRequest("/estancias/animal/"+id, new HashMap<>(), Estancia.class);
    }


}
