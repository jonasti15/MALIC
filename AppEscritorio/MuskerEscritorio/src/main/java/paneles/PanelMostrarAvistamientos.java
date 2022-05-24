package paneles;

import controladores.ControladorAlertas;
import dialogo.DialogoInfoAvistamiento;
import elementos.Avistamiento;
import modelotablas.ModeloColumnasTablaAvistamientos;
import modelotablas.ModeloTablaAvistamientos;
import renderertablas.RenderTablaAvistamientos;

import javax.swing.*;
import java.awt.*;

public class PanelMostrarAvistamientos extends JScrollPane {
    JTable tabla;
    ModeloColumnasTablaAvistamientos colum;
    ModeloTablaAvistamientos tablaModel;
    RenderTablaAvistamientos renderer;
    JFrame MUsker;
    ControladorAlertas controlador;
    public PanelMostrarAvistamientos(ControladorAlertas controlador){
        this.controlador=controlador;
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.renderer=new RenderTablaAvistamientos();
        this.tablaModel=new ModeloTablaAvistamientos(controlador.getAvistamientos());
        this.colum=new ModeloColumnasTablaAvistamientos(renderer);

        tabla = new JTable(tablaModel, colum);
        tabla.setBackground(Color.white);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                Avistamiento alerta=tablaModel.getAvistamiento(row);
                DialogoInfoAvistamiento dialogoInfoAvistamiento=new DialogoInfoAvistamiento(MUsker,"Avistamiento: "+alerta.getEspecie(),false, alerta,controlador );
            }
        });
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setViewportView(tabla);
    }
}
