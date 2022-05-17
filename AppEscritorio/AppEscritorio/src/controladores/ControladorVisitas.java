package controladores;

import dialogo.DialogoAnadirAnimal;
import dialogo.DialogoAnadirVisita;
import dialogo.DialogoEditarAnimal;
import dialogo.DialogoEditarVisita;
import elementos.*;

import javax.swing.*;
import java.sql.Date;

public class ControladorVisitas {
    JFrame ventana;
    DialogoAnadirVisita dialogoAnadirVisita;
    DialogoEditarVisita dialogoEditarVisita;
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


    public User[] getListaUsuarios() {
        User[] lista = new User[0];
        //llamar a REST para obtener l lista

        return lista;
    }


    public void editarVisita(Long Visita_id, Date fecha, User guia) {
        //llamar a REST para editar Visita
    }

    public void anadirVisita(Date fecha, User guia) {
        //llamar a REST para anadir Visita
    }




}
