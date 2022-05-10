package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogo.DialogoReserva;

public class ControladorDialogoReserva implements ActionListener{
	DialogoReserva reserva;
	
	public ControladorDialogoReserva(DialogoReserva reserva) {
		this.reserva = reserva;
	}

	/**
	 * Método para modificar una reserva
	 * Casos:
	 * cancelar - no modifica la reserva
	 * aceptar - modifica la reserva en el caso de pulsar el botón 'Si' en el dialogo que se abre y
	 * es posible modificar la reserva con los nuevos datos
	 * @param evt - Nombre de la accion
	 *@author Jon
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String accion = evt.getActionCommand();
		String motivoError = "";
		int opcion;
		
		switch(accion) {
		case "aceptar":
			opcion = JOptionPane.showConfirmDialog(reserva, "Estas seguro que quieres modificar la reserva? ",
					"Confirmar modificación", JOptionPane.YES_NO_OPTION);
			switch(opcion) {
			case JOptionPane.YES_OPTION:
				if(!reserva.comprobarReservaFecha(reserva.getFechaInit().getText(), reserva.getFechaFin().getText()) || !reserva.comprobarReservaCamas(Integer.parseInt(reserva.getNumA().getText()), Integer.parseInt(reserva.getNumN().getText()), Integer.parseInt(reserva.getNumJ().getText()))) {
					JOptionPane.showMessageDialog(reserva, "¡No se puede modificar esa habitación! " + motivoError, "Error!", JOptionPane.ERROR_MESSAGE);
				}else {
					reserva.getReserva().setNumHabitacion(Integer.parseInt(reserva.getNumHabi().getText()));
					reserva.getReserva().setFechaInicio(reserva.getFechaInit().getText());
					reserva.getReserva().setFechaFinal(reserva.getFechaFin().getText());
					reserva.getReserva().setNumA(Integer.parseInt(reserva.getNumA().getText()));
					reserva.getReserva().setNumN(Integer.parseInt(reserva.getNumN().getText()));
					reserva.getReserva().setNumJ(Integer.parseInt(reserva.getNumJ().getText()));
				}
				break;
			case JOptionPane.NO_OPTION:
				break;
			}
			reserva.dispose();
			break;
		case "cancelar":
			reserva.dispose();
			break;
		}
	}
}
