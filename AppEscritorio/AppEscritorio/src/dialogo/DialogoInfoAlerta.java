package dialogo;

import controladores.ControladorVisitas;
import elementos.Alerta;
import elementos.Visita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoInfoAlerta extends JDialog {
    public final static int DEFAULT_WIDTH = 900;
    public final static int DEFAULT_HEIGHT = 700;
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);

    Alerta alerta;

    public DialogoInfoAlerta(JFrame ventana, String titulo, boolean modo, Alerta alerta){
        super(ventana, titulo, modo);
        this.alerta=alerta;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = (int) toolkit.getScreenSize().getWidth();
        int height = (int) toolkit.getScreenSize().getHeight();

        this.setLocation(width/2 - DEFAULT_WIDTH/2, height/2 - DEFAULT_HEIGHT/2);
        this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setContentPane(crearPanel());
        this.setVisible(true);

    }

    private Container crearPanel() {
        JPanel panel =new JPanel(new BorderLayout(0,0));
        panel.add(crearPanelInfo(),BorderLayout.CENTER);

        return panel;
    }

    private Component crearPanelInfo() {
        JPanel panel =new JPanel(new GridLayout(4,1));
        panel.setBackground(COLORFONDO);
        panel.add(anadirDato("ID del animal: "+alerta.getAnimal_id()));
        panel.add(anadirDato("Especie: "+alerta.getEspecie().getDescripcion()));
        panel.add(anadirDato("Estado: "+alerta.getEstado()));
        panel.add(anadirDato("Recinto: "+alerta.getRecinto_id().getRecinto_id()+" "+alerta.getRecinto_id().getDescripcion()));

        return panel;
    }

    private Component anadirDato(String nombre) {
        JLabel label=new JLabel(nombre);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(COLORLETRA);
        return label;
    }



}
