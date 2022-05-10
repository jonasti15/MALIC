package dialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import basedatos.ReservasBD;
import controladores.ControladorDialogoReserva;
import elementos.Reserva;

public class DialogoReserva extends JDialog {
	public final static int DEFAULT_WIDTH = 500;
	public final static int DEFAULT_HEIGHT = 700;
	
	Reserva reserva;
	
	JTextField numHabi, fechaInit, fechaFin, numA, numN, numJ;
	
	ControladorDialogoReserva controlador;
	
	public DialogoReserva(JFrame ventana, Reserva reserva){
		super(ventana, "Modificar reserva", true);
		this.reserva = reserva;
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();
		
		this.setLocation(width/2 - DEFAULT_WIDTH/2, height/2 - DEFAULT_HEIGHT/2);
		this.setSize(new Dimension(DEFAULT_WIDTH, 700));
		
		inicializar();
		controlador = new ControladorDialogoReserva(this);
		this.setContentPane(crearPanelDialogo());
		this.setSize(new Dimension(400,300));
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void inicializar() {
		numHabi = new JTextField(String.valueOf(reserva.getNumHabitacion()));
		fechaInit = new JTextField(reserva.getFechaInicio());
		fechaFin = new JTextField(reserva.getFechaFinal());
		numA = new JTextField(String.valueOf(reserva.getNumA()));
		numN = new JTextField(String.valueOf(reserva.getNumN()));
		numJ = new JTextField(String.valueOf(reserva.getNumJ()));
	}
	
	private Container crearPanelDialogo() {
		JPanel panel = new JPanel();
		
		panel.add(crearPanelDatos(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel();
		JButton aceptar = new JButton(new ImageIcon("images/check.png"));
		JButton cancelar = new JButton(new ImageIcon("images/cancel.png"));
		
		aceptar.addActionListener(controlador);
		aceptar.setActionCommand("aceptar");
		
		cancelar.addActionListener(controlador);
		cancelar.setActionCommand("cancelar");
		
		panel.add(aceptar, BorderLayout.EAST);
		panel.add(cancelar, BorderLayout.WEST);
		
		return panel;
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(6,1));
		
		panel.add(crearPanelNumH());
		panel.add(crearPanelFechaInit());
		panel.add(crearPanelFechaFin());
		panel.add(crearPanelNumN());
		panel.add(crearPanelNumA());
		panel.add(crearPanelNumJ());
		
		return panel;
	}

	private Component crearPanelNumJ() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		JLabel label = new JLabel("Numero jubilados: ");
		
		panel.add(label);
		panel.add(numJ);
		
		return panel;
	}

	private Component crearPanelNumA() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		JLabel label = new JLabel("Numero adultos: ");
		
		panel.add(label);
		panel.add(numA);
		
		return panel;
	}

	private Component crearPanelNumN() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		JLabel label = new JLabel("Numero niños: ");
		
		panel.add(label);
		panel.add(numN);
		
		return panel;
	}

	private Component crearPanelFechaFin() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		JLabel label = new JLabel("Fecha fin: ");
		
		panel.add(label);
		panel.add(fechaFin);
		
		return panel;
	}

	private Component crearPanelFechaInit() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		JLabel label = new JLabel("Fecha inicio: ");
		
		panel.add(label);
		panel.add(fechaInit);
		
		return panel;
	}

	private Component crearPanelNumH() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		JLabel label = new JLabel("Numero habitación: ");
		
		panel.add(label);
		panel.add(numHabi);
		
		return panel;
	}

	public boolean comprobarReservaCamas(int numA, int numN, int numJ) {
		int camasDobles, camasIndividuales;
		
		ReservasBD reservasBd = new ReservasBD();
		reservasBd.getNumCamas(String.valueOf(reserva.getNumHabitacion()));
		camasDobles = reservasBd.getCamasDobles();
		camasIndividuales = reservasBd.getCamasIndividuales();
		
		if((numA + numJ + numN) > (camasIndividuales + camasDobles*2)) {
			return false;
		}
		
		return true;
	}

	public boolean comprobarReservaFecha(String fechaInit, String fechaFin) {
		List<Integer> listaHabitacionID;
		boolean encontrado = false;
		int i = 0;
		
		ReservasBD reservasBd = new ReservasBD();
		listaHabitacionID = reservasBd.getReservasFecha(String.valueOf(reserva.getId()), fechaInit, fechaFin);
		
		while(i < listaHabitacionID.size() && !encontrado) {
			if(reserva.getNumHabitacion() == listaHabitacionID.get(i)) {
				encontrado = true;
			}
			i++;
		}
		
		return encontrado;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public Reserva getReserva() {
		return reserva;
	}

	public JTextField getNumHabi() {
		return numHabi;
	}

	public JTextField getFechaInit() {
		return fechaInit;
	}

	public JTextField getFechaFin() {
		return fechaFin;
	}

	public JTextField getNumA() {
		return numA;
	}

	public JTextField getNumN() {
		return numN;
	}

	public JTextField getNumJ() {
		return numJ;
	}
	
}
