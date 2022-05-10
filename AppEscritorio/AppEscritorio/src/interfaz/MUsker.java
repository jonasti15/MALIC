package interfaz;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controladores.ControladorMUsker;
import dialogo.DialogoLogin;
import elementos.ListaIncidencias;
import elementos.Permisos;
import elementos.User;
import paneles.PanelPrincipal;

public class MUsker extends JFrame implements WindowListener{

	public final static int DEFAULT_WIDTH = 1000;
	public final static int DEFAULT_HEIGHT = 600;
	public static Connection conn = null;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	public static String userDB;
	public static String pass;
	private static final String url = "jdbc:mysql://localhost:3306/musker";
	JScrollPane pDisplay;
	
	User user;
	
	public static JButton botonAlerta;
	
	public static ListaIncidencias listaIncidencias;
	
	ControladorMUsker controlador;
	
	public MUsker() {
		super("MUsker");
		DialogoLogin login = new DialogoLogin(this, "MUsker Login", true);
		user = login.getUserLoged();
		controlador = new ControladorMUsker(this);
		
		pDisplay = new JScrollPane();
		pDisplay.setViewportView(new PanelPrincipal());
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
	
	public static void connectToDB() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, MUsker.userDB, MUsker.pass);
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
