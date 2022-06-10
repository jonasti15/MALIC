package paneles;

import controladores.ControladorPantallaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {
    ControladorPantallaPrincipal controladorPantallaPrincipal;
    private static final Color COLORFONDO = new Color(177,216,183);

    public PanelMenu(ControladorPantallaPrincipal controladorPantallaPrincipal){
        this.setBackground(COLORFONDO);
        this.controladorPantallaPrincipal=controladorPantallaPrincipal;
        JLabel label = new JLabel("MUsker");
        label.setFont(new Font("Times new Roman + Trutype font", Font.PLAIN, 36));
        label.setPreferredSize(new Dimension(200, 40));
        label.setForeground(new Color(60, 112, 112));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(crearpanelbotones());
        this.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
    }

    private Component crearpanelbotones() {
        JPanel panel = new JPanel(new GridLayout(4,1,30,30));
        panel.setBackground(COLORFONDO);
        JLabel logo =new JLabel(new ImageIcon("images/MUskerGrande.png"));
        panel.add(logo);
        panel.add(crearBoton("mostrarAnimales"));
        panel.add(crearBoton("alertas"));
        panel.add(crearBoton("mostrarVisitas"));


        return panel;
    }

    private Component crearBoton(String nombre) {
        ImageIcon mostrarAnimalesImg=new ImageIcon("images/"+nombre+".png");
        JButton boton = new JButton(mostrarAnimalesImg);
        boton.setActionCommand(nombre);
        boton.addActionListener(controladorPantallaPrincipal);
        boton.setPreferredSize(new Dimension(mostrarAnimalesImg.getIconWidth(),mostrarAnimalesImg.getIconHeight()));
        boton.setHorizontalAlignment(JLabel.CENTER);

        return boton;
    }
}
