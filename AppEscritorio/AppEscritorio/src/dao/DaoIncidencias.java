package dao;

import java.util.List;

import elementos.Incidencia;

/**
 * Interfaz donde se encuentran todos los métodos para interactuar con las incidencias
 * @author Jon Clase
 */
public interface DaoIncidencias {
	public List<Incidencia> cargarIncidencias();
	public void modificar(Incidencia incidencia);
}
