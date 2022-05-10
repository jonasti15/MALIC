package dao;

import java.util.List;


/**
 * Interfaz donde est�n todos los m�todos para interactuar con los datos
 * @author Jon Clase
 */
public interface DaoDatos {
	public void whereFechaNumReservas(String fecha);
	public void whereFechaIncidencias(String fecha);
	public void whereFechaGasto(String fecha);
	public void filtrarGasto();
	public void filtrarIncidencia();
	public void filtrarNumReservas();
	public void numResults();
	public List<Float> getResult();
	public List<String> getFiltradoPor();
}
