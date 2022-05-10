package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controladores.ControladorPanelReservas;
import elementos.ListaReservas;
import elementos.Reserva;
import modelotablas.ModeloColumnasTablaReservas;
import modelotablas.ModeloTablaReservas;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import renderertablas.RenderTablaReservas;

public class PanelReservas extends JScrollPane {
	
	JTable tabla;
	ModeloColumnasTablaReservas colum;
	ModeloTablaReservas tablaModel;
	RenderTablaReservas rendTabla;
	
	JTextField numHabitacion;
	String date = "";
	
	ListaReservas lista;
	Reserva reserva;
	
	UtilDateModel dateModel;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	Date selectedDate;
	
	JFrame ventana;
	
	ControladorPanelReservas controlador;
	
	public PanelReservas(JFrame ventana) {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.ventana = ventana;
		this.setBorder(null);
		this.setBackground(Color.WHITE);
		this.controlador = new ControladorPanelReservas(this);
		tablaModel = new ModeloTablaReservas();
		lista = new ListaReservas();
		rendTabla = new RenderTablaReservas();
		colum = new ModeloColumnasTablaReservas(rendTabla);
		this.setViewportView(crearPanelVentana());
	}

	private Component crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(crearPanelFiltros(), BorderLayout.NORTH);
		panel.add(crearPanelTabla(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private Component crearPanelBotones() {
		JPanel panel = new JPanel();
		JButton modificar = new JButton(new ImageIcon("images/modificar.png"));
		JButton delete = new JButton(new ImageIcon("images/delete.png"));
		
		modificar.setActionCommand("modificar");
		modificar.addActionListener(controlador);
		
		delete.setActionCommand("borrar");
		delete.addActionListener(controlador);
		
		modificar.setSize(new Dimension(20,20));
		delete.setSize(new Dimension(20,20));
		
		panel.add(modificar);
		panel.add(delete);
		
		
		return panel;
	}

	private Component crearPanelTabla() {
		JScrollPane panelTable = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		tabla = new JTable(tablaModel, colum);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelTable.setViewportView(tabla);
		
		return panelTable;
	}

	private Component crearPanelFiltros() {
		JPanel panel = new JPanel();
		JButton boton = new JButton(new ImageIcon("images/lupa.png"));
		
		boton.setActionCommand("buscar");
		boton.addActionListener(controlador);
		
		dateModel = new UtilDateModel();
		datePanel = new JDatePanelImpl(dateModel);
		datePanel.addActionListener(controlador);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setTextEditable(true);
		
		numHabitacion = new JTextField(10);
		panel.add(numHabitacion);
		panel.add(datePicker);
		panel.add(boton);
		
		return panel;
	}

	public void modificarTabla() {
		while(tablaModel.getRowCount() > 0) {
			tablaModel.borrar(0);
		}
		for(Reserva r : lista.getLista()) {
			tablaModel.insertar(r);
		}
	}

	public void convertToString(Calendar selectedDate) {
		date = selectedDate.get(Calendar.YEAR) + "-" + (selectedDate.get(Calendar.MONTH) + 1) + "-" + selectedDate.get(Calendar.DAY_OF_MONTH);
	}

	public JTable getTabla() {
		return tabla;
	}

	public ModeloTablaReservas getTablaModel() {
		return tablaModel;
	}

	public JTextField getNumHabitacion() {
		return numHabitacion;
	}

	public String getDate() {
		return date;
	}

	public ListaReservas getLista() {
		return lista;
	}

	public void setLista(ListaReservas lista) {
		this.lista = lista;
	}

	public JFrame getVentana() {
		return ventana;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public JDatePanelImpl getDatePanel() {
		return datePanel;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UtilDateModel getDateModel() {
		return dateModel;
	}
	
}
