
package controladores;

import interfaz.MUsker;
import paneles.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorPantallaPrincipal implements ActionListener {
    PreparedStatement ps = null;
    ResultSet rs = null;
    PanelPrincipal panelActual;
    JFrame MUsker;
    ControladorAnimales controlador;
    ControladorVisitas controladorVisitas;
    ControladorAlertas controladorAlertas;
    JPanel actual;

    public ControladorPantallaPrincipal(PanelPrincipal panel, MUsker mUsker, ControladorAnimales controlador, ControladorVisitas controladorVisitas, ControladorAlertas controladorAlertas) {
        this.panelActual = panel;
        this.MUsker=mUsker;
        this.controlador=controlador;
        this.controladorVisitas=controladorVisitas;
        this.controladorAlertas=controladorAlertas;

    }
    void recargarVisitas(){
        actual = new PanelMostrarVisitas(this, this.MUsker, controladorVisitas);
        this.panelActual.setViewportView(actual);
    }
    void recargarAnimales(){
        actual = new PanelMostrarAnimales(this, this.MUsker, controlador);
        this.panelActual.setViewportView(actual);
    }
    void recargarAlertas(){
        actual = new PanelAlertas(this, this.MUsker, controladorAlertas);
        this.panelActual.setViewportView(actual);
    }


    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch(accion) {
            case "mostrarAnimales":
                System.out.println("Mostrar animales");
                actual = new PanelMostrarAnimales(this, this.MUsker, controlador);
                this.panelActual.setViewportView(actual);
                break;
            case "alertas":
                actual = new PanelAlertas(this, this.MUsker, controladorAlertas);
                this.panelActual.setViewportView(actual);
                break;
            case "mostrarVisitas":
                actual = new PanelMostrarVisitas(this, this.MUsker, controladorVisitas);
                this.panelActual.setViewportView(actual);
                break;
            case "MUskerBarra":
                actual =new PanelMenu(this);
                this.panelActual.setViewportView(actual);
                break;
            case "masAnimales":
                this.controlador.anadirAnimal();
                this.MUsker.repaint();
                break;
            case "masVisitas":
                this.controladorVisitas.anadirVisita();
                this.MUsker.repaint();
                break;
        }


    }
}
