package controladores;

import elementos.Alerta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorAlertas {
    JFrame ventana;
    List<Alerta>listaAlertas;
    public ControladorAlertas(JFrame mUsker) {
        this.ventana=mUsker;
        this.listaAlertas=new ArrayList<>();
    }

    public List<Alerta> getAlertas(){
        //coger alertas de RabbitMQ
        return this.listaAlertas;

    }



}
