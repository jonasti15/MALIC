package dialogo;

import controladores.ControladorVisitas;
import elementos.User;
import elementos.Visita;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

public class DialogoEditarVisita extends JDialog implements ActionListener {

    public final static int DEFAULT_WIDTH = 900;
    public final static int DEFAULT_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4);
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);
    ControladorVisitas controlador;
    Visita visita;
    public JComboBox txUser;
    public JTextField fecha;
    JDatePickerImpl datePicker;
    JTextField desc;
    public DialogoEditarVisita(JFrame ventana, String titulo, boolean modo, ControladorVisitas controlador, Visita visita){
        super(ventana, titulo, modo);
        this.controlador=controlador;
        this.visita=visita;
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
        JPanel panel =new JPanel(new GridLayout(2,1));
        panel.setBackground(COLORFONDO);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        panel.add(datePanel);
        JPanel panelUser=new JPanel(new GridLayout(2,4,50,50));
        panelUser.add(anadirDato("Guia: "));
        User[] listaUsers=controlador.getListaUsuarios();
        txUser=new JComboBox(listaUsers);
        panelUser.add(txUser);
        panelUser.add(anadirDato("Descripcion: "));
        desc=new JTextField(visita.getDescripcion());
        panelUser.add(desc);
        panelUser.setBorder(BorderFactory.createEmptyBorder(100,0,100,0));
        panelUser.setBackground(COLORFONDO);
        panel.add(panelUser);
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




    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch(accion) {
            case "editar":
                Calendar c1 = Calendar.getInstance();
                c1.set(Calendar.MONTH, datePicker.getModel().getMonth());
                c1.set(Calendar.DATE, datePicker.getModel().getDay());
                c1.set(Calendar.YEAR, datePicker.getModel().getYear());
                java.util.Date date=c1.getTime();
                Date datesql=new Date(date.getTime());

                if(desc.getText().equals("")||desc==null){
                    JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos!","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                java.util.Date hoy=new java.util.Date();
                Date hoySql=new Date(hoy.getTime());
                if(date.before(hoySql)){
                    JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior a la de hoy!","Error", JOptionPane.ERROR_MESSAGE);
                }
                controlador.editarVisita(this.visita.getVisitaId(),datesql, (User) this.txUser.getSelectedItem(), desc.getText());
                this.dispose();
                break;
            case "cancelar":
                this.dispose();

                break;

        }
    }
}
