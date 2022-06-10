package paneles;

import controladores.ControladorAlertas;
import dialogo.DialogoInfoAlerta;
import elementos.Alerta;
import modelotablas.ModeloColumnasTablaAlertas;
import modelotablas.ModeloTablaAlertas;
import renderertablas.RenderTablaAlertas;

import javax.swing.*;
import java.awt.*;

public class PanelMostrarAlertas extends JScrollPane {
    JTable tabla;
    ModeloColumnasTablaAlertas colum;
    ModeloTablaAlertas tablaModel;
    RenderTablaAlertas renderer;
    JFrame MUsker;
    ControladorAlertas controlador;
    public PanelMostrarAlertas(ControladorAlertas controlador){
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.renderer=new RenderTablaAlertas();
        this.tablaModel=new ModeloTablaAlertas(controlador.getListaAlertas());
        this.colum=new ModeloColumnasTablaAlertas(renderer);
        this.controlador=controlador;

        tabla = new JTable(tablaModel, colum);
        tabla.setBackground(Color.white);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                Alerta alerta=tablaModel.getAlerta(row);
                DialogoInfoAlerta dialogoInfoAnimal=new DialogoInfoAlerta(MUsker,"Alerta: "+alerta.getEspecie(),false, alerta, controlador);
            }
        });
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setViewportView(tabla);
    }
}
