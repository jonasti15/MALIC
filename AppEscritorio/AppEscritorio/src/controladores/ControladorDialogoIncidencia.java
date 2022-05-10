package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogo.DialogoIncidencia;

public class ControladorDialogoIncidencia implements ActionListener{
	DialogoIncidencia incidencia;
	
	public ControladorDialogoIncidencia(DialogoIncidencia incidencia) {
		this.incidencia = incidencia;
	}
	
	/**
	 * Método que sirve para cambiar el estado de la incidencia
	 * Casos:
	 * cancelar - no modifica el estado
	 * aceptar - modifica el estado si se encuentran todos los datos
	 * @param evt - Nombre de la accion
	 *@author Jon
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String accion = evt.getActionCommand();
		
		switch(accion) {
		case "aceptar":
			if(incidencia.getDescripcion().getText().equals("") || incidencia.getTrabajador().getText().equals("")) {
				JOptionPane.showMessageDialog(incidencia, "¡Introduce los datos! ", "Error!", JOptionPane.ERROR_MESSAGE);
			}else {
				incidencia.getIncidencia().setDescripcion(incidencia.getDescripcion().getText());
				incidencia.getIncidencia().setTrabajador(incidencia.getTrabajador().getText());
				incidencia.dispose();
			}
			break;
		case "cancelar":
			incidencia.setIncidencia(null);
			incidencia.dispose();
			break;
		}
	}

}
