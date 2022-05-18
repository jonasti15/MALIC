package paneles;

import controladores.ControladorAlertas;
import dialogo.DialogoInfoAvistamiento;
import elementos.Avistamiento;
import modelotablas.ModeloColumnasTablaAvistamientos;
import modelotablas.ModeloTablaAvistamientos;
import renderertablas.RenderTablaAvistamientos;

import javax.swing.*;

public class PanelMostrarAvistamientos extends JScrollPane {
    JTable tabla;
    ModeloColumnasTablaAvistamientos colum;
    ModeloTablaAvistamientos tablaModel;
    RenderTablaAvistamientos renderer;
    JFrame MUsker;
    ControladorAlertas controlador;
    public PanelMostrarAvistamientos(){
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.renderer=new RenderTablaAvistamientos();
        this.tablaModel=new ModeloTablaAvistamientos();
        this.colum=new ModeloColumnasTablaAvistamientos(renderer);

        tabla = new JTable(tablaModel, colum);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                Avistamiento alerta=tablaModel.getAvistamiento(row);
                DialogoInfoAvistamiento dialogoInfoAnimal=new DialogoInfoAvistamiento(MUsker,"Avistamiento: "+alerta.getEspecie(),false, alerta);
            }
        });
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setViewportView(tabla);
    }
}
