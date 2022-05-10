package dialogo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladores.ControladorDialogoLogin;
import elementos.User;
import gestionpantallas.RoundedBorder;
import paneles.PanelLoginForm;

public class DialogoLogin extends JDialog {

	private static final Color COLOROSCURO = new Color(118,185,71);
	public static Color COLORFONDO = new Color(177,216,183);
	public final static int DEFAULT_WIDTH = 500;
	public final static int DEFAULT_HEIGHT = 700;
	
	ControladorDialogoLogin controlador;
	
	User user;

	JFrame ventana;
	JTextField usuario;
	JPasswordField password;
	
	JCheckBox mostrar = new JCheckBox();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	public DialogoLogin(JFrame ventana, String titulo, boolean modo) {
		super(ventana, titulo, modo);
		
		this.ventana = ventana;
		controlador = new ControladorDialogoLogin(this);
		this.setContentPane(crearPanel());

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();
		
		this.setLocation(width/2 - DEFAULT_WIDTH/2, height/2 - DEFAULT_HEIGHT/2);
		this.setSize(new Dimension(DEFAULT_WIDTH, 700));
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private Container crearPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(crearPanelRellenoFormulario());

		panel.setBackground(COLORFONDO);
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,120,100));
		return panel;
	}

	private Container crearBotonConfirmar() {
		JPanel panel = new JPanel(new GridLayout(6, 1, 0, 0));
		panel.setBackground(Color.white);

		ImageIcon mostrarAnimalesImg=new ImageIcon("images/iniciarSesion.png");
		JButton boton = new JButton(mostrarAnimalesImg);
		boton.setPreferredSize(new Dimension(mostrarAnimalesImg.getIconWidth(),mostrarAnimalesImg.getIconHeight()));
		boton.setHorizontalAlignment(JLabel.CENTER);
		boton.setBorder(new RoundedBorder(40));
		boton.addActionListener(controlador);
		boton.setActionCommand("confirmar");
		
		JPanel pBoton = new JPanel();
		pBoton.setBackground(Color.white);
		pBoton.add(boton);
		panel.add(pBoton);
		
		return panel;
	}

	private Component crearPanelRellenoFormulario() {
		JPanel panel = new PanelLoginForm();
		usuario = new JTextField(20);
		password = new JPasswordField(15);

		panel.add(usuario);
		panel.add(password);
		JPanel panelGrid = new JPanel(new GridLayout(3,1,0,0));
		panelGrid.setBackground(Color.white);
		panelGrid.add(new JLabel(new ImageIcon("images/MUsker.png")));
		panelGrid.add(crearTextField(usuario, "Usuario"));
		panelGrid.add(crearTextField(password, "Contraseña"));

		panel.add(panelGrid);
		panel.add(crearBotonConfirmar());
		panel.setBackground(Color.white);


		return panel;
	}

	private Component crearTextField(JTextField text, String titulo) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		
		if(text instanceof JPasswordField) {
			JPanel panel2 = new JPanel();
			panel2.setBackground(Color.white);
			
			this.mostrar = new JCheckBox();
			this.mostrar.setIcon(new ImageIcon(escalarFotoFondoBlancoPng("iconos/hidePassword.png",200,200,25,25)));
			this.mostrar.setSelectedIcon(new ImageIcon(escalarFotoFondoBlancoPng("iconos/showPassword.png",200,200,25,25)));
			this.mostrar.setBackground(Color.white);
			this.mostrar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(0, 3, 0, 3)));
			this.mostrar.addActionListener(controlador);
			this.mostrar.setActionCommand("mostrar");
			
			text.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOROSCURO),BorderFactory.createEmptyBorder(8, 8, 8, 8)));
			panel2.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(10, 5, 5, 5),
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOROSCURO, 2), titulo),
								BorderFactory.createEmptyBorder(5, 5, 10, 5))));
			panel2.add(text);
			panel2.add(mostrar);
			panel.add(panel2);
		}
		else {
			text.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createCompoundBorder(
							BorderFactory.createEmptyBorder(5, 5, 10, 5),
							BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOROSCURO, 2), titulo)),
					BorderFactory.createCompoundBorder(
							BorderFactory.createEmptyBorder(15, 10, 15, 10),
							BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOROSCURO),BorderFactory.createEmptyBorder(8, 8, 8, 8)))));
			panel.add(text);
		}
		return panel;
	}


	private BufferedImage escalarFotoFondoBlancoPng(String path, int width, int height, int newWidth, int newHeight) {
		File imgFile = new File(path);
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try {
		    buffImg = ImageIO.read(imgFile);
		}
		catch (IOException e) { }
		BufferedImage copy = new BufferedImage(buffImg.getWidth(), buffImg.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = copy.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
		g2d.drawImage(buffImg, 0, 0, null);
		g2d.dispose();

		return resizeImage(copy, newWidth,newHeight);
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}



	
	public User getUserLoged() {
		return user;
	}

	public JTextField getUsuario() {
		return usuario;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public JCheckBox getMostrar() {
		return mostrar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
