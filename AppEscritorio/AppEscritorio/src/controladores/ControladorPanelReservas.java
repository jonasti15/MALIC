package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import basedatos.ReservasBD;
import dialogo.DialogoReserva;
import elementos.Reserva;
import paneles.PanelReservas;

public class ControladorPanelReservas implements ActionListener{
	PanelReservas reservas;
	
	public ControladorPanelReservas(PanelReservas reservas) {
		this.reservas = reservas;
	}
	
	/**
	 * Método para buscar, borrar o abrir el dialogo de modificar una reserva
	 * Casos:
	 * buscar - Realiza una busqueda con los filtros introducidos
	 * borrar - Borra la reserva seleccionada si se pulsa 'Si' en el dialogo que se abre
	 * modificar - Se abre el dialogo de modificar reserva
	 * @param evt - Nombre de la accion
	 *@author Jon
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String accion = evt.getActionCommand();
		Reserva reserva;
		
		switch(accion) {
		case "modificar":
			try {
				reserva = reservas.getTablaModel().getReserva(reservas.getTabla().getSelectedRow());
				DialogoReserva dModificar = new DialogoReserva(reservas.getVentana(), reserva);
				reserva = dModificar.getReserva();
				if(reserva != null) {
					ReservasBD reservasBd = new ReservasBD();
					reservasBd.modificar(reserva);
					reservas.modificarTabla();
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(reservas.getVentana(), "Selecciona una reserva", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "borrar":
			try {
				reserva = reservas.getTablaModel().getReserva(reservas.getTabla().getSelectedRow());
				int opcion = JOptionPane.showConfirmDialog(reservas.getVentana(), "Estas seguro que quieres eliminar la reserva? ",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
				switch(opcion) {
				case JOptionPane.YES_OPTION:
					reservas.getTablaModel().borrar(reservas.getTabla().getSelectedRow());
					ReservasBD reservasBd = new ReservasBD();
					reservasBd.borrar(reserva);
					break;
				case JOptionPane.NO_OPTION:
					break;	
				}	
			}catch(Exception e) {
				JOptionPane.showMessageDialog(reservas.getVentana(), "Selecciona una reserva", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "buscar":
			ReservasBD reservasBd = new ReservasBD();
			reservas.getLista().setLista(reservasBd.filtroBusqueda(reservas.getNumHabitacion().getText(), reservas.getDate()));
			reservas.modificarTabla();
			reservas.setDate("");
			break;
		default:
			reservas.setSelectedDate((Date) reservas.getDatePanel().getModel().getValue());
			Calendar selectedValue = Calendar.getInstance();
			if(reservas.getSelectedDate() != null) {
				selectedValue.setTime(reservas.getSelectedDate());
				reservas.convertToString(selectedValue);
			}
			break;
		}
	}

}
