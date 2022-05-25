package dialogo;

import controladores.ControladorAnimales;
import elementos.Animal;
import elementos.Especie;
import elementos.Recinto;
import elementos.TipoEstado;
import org.apache.tools.ant.util.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class DialogoAnadirAnimal extends JDialog implements ActionListener {
    private static String BASE_PATH = "C:/Users/ibail/OneDrive/Escritorio/LANAK/3 MAILA/EBAL2/PBL6/MALIC/AppEscritorio/MuskerEscritorio";
    public final static int DEFAULT_WIDTH = 1000;
    public final static int DEFAULT_HEIGHT = 900;
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);

    ControladorAnimales controlador;
    JComboBox txEspecie, txEstado, txRecinto;
    JTextField txMotivo;
    JLabel txPath;
    File f;

    public DialogoAnadirAnimal(JFrame ventana, String titulo, boolean modo, ControladorAnimales controlador){
        super(ventana, titulo, modo);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = (int) toolkit.getScreenSize().getWidth();
        int height = (int) toolkit.getScreenSize().getHeight();
        this.controlador=controlador;
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
        JPanel panel =new JPanel(new GridLayout(5,2,30,90));
        panel.setBackground(COLORFONDO);

        panel.add(anadirDato("Especie: "));
        Especie[] listaEspecies=controlador.getListaEspecies();
        txEspecie=new JComboBox(listaEspecies);
        panel.add(txEspecie);

        panel.add(anadirDato("Estado: "));
        TipoEstado[] listaEstados=controlador.getListaEstados();
        txEstado=new JComboBox(listaEstados);
        panel.add(txEstado);

        panel.add(anadirDato("Recinto: "));
        Recinto[] listaRecinto=controlador.getListaRecintos();
        txRecinto=new JComboBox(listaRecinto);
        panel.add(txRecinto);

        panel.add(anadirDato("Motivo: "));
        txMotivo=new JTextField("");
        panel.add(txMotivo);

        panel.add(anadirDato("Cargar foto: "));

        panel.add(crearPanelBotonSubir());

        panel.setBorder(BorderFactory.createEmptyBorder(30,60,30,60));
        return panel;
    }

    private Component crearPanelBotonSubir() {
        JPanel panel =new JPanel();
        txPath=new JLabel("/images/foto1.png");
        JButton boton=new JButton("Cargar Imagen");
        boton.setActionCommand("cargar");
        boton.addActionListener(this);
        panel.add(boton);
        panel.add(txPath);
        panel.setBackground(COLORFONDO);
        return panel;
    }

    private Component anadirDato(String nombre) {
        JLabel label=new JLabel(nombre);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setForeground(COLORLETRA);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private Component crearPanelBotones() {
        JPanel panel =new JPanel();
        panel.add(crearBoton("anadir"));
        panel.add(crearBoton("cancelar"));
        panel.setBorder(BorderFactory.createEmptyBorder(60,20,0,20));
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

    public JComboBox getTxEspecie() {
        return txEspecie;
    }

    public JComboBox getTxEstado() {
        return txEstado;
    }

    public JComboBox getTxRecinto() {
        return txRecinto;
    }



    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch (accion) {
            case "cancelar":
                this.dispose();
                break;
            case "cargar":

                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                f = chooser.getSelectedFile();
                if (f == null) {
                    return;
                }else{
                    String filename = f.getAbsolutePath();
                    txPath.setText(filename);
                }

                break;
            case "anadir":

                Animal animalCreado = controlador.anadirAnimal((Especie) this.getTxEspecie().getSelectedItem(), (TipoEstado) this.getTxEstado().getSelectedItem(), (Recinto) this.getTxRecinto().getSelectedItem());
                Date hoy=new Date();
                java.sql.Date fecha=new java.sql.Date(hoy.getTime());
                controlador.anadirEstancia(txMotivo.getText(), fecha);
                try {
                    File nuevaFoto=new File(BASE_PATH + "/images/animals/"+animalCreado.getAnimalId() + ".png");
                    Files.copy(f.toPath(), nuevaFoto.toPath());
                    controlador.sendPhoto("/images/animals/"+animalCreado.getAnimalId() + ".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.dispose();
                break;


        }
    }
}
