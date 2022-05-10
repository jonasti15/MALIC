package dialogo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.ControladorDialogoIncidencia;
import elementos.Incidencia;

public class DialogoIncidencia extends JDialog {
	public final static int DEFAULT_WIDTH = 500;
	public final static int DEFAULT_HEIGHT = 700;
	
	Incidencia incidencia;
	
	JTextField descripcion, trabajador;
	
	ControladorDialogoIncidencia controlador;
	
	public DialogoIncidencia(JFrame ventana, Incidencia incidencia) {
		super(ventana, "Incidencia", true);
		this.incidencia = incidencia;
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();
		
		this.setLocation(width/2 - DEFAULT_WIDTH/2, height/2 - DEFAULT_HEIGHT/2);
		this.setSize(new Dimension(DEFAULT_WIDTH, 700));
		
		controlador = new ControladorDialogoIncidencia(this);
		this.setContentPane(crearPanelDialogo());
		this.setSize(new Dimension(400, 300));
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private Container crearPanelDialogo() {
		JPanel panel = new JPanel();
		
		panel.add(crearPanelCentral(), BorderLayout.NORTH);
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

	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new GridLayout(6, 1));
		
		panel.add(crearPanelIncidenciaID());
		panel.add(crearPanelNumHabitacion());
		panel.add(crearPanelDescripcion());
		panel.add(crearPanelTrabajador());
		panel.add(crearPanelFecha());
		panel.add(crearPanelHora());
		
		return panel;
	}

	private Component crearPanelHora() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel horaText, hora;
		
		horaText = new JLabel("Hora: ");
		hora = new JLabel(this.incidencia.getHora());
		
		panel.add(horaText);
		panel.add(hora);
		
		return panel;
	}

	private Component crearPanelFecha() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel fechaText, fecha;
		
		fechaText = new JLabel("Fecha: ");
		fecha = new JLabel(this.incidencia.getFecha());
		
		panel.add(fechaText);
		panel.add(fecha);
		
		return panel;
	}

	private Component crearPanelTrabajador() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel trabajadorText;
		
		trabajadorText = new JLabel("Trabajador: ");
		this.trabajador = new JTextField(10);
		
		panel.add(trabajadorText);
		panel.add(this.trabajador);
		
		return panel;
	}

	private Component crearPanelDescripcion() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel descText;
		
		descText = new JLabel("Descripción: ");
		this.descripcion = new JTextField(10);
		
		panel.add(descText);
		panel.add(this.descripcion);
		
		return panel;
	}

	private Component crearPanelNumHabitacion() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel numText, num;
		
		numText = new JLabel("Número habitación: ");
		num = new JLabel(String.valueOf(this.incidencia.getNumHabitacion()));
		
		panel.add(numText);
		panel.add(num);
		
		return panel;
	}

	private Component crearPanelIncidenciaID() {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		JLabel idText, id;
		
		idText = new JLabel("Número incidencia: ");
		id = new JLabel(String.valueOf(this.incidencia.getId()));
		
		panel.add(idText);
		panel.add(id);
		
		return panel;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public JTextField getDescripcion() {
		return descripcion;
	}

	public JTextField getTrabajador() {
		return trabajador;
	}
	
}
