package paneles;

import com.sun.xml.internal.ws.dump.LoggingDumpTube;

import javax.swing.*;
import java.awt.*;

public class PanelMostrarAnimales extends JPanel  {
    public PanelMostrarAnimales (){
        this.setLayout(new BorderLayout(10,10));
        this.add(getToolBar(), BorderLayout.NORTH);
        this.add(crearPanel());
    }

    private Component crearPanel() {
        JScrollPane panel =new JScrollPane();
        panel.setBackground(Color.white);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.lista=new JList<>();
        panel.setBorder(null);
        RendererPlan rPlan=new RendererPlan(this.controlador.usuario);
        this.listaPlanes= controlador.cargarPlanes();
        this.lista.setModel(this.listaPlanes);
        this.lista.setCellRenderer(rPlan);
        this.lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.setViewportView(lista);
        return panel;
    }

    private JToolBar getToolBar() {
        JToolBar barraBotones = new JToolBar();
        Color color=new Color(118, 185, 71);
        JButton boton =new JButton(new ImageIcon("images/MUskerBarra.png"));
        boton.setBackground(color);
        barraBotones.addSeparator();
        barraBotones.add(boton, BorderLayout.CENTER);

        barraBotones.setBackground(color);
        return barraBotones;
    }
}
