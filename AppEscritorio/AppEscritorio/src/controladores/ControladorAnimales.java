package controladores;

import dialogo.DialogoAnadirAnimal;
import dialogo.DialogoEditarAnimal;
import elementos.Animal;
import elementos.Especie;
import elementos.Recinto;
import elementos.TipoEstado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAnimales  {
    JFrame ventana;
    DialogoAnadirAnimal dialogoAnadirAnimal;
    DialogoEditarAnimal dialogoEditarAnimal;
    public ControladorAnimales(JFrame mUsker) {
        this.ventana=mUsker;
    }

    public void anadirAnimal() {
        dialogoAnadirAnimal=new DialogoAnadirAnimal(ventana, "Anadir animal", true, this);
    }

    public void editar(Animal animal) {
        dialogoEditarAnimal=new DialogoEditarAnimal(ventana, "Editar "+animal.getEspecie().getDescripcion()+" ID: "+ animal.getAnimal_id(),true, this, animal);

    }

    public void eliminar(Animal animal) {
        //llamar a REST para eliminar animal
    }


    public Especie[] getListaEspecies() {
        Especie[] lista = new Especie[0];
        //llamar a REST para obtener l lista

        return lista;
    }

    public TipoEstado[] getListaEstados() {
        TipoEstado[] lista = new TipoEstado[0];
        //llamar a REST para obtener l lista

        return lista;
    }

    public Recinto[] getListaRecintos() {
        Recinto[] lista = new Recinto[0];
        //llamar a REST para obtener l lista
        return lista;
    }


    public void editarAnimal(Long animal_id, Especie especie, TipoEstado estado, Recinto recinto) {
        //llamar a REST para editar animal
    }

    public void anadirAnimal(Especie especie, TipoEstado estado, Recinto recinto) {
        //llamar a REST para anadir animal
    }



}
