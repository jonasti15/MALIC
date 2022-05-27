package dialogo;

import controladores.ControladorAlertas;
import elementos.Alerta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoInfoAlerta extends JDialog implements ActionListener {
    public final static int DEFAULT_WIDTH = 900;
    public final static int DEFAULT_HEIGHT = 700;
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);

    Alerta alerta;
    ControladorAlertas controladorAlertas;
    public DialogoInfoAlerta(JFrame ventana, String titulo, boolean modo, Alerta alerta, ControladorAlertas controladorAlertas){
        super(ventana, titulo, modo);
        this.alerta=alerta;
        this.controladorAlertas=controladorAlertas;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = (int) toolkit.getScreenSize().getWidth();
        int height = (int) toolkit.getScreenSize().getHeight();

        this.setLocation(width/2 - DEFAULT_WIDTH/2, height/2 - DEFAULT_HEIGHT/2);
        this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setContentPane(crearPanel());
        this.setVisible(true);

    }

    private Container crearPanel() {
        JScrollPane panelScroll=new JScrollPane();
        panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel panel =new JPanel(new BorderLayout(0,0));
        panel.add(crearPanelBoton(),BorderLayout.SOUTH);
        panel.add(crearPanelInfo(),BorderLayout.CENTER);
        panelScroll.setViewportView(panel);

        return panelScroll;
    }

    private Component crearPanelBoton() {
        JPanel panel =new JPanel();
        panel.add(crearBoton("atendido"));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.setBackground(COLORFONDO);
        return panel;
    }
    private Component crearBoton(String nombre) {
        ImageIcon mostrarAnimalesImg=new ImageIcon("images/"+nombre+".png");
        JButton boton = new JButton(mostrarAnimalesImg);
        boton.setActionCommand(nombre);
        boton.addActionListener(this);
        boton.setPreferredSize(new Dimension(mostrarAnimalesImg.getIconWidth(),mostrarAnimalesImg.getIconHeight()));
        boton.setHorizontalAlignment(JLabel.CENTER);

        return boton;
    }

    private Component crearPanelInfo() {
        JPanel panel =new JPanel(new GridLayout(5,1));
        panel.setBackground(COLORFONDO);
        panel.add(anadirDato("ID del animal: "+alerta.getAnimalId()));
        panel.add(anadirDato("Especie: "+alerta.getEspecie().getDescripcion()));
        panel.add(anadirDato("Estado anterior: "+alerta.getEstado()));
        panel.add(anadirDato("Estado actual: "+alerta.getEstadoNuevo()));
        panel.add(anadirDato("Recinto: "+alerta.getRecinto_id().getRecinto_id()+" "+alerta.getRecinto_id().getDescripcion()));

        return panel;
    }

    private Component anadirDato(String nombre) {
        JLabel label=new JLabel(nombre);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(COLORLETRA);
        return label;
    }



    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch(accion) {
            case "atendido":
                controladorAlertas.quitarAlerta(alerta);
                this.dispose();
                break;

        }
    }

}
