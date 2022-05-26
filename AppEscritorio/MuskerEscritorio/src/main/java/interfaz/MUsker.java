package interfaz;

import RabbitMQ.HiloConsumidor;
import RabbitMQ.HiloConsumidorAvistamientos;
import controladores.ControladorAlertas;
import dialogo.DialogoLogin;
import elementos.User;
import paneles.PanelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MUsker extends JFrame implements WindowListener{
	public static String access_token = "";
	public static String refresh_token = "";

	private static final Color COLORFONDO = new Color(177,216,183);
	private static final Color COLORTOOLBAR = new Color(118, 185, 71);
	private static final Color COLORLETRA = new Color(47, 82, 51);
	public final static int DEFAULT_WIDTH = 1000;
	public final static int DEFAULT_HEIGHT = 600;
	JScrollPane pDisplay;
	JPanel alertas;
	User user;
	JLabel labelAlertas;
	ControladorAlertas controladorAlertas;
	boolean  alertado;
	public static JButton botonAlerta;




	public MUsker() {
		super("MUsker");
		DialogoLogin login = new DialogoLogin(this, "MUsker Login", true);
		this.alertado=false;
		user = login.getUserLoged();
		this.controladorAlertas=new ControladorAlertas(this);
		pDisplay = new JScrollPane();
		pDisplay.setViewportView(crearPanel());
		pDisplay.setBorder(null);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int)toolkit.getScreenSize().getWidth();
		int height = (int)toolkit.getScreenSize().getHeight();
		
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLocation(width / 2 - DEFAULT_WIDTH / 2, height / 2 - DEFAULT_HEIGHT / 2);

		
		if(user == null) {
			this.dispose();
		}else {
			this.setContentPane(pDisplay);
			this.setVisible(true);
			this.setBackground(new Color(177,216,183));
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			HiloConsumidor consumidor=new HiloConsumidor(controladorAlertas);
			HiloConsumidorAvistamientos consumidorAvistamientos=new HiloConsumidorAvistamientos(controladorAlertas);
			consumidor.start();
			consumidorAvistamientos.start();
		}
	}



	public JLabel getLabelAlertas() {
		return labelAlertas;
	}

	public void setLabelAlertas(JLabel labelAlertas) {
		this.labelAlertas = labelAlertas;
	}

	public boolean isAlertado() {
		return alertado;
	}

	public void setAlertado(boolean alertado) {
		this.alertado = alertado;
	}

	private Component crearPanel() {
		JPanel panel=new JPanel(new BorderLayout(0,0));
		JScrollPane pPrincipal= new PanelPrincipal(this, controladorAlertas);
		alertas=new JPanel(new BorderLayout(20,0));
		alertas.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		alertas.add(crearLabelAlertas());
		panel.add(pPrincipal, BorderLayout.CENTER);
		panel.add(alertas, BorderLayout.NORTH);
		alertas.setBackground(COLORTOOLBAR);
		return panel;
	}

	private Component crearLabelAlertas() {
		labelAlertas=new JLabel("No hay alertas");
		labelAlertas.setFont(new Font("Serif", Font.BOLD, 20));
		labelAlertas.setHorizontalAlignment(SwingConstants.CENTER);
		labelAlertas.setForeground(COLORLETRA);

		return labelAlertas;
	}
	synchronized public void alertar(){
		alertado=true;
		alertas.setBackground(Color.red);
		labelAlertas.setForeground(Color.white);
		labelAlertas.setText("Alerta");
	}
	 synchronized public void desAlertar(){
		alertado=false;
		alertas.setBackground(COLORTOOLBAR);
		labelAlertas.setForeground(COLORLETRA);
		labelAlertas.setText("No hay alertas");
	}
	
	public JScrollPane getpDisplay() {
		return pDisplay;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		MUsker MUsker = new MUsker();
	}
	
}
