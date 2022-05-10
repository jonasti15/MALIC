package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controladores.ControladorPanelIncidencias;
import elementos.Incidencia;
import elementos.ListaIncidencias;
import modelotablas.ModeloColumnasTablaIncidencias;
import modelotablas.ModeloTablaIncidencias;
import renderertablas.RenderTablaIncidencias;

public class PanelIncidencias extends JScrollPane{
	
	JFrame ventana;
	
	JTable tabla;
	ModeloColumnasTablaIncidencias colum;
	ModeloTablaIncidencias tablaModel;
	RenderTablaIncidencias rendTabla;
	
	ListaIncidencias lista;
	Incidencia incidencia;
	
	ControladorPanelIncidencias controlador;
	
	public PanelIncidencias(ListaIncidencias lista, JFrame ventana) {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.lista = lista;
		
		this.controlador = new ControladorPanelIncidencias(this);
		
		this.setBorder(null);
		this.setBackground(Color.WHITE);
		
		tablaModel = new ModeloTablaIncidencias();
		rendTabla = new RenderTablaIncidencias();
		colum = new ModeloColumnasTablaIncidencias(rendTabla);
		this.ventana = ventana;
		
		iniciarTabla();
		this.setViewportView(crearPanelVentana());
	}

	private void iniciarTabla() {
		while(tablaModel.getRowCount() > 0) {
			tablaModel.borrar(0);
		}
		for(Incidencia i : lista.getLista()) {
			tablaModel.insertar(i);
		}
	}

	private Component crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(crearPanelTabla(), BorderLayout.CENTER);
		panel.add(crearPanelBoton(), BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearPanelBoton() {
		JPanel panel = new JPanel();
		JButton modificar = new JButton(new ImageIcon("images/modificar.png"));
		
		modificar.setActionCommand("modificar");
		modificar.addActionListener(this.controlador);
		
		panel.add(modificar);
		
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

	public ModeloTablaIncidencias getTablaModel() {
		return tablaModel;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public ListaIncidencias getLista() {
		return lista;
	}

	public void setLista(ListaIncidencias lista) {
		this.lista = lista;
	}

	public JTable getTabla() {
		return tabla;
	}

	public JFrame getVentana() {
		return ventana;
	}
	
}
