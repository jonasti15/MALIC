
package controladores;

import elementos.Permisos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import interfaz.MUsker;
import paneles.*;

import javax.swing.*;

public class ControladorPantallaPrincipal implements ActionListener {
    PreparedStatement ps = null;
    ResultSet rs = null;
    PanelPrincipal panelActual;
    JFrame MUsker;
    ControladorAnimales controlador;
    ControladorVisitas controladorVisitas;

    public ControladorPantallaPrincipal(PanelPrincipal panel, MUsker mUsker, ControladorAnimales controlador, ControladorVisitas controladorVisitas) {
        this.panelActual = panel;
        this.MUsker=mUsker;
        this.controlador=controlador;
        this.controladorVisitas=controladorVisitas;
    }

    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch(accion) {
            case "mostrarAnimales":
                System.out.println("Mostrar animales");
                PanelMostrarAnimales panelMostrarAnimales = new PanelMostrarAnimales(this, this.MUsker, controlador);
                this.panelActual.setViewportView(panelMostrarAnimales);
                break;
            case "alertas":
                PanelAlertas panelAlertas = new PanelAlertas(this, this.MUsker);
                this.panelActual.setViewportView(panelAlertas);
                break;
            case "mostrarVisitas":
                PanelMostrarVisitas panelMostrarVisitas = new PanelMostrarVisitas(this, this.MUsker, controladorVisitas);
                this.panelActual.setViewportView(panelMostrarVisitas);
                break;
            case "MUskerBarra":
                PanelMenu menu =new PanelMenu(this);
                this.panelActual.setViewportView(menu);
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
