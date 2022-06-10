package paneles;

import controladores.ControladorAlertas;
import controladores.ControladorVisitas;
import dialogo.DialogoInfoAlerta;
import dialogo.DialogoInfoVisita;
import elementos.Alerta;
import elementos.Visita;
import modelotablas.ModeloColumnasTablaAlertas;
import modelotablas.ModeloColumnasTablaVisitas;
import modelotablas.ModeloTablaAlertas;
import modelotablas.ModeloTablaVisitas;
import renderertablas.RenderTablaAlertas;
import renderertablas.RenderTablaVisitas;

import javax.swing.*;

public class PanelMostrarAlertas extends JScrollPane {
    JTable tabla;
    ModeloColumnasTablaAlertas colum;
    ModeloTablaAlertas tablaModel;
    RenderTablaAlertas renderer;
    JFrame MUsker;
    ControladorAlertas controlador;
    public PanelMostrarAlertas(){
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.renderer=new RenderTablaAlertas();
        this.tablaModel=new ModeloTablaAlertas();
        this.colum=new ModeloColumnasTablaAlertas(renderer);

        tabla = new JTable(tablaModel, colum);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                Alerta alerta=tablaModel.getAlerta(row);
                DialogoInfoAlerta dialogoInfoAnimal=new DialogoInfoAlerta(MUsker,"Alerta: "+alerta.getEspecie(),false, alerta);
            }
        });
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setViewportView(tabla);
    }
}
