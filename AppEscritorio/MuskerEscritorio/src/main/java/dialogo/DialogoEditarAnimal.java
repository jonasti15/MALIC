package dialogo;

import controladores.ControladorAnimales;
import elementos.Animal;
import elementos.Especie;
import elementos.Recinto;
import elementos.TipoEstado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoEditarAnimal extends JDialog implements ActionListener {

    public final static int DEFAULT_WIDTH = 900;
    public final static int DEFAULT_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4);
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);
    ControladorAnimales controlador;
    Animal animal;
    public JComboBox txEspecie, txEstado, txRecinto;

    public DialogoEditarAnimal(JFrame ventana, String titulo, boolean modo, ControladorAnimales controlador, Animal animal){
        super(ventana, titulo, modo);
        this.controlador=controlador;
        this.animal=animal;
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
        panel.add(crearPanelBotones(),BorderLayout.SOUTH);
        panel.add(crearPanelInfo(),BorderLayout.CENTER);
        panelScroll.setViewportView(panel);

        return panelScroll;
    }

    private Component crearPanelInfo() {
        JPanel panel =new JPanel(new GridLayout(4,1,30,90));
        panel.setBackground(COLORFONDO);

        panel.add(anadirDato("Especie: "));
        Especie[] listaEspecies=controlador.getListaEspecies();
        txEspecie=new JComboBox(listaEspecies);
        for(int i =0;i<listaEspecies.length;i++){
            if(listaEspecies[i].getEspecieId()==animal.getEspecie().getEspecieId()){
                txEspecie.setSelectedIndex(i);
            }
        }
        panel.add(txEspecie);

        panel.add(anadirDato("Estado: "));
        TipoEstado[] listaEstados=controlador.getListaEstados();

        txEstado=new JComboBox(listaEstados);
        for(int i =0;i<listaEstados.length;i++){
            if(listaEstados[i].getEstado_id()==animal.getEstado().getEstado_id()){
                txEstado.setSelectedIndex(i);
            }
        }

        panel.add(txEstado);

        panel.add(anadirDato("Recinto: "));
        Recinto[] listaRecinto=controlador.getListaRecintos();
        txRecinto=new JComboBox(listaRecinto);
        for(int i =0;i<listaRecinto.length;i++){
            if(listaRecinto[i].getRecinto_id()==animal.getRecinto_id().getRecinto_id()){
                txRecinto.setSelectedIndex(i);
            }
        }
        panel.add(txRecinto);

        panel.setBorder(BorderFactory.createEmptyBorder(30,60,30,60));
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
        panel.add(crearBoton("editar"));
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


    public Animal getAnimal() {
        return animal;
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
        switch(accion) {
            case "editar":

                Animal animaleditado=controlador.editarAnimal(this.getAnimal().getAnimalId(),(Especie)this.getTxEspecie().getSelectedItem(), (TipoEstado)this.getTxEstado().getSelectedItem(), (Recinto)this.getTxRecinto().getSelectedItem());
                if (animaleditado!=null){
                    this.dispose();
                }

                break;
            case "cancelar":
                this.dispose();

                break;

        }
    }
}
