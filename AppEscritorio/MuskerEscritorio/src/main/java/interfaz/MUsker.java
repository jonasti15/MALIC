package interfaz;

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
	private static final Color COLORFONDO = new Color(177,216,183);
	private static final Color COLORTOOLBAR = new Color(118, 185, 71);
	private static final Color COLORLETRA = new Color(47, 82, 51);
	public final static int DEFAULT_WIDTH = 1000;
	public final static int DEFAULT_HEIGHT = 600;
	public static Connection conn = null;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	public static String userDB;
	public static String pass;
	private static final String url = "jdbc:mysql://localhost:3306/musker";
	JScrollPane pDisplay;
	JPanel alertas;
	User user;
	JLabel labelAlertas;
	int numAlertas=0;
	public static JButton botonAlerta;




	public MUsker() {
		super("MUsker");
		DialogoLogin login = new DialogoLogin(this, "MUsker Login", true);
		user = login.getUserLoged();
		
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
		}
	}

	private Component crearPanel() {
		JPanel panel=new JPanel(new BorderLayout(0,0));
		JScrollPane pPrincipal= new PanelPrincipal(this);
		alertas=new JPanel(new BorderLayout(20,0));
		alertas.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		alertas.add(crearLabelAlertas());
		panel.add(pPrincipal, BorderLayout.CENTER);
		panel.add(alertas, BorderLayout.NORTH);
		alertas.setBackground(COLORTOOLBAR);
		return panel;
	}

	private Component crearLabelAlertas() {
		labelAlertas=new JLabel(numAlertas+" Alertas");
		labelAlertas.setFont(new Font("Serif", Font.BOLD, 20));
		labelAlertas.setHorizontalAlignment(SwingConstants.CENTER);
		labelAlertas.setForeground(COLORLETRA);
		return labelAlertas;
	}
	void alertar(){
		labelAlertas.setForeground(Color.white);
		alertas.setBackground(Color.red);
	}
	void desAlertar(){
		labelAlertas.setForeground(COLORLETRA);
		alertas.setBackground(COLORTOOLBAR);
	}


	public static void connectToDB() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "worker", "worker");
			if(conn != null) {
				System.out.println("Conexion establecida");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error al conectar " + e);
		}
	}
	
	public void disconnectFromDB() {
		conn = null;
		if(conn == null) {
			System.out.println("Conexion terminada");
		}
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
		this.disconnectFromDB();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.disconnectFromDB();
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
