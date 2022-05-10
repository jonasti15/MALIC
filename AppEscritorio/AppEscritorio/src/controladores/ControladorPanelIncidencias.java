package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import basedatos.IncidenciasBD;
import dialogo.DialogoIncidencia;
import elementos.Incidencia;
import interfaz.MUsker;
import paneles.PanelIncidencias;

public class ControladorPanelIncidencias implements ActionListener{

	PanelIncidencias incidencias;
	
	public ControladorPanelIncidencias(PanelIncidencias incidencias) {
		this.incidencias = incidencias;
	}
	
	/**
	 * Método que sirve para abrir el dialogo de modificar el estado de una incidencia
	 * Casos:
	 * modificar - Abre el dialogo para modificar una incidencia
	 * @param - Nombre de la accion
	 *@author Jon
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String accion = evt.getActionCommand();
		Incidencia incidencia;
		switch(accion) {
			case "modificar":
				try {
					incidencia = incidencias.getTablaModel().getIncidencia(incidencias.getTabla().getSelectedRow());
					DialogoIncidencia dialogo = new DialogoIncidencia(incidencias.getVentana(), incidencia);
					incidencia = dialogo.getIncidencia();
					if(incidencia != null && !incidencia.getDescripcion().equals("") && !incidencia.getTrabajador().equals("")) {
						IncidenciasBD incidenciasBd = new IncidenciasBD();
						incidenciasBd.modificar(incidencia);
						incidencias.getTablaModel().borrar(incidencias.getTabla().getSelectedRow());
						incidencias.getLista().removeIncidencia(incidencia);
						if(incidencias.getLista().getLista().isEmpty()) {
							MUsker.botonAlerta.setIcon(new ImageIcon("images/noalert.png"));
						}
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(incidencias.getVentana(), "Selecciona una incidencia", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				break;
		}
	}

}
