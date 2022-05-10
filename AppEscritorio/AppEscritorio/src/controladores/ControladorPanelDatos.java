package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import basedatos.DatosBD;
import paneles.PanelDatos;

public class ControladorPanelDatos implements ActionListener{
	PanelDatos datos;
	
	public ControladorPanelDatos(PanelDatos datos) {
		this.datos = datos;
	}
	
	/**
	 * Método que sirve para realizar la busqueda de los datos
	 * Casos:
	 * buscar - Realiza la busqueda con los filtros introducidos
	 * @param evt - Nombre de la accion
	 *@author Jon
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String accion = evt.getActionCommand();
		
		if(accion.equals("buscar")) {
			datos.rellenarStringGroupBy();
			if(datos.getqGroupBy().equals("")) {
				JOptionPane.showMessageDialog(datos, "¡Selecciona algún campo!", "¡Error!", JOptionPane.ERROR_MESSAGE);
			}else {
				if((datos.getInitDate().equals("") && !datos.getFinalDate().equals("")) || (!datos.getInitDate().equals("") && datos.getFinalDate().equals(""))) {
					JOptionPane.showMessageDialog(datos, "¡Introduce fechas correctas!", "¡Error!", JOptionPane.ERROR_MESSAGE);
				}else {
					String fecha = datos.getInitDate() + "/" + datos.getFinalDate();
					DatosBD datosBd = new DatosBD(datos.getqGroupBy(), fecha);
					if(datos.getNumReservas().isSelected()) {
						datosBd.filtrarNumReservas();
					}else if(datos.getIncidencia().isSelected()) {
						datosBd.filtrarIncidencia();
					}else if(datos.getGasto().isSelected()) {
						datosBd.filtrarGasto();
					}
					datos.setFiltradoPor(datosBd.getFiltradoPor());
					datos.setResultQuery(datosBd.getResult());
					datos.actualizarPanelGrafico();
					datos.setqGroupBy("");
				}
			}
		}else {
			datos.setSelectedInitDate((Date) datos.getInitDatePanel().getModel().getValue());
			Calendar selectedValue = Calendar.getInstance();
			if(datos.getSelectedInitDate() != null) {
				selectedValue.setTime(datos.getSelectedInitDate());
				datos.setInitDate(datos.convertToString(selectedValue));
			}else {
				datos.setInitDate("");
			}
			datos.setSelectedFinalDate((Date) datos.getFinalDatePanel().getModel().getValue());
			selectedValue = Calendar.getInstance();
			if(datos.getSelectedFinalDate() != null) {
				selectedValue.setTime(datos.getSelectedFinalDate());
				datos.setFinalDate(datos.convertToString(selectedValue));
			}else {
				datos.setFinalDate("");
			}
		}
	}

}
