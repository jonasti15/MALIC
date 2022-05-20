package dialogo;

import elementos.Avistamiento;

import javax.swing.*;
import java.awt.*;

public class DialogoInfoAvistamiento extends JDialog {
    public final static int DEFAULT_WIDTH = 900;
    public final static int DEFAULT_HEIGHT = 700;
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);

    Avistamiento alerta;

    public DialogoInfoAvistamiento(JFrame ventana, String titulo, boolean modo, Avistamiento alerta){
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
        panel.add(anadirDato("ID del animal: "+alerta.getAvistamiento_id()));
        panel.add(anadirDato("Fecha: "+alerta.getFecha().toLocalDate().getYear()+"-"+alerta.getFecha().toLocalDate().getMonthValue()+"-"+alerta.getFecha().toLocalDate().getDayOfMonth()));
        panel.add(anadirDato("Descripcion: "+alerta.getDescripcion()));
        panel.add(anadirDato("Especie: "+alerta.getEspecie().getDescripcion()));
        panel.add(anadirDato("Localizacion: "+alerta.getLocalizacion()));
        panel.add(anadirDato("Usuario: "+alerta.getUser().getNombre()));
        return panel;
    }

    private Component anadirDato(String nombre) {
        JLabel label=new JLabel(nombre);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(COLORLETRA);
        return label;
    }



}
