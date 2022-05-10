package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaz.MUsker;
import paneles.PanelDatos;
import paneles.PanelIncidencias;
import paneles.PanelPrincipal;
import paneles.PanelReservas;

public class ControladorMUsker implements ActionListener{
	
	MUsker MUsker;
	
	public ControladorMUsker(MUsker MUsker) {
		this.MUsker = MUsker;
	}

	/**
	 * Método que sirve para cambiar de panel en la aplicación
	 * Casos:
	 * alerta - Cambiar al panel de las incidencias
	 * reserva - Cambiar al panel de las reservas
	 * datos - Cambiar al panel de los datos
	 * inicio - Cambiar al panel principal
	 * @param evt - Nombre de la accion
	 *@author Jon
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String accion = evt.getActionCommand();
		
		switch(accion) {
		case "alerta": 
			MUsker.getpDisplay().setViewportView(new PanelIncidencias(MUsker.listaIncidencias, MUsker));
			break;
		case "reserva":
			MUsker.getpDisplay().setViewportView(new PanelReservas(MUsker));
			break;
		case "datos":
			MUsker.getpDisplay().setViewportView(new PanelDatos(MUsker));
			break;
		case "inicio":
			MUsker.getpDisplay().setViewportView(new PanelPrincipal());
			break;
		}
	}
}
