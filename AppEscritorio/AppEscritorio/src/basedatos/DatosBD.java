package basedatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoDatos;
import interfaz.MUsker;


 /**
  * Clase que sirve para obtener diferentes tipos de datos de la base de datos
 * @author Jon Clase
 *
 */
public class DatosBD implements DaoDatos{
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	List<Float> result;
	List<String> filtradoPor;
	
	int numResults;
	String groupBy, fecha;
	
	public DatosBD(String groupBy, String fecha) {
		result = new ArrayList<>();
		filtradoPor = new ArrayList<>();
		this.groupBy = groupBy;
		this.fecha = fecha;
		this.numResults();
	}

	
	/**
	 * Método que sirve para hacer un String con la sentencia WHERE de las fechas introducidas para el número de reservas.
	 * @param fecha - String de las dos fechas introducidas
	 *@author Jon 
	 */
	@Override
	public void whereFechaNumReservas(String fecha) {
		String [] valores = fecha.split("[/]");
		this.fecha = ("WHERE r.fechaInicio <= DATE('" + valores[1] + "') AND r.fechaFin >= DATE('" +valores[0]+"')");
	}

	/**
	 * Método que sirve para hacer un String con la sentencia WHERE de las fechas introducidas para el número de incidencias
	 * @param fecha - String de las dos fechas introducidas
	 *@author Jon
	 */
	@Override
	public void whereFechaIncidencias(String fecha) {
		String [] valores = fecha.split("[/]");
		this.fecha = ("WHERE i.fecha <= DATE('" + valores[1] + "') AND i.fecha >= DATE('" + valores[0] + "')");
	}


	/**
	 * Método que sirve para hacer un String con la sentencia WHERE de las fechas introducidas para el gasto
	 * @param fecha - String de las dos fechas introducidas
	 *@author Jon
	 */
	@Override
	public void whereFechaGasto(String fecha) {
		String [] valores = fecha.split("[/]");
		this.fecha = ("WHERE m.fecha <= DATE('" + valores[1] + "') AND m.fecha >= DATE('" + valores[0] + "')");
	}
	
	/**
	 * Método que sirve para obtener los datos del gasto de la temperatura
	 *@author Jon 
	 */
	@Override
	public void filtrarGasto() {
		int i;
		try {
			if(!this.fecha.equals("/")) {
				this.whereFechaGasto(fecha);
			}else {
				this.fecha = ("");
			}
			ps = MUsker.conn.prepareStatement("SELECT " + this.groupBy+ ", ROUND(AVG(ABS(temperatura-temperaturaDeseada)), 2) FROM medicion m JOIN habitacion h on m.habitacionID = h.habitacionID JOIN categoria c on h.categoriaID = c.categoriaID JOIN reserva r on h.habitacionID = r.habitacionID JOIN usuario u on r.usuarioID = u.usuarioID JOIN incidencia i on h.habitacionID = i.habitacionID " + this.fecha + " GROUP BY " + this.groupBy);
			rs = ps.executeQuery();
			while(rs.next()) {
				for(i = 1; i<=this.numResults; i++) {
					filtradoPor.add(rs.getString(i));
				}
				filtradoPor.add("!");
				result.add(rs.getFloat(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Método que sirve para obtener los datos de las incidencias
	 *@author Jon 
	 */
	@Override
	public void filtrarIncidencia() {
		int i;
		try {
			if(!this.fecha.equals("/")) {
				this.whereFechaIncidencias(fecha);
			}else {
				this.fecha = ("");
			}
			ps = MUsker.conn.prepareStatement("SELECT " + this.groupBy+ ", COUNT(DISTINCT i.incidenciaID) FROM reserva r JOIN habitacion h on r.habitacionID = h.habitacionID JOIN categoria c on h.categoriaID = c.categoriaID JOIN usuario u on r.usuarioID = u.usuarioID JOIN incidencia i on h.habitacionID = i.habitacionID " +this.fecha+ " GROUP BY " + this.groupBy);
			rs = ps.executeQuery();
			while(rs.next()) {
				for(i = 1; i<=this.numResults; i++) {
					filtradoPor.add(rs.getString(i));
				}
				filtradoPor.add("!");
				result.add(rs.getFloat(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que sirve para obtener el número de reservas
	 *@author Jon 
	 */
	@Override
	public void filtrarNumReservas() {
		int i;
		try {
			if(!this.fecha.equals("/")) {
				this.whereFechaNumReservas(fecha);
			}else {
				this.fecha = ("");
			}
			ps = MUsker.conn.prepareStatement("SELECT " +this.groupBy+ ", COUNT(r.reservaID) FROM reserva r JOIN habitacion h on h.habitacionID = r.habitacionID JOIN categoria c on c.categoriaID = h.categoriaID JOIN usuario u on u.usuarioID = r.usuarioID " +this.fecha+ "GROUP BY " + this.groupBy);
			rs = ps.executeQuery();
			while(rs.next()) {
				for(i = 1; i<=this.numResults; i++) {
					filtradoPor.add(rs.getString(i));
				}
				filtradoPor.add("!");
				result.add(rs.getFloat(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Método que sirve para obtener el número de filtros elegidos
	 *@author Jon
	 */
	@Override
	public void numResults() {
		String [] valores = this.groupBy.split("[,]");
		this.numResults = valores.length;
	}

	/**
	 * Método para obtener el resultado del filtro
	 *@author Jon 
	 */
	@Override
	public List<Float> getResult() {
		return result;
	}

	/**
	 * Método para obtener los filtros
	 *@author Jon
	 */
	@Override
	public List<String> getFiltradoPor() {
		return filtradoPor;
	}
	
}
