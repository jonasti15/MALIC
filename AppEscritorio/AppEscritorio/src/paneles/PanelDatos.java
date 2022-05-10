package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import controladores.ControladorPanelDatos;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PanelDatos extends JScrollPane {
	
	CategoryDataset dataset;
	JFreeChart chart;
	ChartPanel chartPanel;
	
	JFrame ventana;
	
	JRadioButton numReservas, incidencia, gasto;
	ButtonGroup grupo;
	
	JCheckBox cTipo, cPiso, cEsquina, cDescripcion, cEdad, cDuracion;
	
	UtilDateModel initDateModel, finalDateModel;
	JDatePanelImpl initDatePanel, finalDatePanel;
	JDatePickerImpl initDatePicker, finalDatePicker;
	Date selectedInitDate, selectedFinalDate;
	
	String initDate = "", finalDate = "";
	String qGroupBy = "";
	
	List<String> filtradoPor;
	List<Float> resultQuery;
	
	ControladorPanelDatos controlador;
	
	public PanelDatos(JFrame ventana) {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.ventana = ventana;
		filtradoPor = new ArrayList<>();
		resultQuery = new ArrayList<>();
		controlador = new ControladorPanelDatos(this);
		this.setBorder(null);
		this.setBackground(Color.WHITE);
		this.setViewportView(crearPanelVentana());
	}

	private Component crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(crearPanelFecha(), BorderLayout.NORTH);
		panel.add(crearPanelCentral(), BorderLayout.CENTER);
		panel.add(crearPanelOpciones(), BorderLayout.SOUTH);
		
		return panel;
	}
	private Component crearPanelFecha() {
		JPanel panel = new JPanel ();
		
		initDateModel = new UtilDateModel();
		initDatePanel = new JDatePanelImpl(initDateModel);
		initDatePanel.addActionListener(controlador);
		initDatePicker = new JDatePickerImpl(initDatePanel);
		initDatePicker.setTextEditable(true);
		
		finalDateModel = new UtilDateModel();
		finalDatePanel = new JDatePanelImpl(finalDateModel);
		finalDatePanel.addActionListener(controlador);
		finalDatePicker = new JDatePickerImpl(finalDatePanel);
		finalDatePicker.setTextEditable(true);
		
		panel.add(initDatePicker);
		panel.add(finalDatePicker);
		
		return panel;
	}

	private Component crearPanelCentral() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, crearPanelGrafica(), crearPanelCheckBox());
	
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int)toolkit.getScreenSize().getWidth();
		
		panel.setDividerLocation(width-280);
		
		return panel;
	}

	private Component crearPanelCheckBox() {
		JPanel panel = new JPanel(new GridLayout(6, 1));
		
		cTipo = new JCheckBox("Tipo");
		cPiso = new JCheckBox("Piso");
		cEsquina = new JCheckBox("Esquina");
		cDescripcion = new JCheckBox("Descripción");
		cEdad = new JCheckBox("Edad");
		cDuracion = new JCheckBox("Duración");
		
		panel.add(cTipo);
		panel.add(cPiso);
		panel.add(cEsquina);
		panel.add(cDescripcion);
		panel.add(cEdad);
		panel.add(cDuracion);
		
		return panel;
	}

	private Component crearPanelGrafica() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		width -= 280*2;
		int height = 500;

		dataset = new DefaultCategoryDataset();
		chart = ChartFactory.createBarChart3D("HOLA", "","" ,
					dataset, PlotOrientation.HORIZONTAL, true, true, false);
		chartPanel = new ChartPanel(null);
		chartPanel.setPreferredSize(new java.awt.Dimension(width, height));
		chartPanel.setMaximumDrawWidth(2000);
		
		panel.add(chartPanel);
		
		return panel;
	}
	
	public void actualizarPanelGrafico() {
		dataset = createDataset();
		chart = createChart(dataset);
		chartPanel.setChart(chart);
		
		setMinimumTickUnits(chart);
		crearRenderer(chart);
	}
	
	public void setMinimumTickUnits(JFreeChart chart) {
		ValueAxis axis = chart.getCategoryPlot().getRangeAxis();
		axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		CategoryPlot catPlot = chart.getCategoryPlot();
        CategoryAxis domainAxis = catPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	  }	
	
	  private void crearRenderer(JFreeChart chart) {
		  CategoryItemRenderer renderer = ((CategoryPlot)chart.getPlot()).getRenderer();
		
	      renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	      renderer.setBaseItemLabelsVisible(true);
	}

	
	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<String> values = new ArrayList<>();
		String strTmp="";
		
		if(!this.filtradoPor.isEmpty()) {
			strTmp = filtradoPor.get(0);	
		}
		
		 for (int i = 1; i < filtradoPor.size(); i++) {
			 if(filtradoPor.get(i).equals("!")) {
				 values.add(strTmp);
				 strTmp = "";
			 }
			 else {
				 if(strTmp.equals(""))strTmp = strTmp+filtradoPor.get(i);
				 else strTmp = strTmp+","+filtradoPor.get(i);
			 }
	     }
		
		for(int i = 0;i<values.size();i++) dataset.setValue(resultQuery.get(i), getFilter(), values.get(i));
		
		return dataset;
	}


	private String getFilter() {
		String str = "";
		if(cTipo.isSelected())str= str+","+cTipo.getText();
		if(cPiso.isSelected())str= str+","+cPiso.getText();
		if(cEsquina.isSelected())str= str+","+cEsquina.getText();
		if(cDescripcion.isSelected())str= str+","+cDescripcion.getText();
		if(cEdad.isSelected())str= str+","+cEdad.getText();
		if(cDuracion.isSelected())str= str+","+cDuracion.getText();
		return str.substring(1);
	}

	private String getFilterValueName() {
		if(numReservas.isSelected())return numReservas.getText();
		if(incidencia.isSelected())return incidencia.getText();
		if(gasto.isSelected())return gasto.getText();
		
		return null;
	}
	
	private JFreeChart createChart(final CategoryDataset dataset) {
		JFreeChart chart;
		chart = ChartFactory.createBarChart3D(getFilterValueName().toUpperCase(), getFilter(),getFilterValueName() ,
					  								dataset, PlotOrientation.HORIZONTAL, true, true, false);
		return chart;
	}
	
	private Component crearPanelOpciones() {
		JPanel panel = new JPanel();
		JButton boton = new JButton(new ImageIcon("images/lupa.png"));
		panel.setBackground(Color.WHITE);
		
		grupo = new ButtonGroup();
			
		numReservas = new JRadioButton("Número de reservas");
		numReservas.setActionCommand("numReservas");
		grupo.add(numReservas);
		
		incidencia = new JRadioButton("Incidencia");
		incidencia.setActionCommand("incidencia");
		grupo.add(incidencia);
		
		gasto = new JRadioButton("Diferencia de temperatura");
		gasto.setActionCommand("gasto");
		grupo.add(gasto);
		
		boton.setActionCommand("buscar");
		boton.addActionListener(controlador);
		
		numReservas.setSelected(true);
		
		panel.add(numReservas);
		panel.add(incidencia);
		panel.add(gasto);
		panel.add(boton);
		
		return panel;
	}


	public void rellenarStringGroupBy() {
		
		if(cTipo.isSelected()) {
			this.qGroupBy = ("c.tipo");
		}
		if(cPiso.isSelected()) {
			if(!this.qGroupBy.equals("")) {
				this.qGroupBy = (this.qGroupBy + ", ");
			}
			this.qGroupBy = (this.qGroupBy + "FLOOR(h.habitacionID / 100)");
		}
		if(cEsquina.isSelected()) {
			if(!this.qGroupBy.equals("")) {
				this.qGroupBy = (this.qGroupBy + ", ");
			}
			this.qGroupBy = (this.qGroupBy + "c.esquina");
		}
		if(cDescripcion.isSelected()) {
			if(!this.qGroupBy.equals("")) {
				this.qGroupBy = (this.qGroupBy + ", ");
			}
			this.qGroupBy = (this.qGroupBy + "c.descripcion");
		}
		if(cEdad.isSelected()) {
			if(!this.qGroupBy.equals("")) {
				this.qGroupBy = (this.qGroupBy + ", ");
			}
			this.qGroupBy = (this.qGroupBy + "r.adultos, r.infantiles, r.jubilados");
		}
		if(cDuracion.isSelected()) {
			if(!this.qGroupBy.equals("")) {
				this.qGroupBy = (this.qGroupBy + ", ");
			}
			this.qGroupBy = (this.qGroupBy + "r.fechaFin - r.fechaInicio");
		}
		
	}
	
	public String convertToString(Calendar selectedDate) {
		String date = selectedDate.get(Calendar.YEAR) + "-" + (selectedDate.get(Calendar.MONTH) + 1) + "-" + selectedDate.get(Calendar.DAY_OF_MONTH);
		
		return date;
	}
	
	public String getqGroupBy() {
		return qGroupBy;
	}

	public void setqGroupBy(String qGroupBy) {
		this.qGroupBy = qGroupBy;
	}

	public String getInitDate() {
		return initDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public ButtonGroup getGrupo() {
		return grupo;
	}

	public void setFiltradoPor(List<String> filtradoPor) {
		this.filtradoPor = filtradoPor;
	}

	public void setResultQuery(List<Float> resultQuery) {
		this.resultQuery = resultQuery;
	}

	public List<String> getFiltradoPor() {
		return filtradoPor;
	}

	public List<Float> getResultQuery() {
		return resultQuery;
	}
	
	public Date getSelectedInitDate() {
		return selectedInitDate;
	}

	public void setSelectedInitDate(Date selectedInitDate) {
		this.selectedInitDate = selectedInitDate;
	}

	public Date getSelectedFinalDate() {
		return selectedFinalDate;
	}

	public void setSelectedFinalDate(Date selectedFinalDate) {
		this.selectedFinalDate = selectedFinalDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public JDatePanelImpl getInitDatePanel() {
		return initDatePanel;
	}

	public void setInitDatePanel(JDatePanelImpl initDatePanel) {
		this.initDatePanel = initDatePanel;
	}

	public JDatePanelImpl getFinalDatePanel() {
		return finalDatePanel;
	}

	public void setFinalDatePanel(JDatePanelImpl finalDatePanel) {
		this.finalDatePanel = finalDatePanel;
	}

	public JRadioButton getNumReservas() {
		return numReservas;
	}

	public JRadioButton getIncidencia() {
		return incidencia;
	}

	public JRadioButton getGasto() {
		return gasto;
	}
	
}
