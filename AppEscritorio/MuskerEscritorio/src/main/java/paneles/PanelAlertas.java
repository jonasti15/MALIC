package paneles;

import controladores.ControladorAlertas;
import controladores.ControladorPantallaPrincipal;
import elementos.Visita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelAlertas extends JPanel implements ActionListener {
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORTOOLBAR = new Color(118, 185, 71);
    private static final Color COLORLETRA = new Color(47, 82, 51);
    ControladorPantallaPrincipal controladorPantallaPrincipal;
    List<Visita> listaAnimales;
    JScrollPane panelMostrarAlertas, panelMostrarAvistamientos;
    JButton botonMostrarAlertas, botonMostrarAvistamientos;
    JScrollPane panelContenido;
    JLabel pagina;
    ControladorAlertas controladorAlertas;

    public PanelAlertas(ControladorPantallaPrincipal controladorPantallaPrincipal, JFrame MUsker, ControladorAlertas controladorAlertas){
        this.setLayout(new BorderLayout(0,0));
        this.listaAnimales=new ArrayList<>();
        this.controladorAlertas=controladorAlertas;
        this.controladorPantallaPrincipal=controladorPantallaPrincipal;
        this.setBackground(COLORFONDO);
        this.setPreferredSize(new Dimension(2,2));
        this.add(crearPanel(), BorderLayout.CENTER);
        this.add(getToolBar(), BorderLayout.NORTH);
        this.add(crearPaelVacio(), BorderLayout.WEST);
        this.add(crearPaelVacio(), BorderLayout.EAST);
    }

    private JPanel crearPaelVacio() {
        JPanel panel=new JPanel();
        panel.setBackground(COLORFONDO);
        panel.setPreferredSize(new Dimension(200,200));
        return panel;
    }

    private Component crearPanel() {
        panelContenido =new JScrollPane();
        panelContenido.setBackground(Color.white);
        panelContenido.setBorder(BorderFactory.createLineBorder(Color.white));
        panelContenido.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelContenido.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelContenido.setViewportView(crearPanelMostrarAlertas());
        return panelContenido;
    }

    private Component crearPanelMostrarAlertas() {
        this.panelMostrarAlertas=new PanelMostrarAlertas(controladorAlertas);
        this.panelMostrarAvistamientos=new PanelMostrarAvistamientos(controladorAlertas);
        return panelMostrarAlertas;

    }

    private JToolBar getToolBar() {
        JToolBar barraBotones = new JToolBar();

        double tamanopantalla= Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double tamanoseparador=(tamanopantalla/2)-40;
        pagina=new JLabel("Alertas     ");
        pagina.setForeground(COLORLETRA);
        barraBotones.add(pagina);
        barraBotones.addSeparator(new Dimension((int) tamanoseparador,0));
        barraBotones.add(crearBoton("MUskerBarra"), BorderLayout.CENTER);
        barraBotones.addSeparator(new Dimension((int) tamanoseparador-190,0));
        this.botonMostrarAlertas=crearBotonCambiarVista("mostrarAlertas",false);

        barraBotones.add(botonMostrarAlertas);
        this.botonMostrarAvistamientos=crearBotonCambiarVista("mostrarAvistamientos",true);
        barraBotones.add(botonMostrarAvistamientos);
        barraBotones.setBackground(COLORTOOLBAR);
        return barraBotones;
    }

    private JButton crearBotonCambiarVista(String name, boolean active) {
        JButton boton =new JButton(new ImageIcon("images/"+name+".png"));
        boton.setActionCommand(name);
        boton.setEnabled(active);
        boton.addActionListener(this);
        boton.setBackground(COLORTOOLBAR);
        return boton;
    }

    private JButton crearBoton(String str) {
        JButton boton =new JButton(new ImageIcon("images/"+str+".png"));
        boton.setActionCommand(str);
        boton.addActionListener(controladorPantallaPrincipal);
        boton.setBackground(COLORTOOLBAR);
        return boton;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch(accion) {
            case "mostrarAvistamientos":
                this.botonMostrarAvistamientos.setEnabled(false);
                this.botonMostrarAlertas.setEnabled(true);
                panelMostrarAvistamientos=new PanelMostrarAvistamientos(controladorAlertas);
                this.panelContenido.setViewportView(panelMostrarAvistamientos);
                pagina.setText("Avistamientos");

                break;
            case "mostrarAlertas":
                this.botonMostrarAvistamientos.setEnabled(true);
                this.botonMostrarAlertas.setEnabled(false);
                panelMostrarAlertas= new PanelMostrarAlertas(controladorAlertas);
                this.panelContenido.setViewportView(panelMostrarAlertas);
                pagina.setText("Alertas     ");
                break;

        }
    }
}
