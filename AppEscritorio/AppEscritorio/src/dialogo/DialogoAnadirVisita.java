package dialogo;

import controladores.ControladorVisitas;
import elementos.User;
import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class DialogoAnadirVisita extends JDialog implements ActionListener {

    public final static int DEFAULT_WIDTH = 900;
    public final static int DEFAULT_HEIGHT = 700;
    private static final Color COLORFONDO = new Color(177,216,183);
    private static final Color COLORLETRA = new Color(47, 82, 51);
    ControladorVisitas controlador;
    public JComboBox txUser;
    public JTextField fecha;
    JDatePickerImpl datePicker;


    public DialogoAnadirVisita(JFrame ventana, String titulo, boolean modo, ControladorVisitas controlador){
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
        JPanel panel =new JPanel(new GridLayout(2,1));
        panel.setBackground(COLORFONDO);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        panel.add(datePanel);
        JPanel panelUser=new JPanel(new GridLayout(1,2,50,50));
        panelUser.add(anadirDato("Guia: "));
        User[] listaUsers=controlador.getListaUsuarios();
        txUser=new JComboBox(listaUsers);
        panelUser.add(txUser);
        panelUser.setBorder(BorderFactory.createEmptyBorder(100,0,100,0));
        panelUser.setBackground(COLORFONDO);
        panel.add(panelUser);
        panel.setBorder(BorderFactory.createEmptyBorder(30,60,30,60));
        return panel;
    }

    private Component anadirDato(String nombre) {
        JLabel label=new JLabel(nombre);
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


    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        switch (accion) {
            case "cancelar":
                this.dispose();
                break;
            case "anadir":
                System.out.println(datePicker.getModel().getYear());
                Calendar c1 = Calendar.getInstance();
                c1.set(Calendar.MONTH, datePicker.getModel().getMonth());
                c1.set(Calendar.DATE, datePicker.getModel().getDay());
                c1.set(Calendar.YEAR, datePicker.getModel().getYear());
                Date date=c1.getTime();
                java.sql.Date datesql=new java.sql.Date(date.getTime());
                controlador.anadirVisita(datesql, (User) this.txUser.getSelectedItem());
                this.dispose();
                break;

        }
    }
}
