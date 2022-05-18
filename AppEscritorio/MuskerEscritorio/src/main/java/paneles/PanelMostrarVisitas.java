package paneles;

import controladores.ControladorPantallaPrincipal;
import controladores.ControladorVisitas;
import dialogo.DialogoInfoVisita;
import elementos.Visita;
import modelotablas.ModeloColumnasTablaVisitas;
import modelotablas.ModeloTablaVisitas;
import renderertablas.RenderTablaVisitas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelMostrarVisitas extends JPanel {
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORTOOLBAR = new Color(118, 185, 71);
    private static final Color COLORLETRA = new Color(47, 82, 51);
    ControladorPantallaPrincipal controladorPantallaPrincipal;
    List<Visita> listaAnimales;

    JTable tabla;
    ModeloColumnasTablaVisitas colum;
    ModeloTablaVisitas tablaModel;
    RenderTablaVisitas renderer;
    JFrame MUsker;
    ControladorVisitas controlador;

    public PanelMostrarVisitas(ControladorPantallaPrincipal controladorPantallaPrincipal, JFrame MUsker , ControladorVisitas controlador){
        this.setLayout(new BorderLayout(0,0));
        this.MUsker=MUsker;
        this.controlador=controlador;
        this.listaAnimales=new ArrayList<>();
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
        JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.white));
        this.renderer=new RenderTablaVisitas();
        this.tablaModel=new ModeloTablaVisitas();
        this.colum=new ModeloColumnasTablaVisitas(renderer);

        tabla = new JTable(tablaModel, colum);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                Visita visita=tablaModel.getVisita(row);
                DialogoInfoVisita dialogoInfoAnimal=new DialogoInfoVisita(MUsker,"Visita: "+visita.getFecha().getTime(),false, visita, controlador);
            }
        });
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.setViewportView(tabla);

        return panel;
    }

    private JToolBar getToolBar() {
        JToolBar barraBotones = new JToolBar();

        double tamanopantalla= Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double tamanoseparador=(tamanopantalla/2)-40;
        JLabel pagina=new JLabel("Visitas");
        pagina.setForeground(COLORLETRA);
        barraBotones.add(pagina);
        barraBotones.addSeparator(new Dimension((int) tamanoseparador,0));
        barraBotones.add(crearBoton("MUskerBarra","MUskerBarra"), BorderLayout.CENTER);
        barraBotones.addSeparator(new Dimension((int) tamanoseparador-100,0));
        barraBotones.add(crearBoton("mas","masVisitas"));
        barraBotones.setBackground(COLORTOOLBAR);
        return barraBotones;
    }
    private JButton crearBoton(String str, String str2) {
        JButton boton =new JButton(new ImageIcon("images/"+str+".png"));
        boton.setActionCommand(str2);
        boton.addActionListener(controladorPantallaPrincipal);
        boton.setBackground(COLORTOOLBAR);
        return boton;
    }
}
