package dialogo;

import controladores.ControladorAnimales;
import elementos.Animal;
import elementos.Estancia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoInfoAnimal extends JDialog implements ActionListener {
    public final static int DEFAULT_WIDTH = 900;
    public final static int DEFAULT_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4);
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);

    Animal animal;
    ControladorAnimales controlador;

    public DialogoInfoAnimal(JFrame ventana, String titulo, boolean modo,Animal animal, ControladorAnimales controlador ){
        super(ventana, titulo, modo);
        this.animal=animal;
        this.controlador=controlador;
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
        panel.add(crearPanelBotones(),BorderLayout.SOUTH);
        panel.add(crearPanelInfo(),BorderLayout.CENTER);

        return panel;
    }

    private Component crearPanelInfo() {
        JPanel panel =new JPanel(new GridLayout(7,1));
        panel.setBackground(COLORFONDO);
        panel.add(anadirDato("ID: "+animal.getAnimalId()));
        panel.add(anadirDato("Especie: "+animal.getEspecie().getDescripcion()));
        panel.add(anadirDato("Clase: "+animal.getEspecie().getClase().getDescripcion()));
        panel.add(anadirDato("Estado: "+animal.getEstado().getDescripcion()));
        panel.add(anadirDato("Recinto: "+animal.getRecinto_id().getDescripcion()));
        Estancia estancia=controlador.getEstanciaByAnimalId(animal.getAnimalId());
        if(estancia!=null){
            panel.add(anadirDato("Motivo de entrada: "+estancia.getMotivo_entrada()));
            panel.add(anadirDato("Fecha de entrada: "+estancia.getFechaString()));
        }


        return panel;
    }

    private Component anadirDato(String nombre) {
        JLabel label=new JLabel(nombre);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(COLORLETRA);
        return label;
    }

    private Component crearPanelBotones() {
        JPanel panel =new JPanel();
        panel.add(crearBoton("editar"));
        panel.add(crearBoton("liberado"));
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

    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch(accion) {
            case "editar":
                controlador.editar(this.animal);
                this.dispose();
                break;

            case "liberado":

                controlador.eliminar(this.animal);
                this.dispose();
                break;
        }
    }
}
