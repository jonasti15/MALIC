package basedatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoIncidencias;
import elementos.Incidencia;
import interfaz.MUsker;

/**
 * Clase que sirve para ver y modificar las incidencias
 * @author Jon Clase
 */
public class IncidenciasBD implements DaoIncidencias{
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	/**
	 * Método para cargar las incidencias no finalizadas
	 *@author Jon
	 */
	@Override
	public List<Incidencia> cargarIncidencias() {
		List<Incidencia> lista = new ArrayList<>();
		
		try {
			ps = MUsker.conn.prepareStatement("SELECT incidenciaID, habitacionID, fecha, horaInicio FROM incidencia WHERE finalizado = 0;");
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				int numHabitacion = rs.getInt(2);
				String fecha = rs.getString(3);
				String hora = rs.getString(4);
				lista.add(new Incidencia(id, numHabitacion, fecha, hora));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	/**
	 * Método para modificar el estado de una incidencia a finalizada
	 *@author Jon
	 */
	@Override
	public void modificar(Incidencia incidencia) {
		try {
			ps = MUsker.conn.prepareStatement("UPDATE incidencia SET descripcion = ?, trabajador = ?, finalizado = 1 WHERE incidenciaID = ?;");
			ps.setString(1, incidencia.getDescripcion());
			ps.setString(2, incidencia.getTrabajador());
			ps.setInt(3, incidencia.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
