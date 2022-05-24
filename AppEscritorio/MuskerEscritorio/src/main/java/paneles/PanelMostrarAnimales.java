package paneles;

import controladores.ControladorAnimales;
import controladores.ControladorPantallaPrincipal;
import dialogo.DialogoInfoAnimal;
import elementos.Animal;
import elementos.Visita;
import modelotablas.ModeloColumnasTablaAnimales;
import modelotablas.ModeloTablaAnimales;
import renderertablas.RenderTablaAnimales;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelMostrarAnimales extends JPanel  {
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORTOOLBAR = new Color(118, 185, 71);
    private static final Color COLORLETRA = new Color(47, 82, 51);
    ControladorPantallaPrincipal controladorPantallaPrincipal;
    ControladorAnimales controlador;
    JFrame MUsker;
    List<Visita> listaAnimales;
    JTable tabla;
    ModeloColumnasTablaAnimales colum;
    ModeloTablaAnimales tablaModel;
    RenderTablaAnimales renderer;

    public PanelMostrarAnimales(ControladorPantallaPrincipal controladorPantallaPrincipal, JFrame MUsker, ControladorAnimales controlador){
        this.setLayout(new BorderLayout(0,0));
        this.MUsker=MUsker;
        this.listaAnimales=new ArrayList<>();
        this.controlador=controlador;
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

        this.renderer=new RenderTablaAnimales();
        this.tablaModel=new ModeloTablaAnimales();
        this.tablaModel.setAnimales(controlador.getAnimales());
        this.colum=new ModeloColumnasTablaAnimales(renderer);
        tabla = new JTable(tablaModel, colum);
        tabla.setBackground(Color.white);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                Animal animal=tablaModel.getAnimal(row);
                DialogoInfoAnimal dialogoInfoAnimal=new DialogoInfoAnimal(MUsker,animal.getEspecie().getDescripcion(),false, animal, controlador);
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
        barraBotones.add(crearBoton("MUskerBarra", "MUskerBarra"), BorderLayout.CENTER);
        barraBotones.addSeparator(new Dimension((int) tamanoseparador-100,0));
        barraBotones.add(crearBoton("mas","masAnimales"));
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
