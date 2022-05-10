package basedatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoReservas;
import elementos.Reserva;
import interfaz.MUsker;

/**
 * Esta clase sirve para ver reservas, modificar reservas o borrar reservas.
 * @author Jon
 */

public class ReservasBD implements DaoReservas{
	PreparedStatement ps = null;
	ResultSet rs = null;	
	List<Reserva> lista;
	List<Integer> listaHabitacionID;
	
	int camasDobles, camasIndividuales;
	
	Reserva reserva;
	
	public ReservasBD() {
		listaHabitacionID = new ArrayList<>();
		lista = new ArrayList<>();
	}

	/**
	 *Metodo que sirve para obtener el número de camas individuales y camas dobles de una habitación.
	 *@param numHabitacion - numero de la habitación de la que se quiere hacer la consulta
	 *@author Jon 
	 */
	@Override
	public void getNumCamas(String numHabitacion) {
		try {
			ps = MUsker.conn.prepareStatement("SELECT h.camasIndividuales, h.camasDobles FROM habitacion h WHERE h.habitacionID = ?");
			ps.setString(1, numHabitacion);
			rs = ps.executeQuery();
			while(rs.next()) {
				this.camasIndividuales = rs.getInt(1);
				this.camasDobles = rs.getInt(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo que devuelve una lista de número de habitaciones disponibles entre la fecha seleccionada.
	 * @param numHabitacion - número de la habitación de la que se quiere hacer la consulta
	 * @param fecha - fecha de inicio y fecha de final en un string conjunto, para obtener 
	 * las habitaciones reservadas en esa fecha
	 *@author Jon
	 */
	@Override
	public List<Integer> getReservasFecha(String numHabitacion, String fechaInit, String fechaFin) {
		
		try {
			ps = MUsker.conn.prepareStatement("SELECT h.habitacionID FROM habitacion h WHERE h.habitacionID NOT IN(SELECT h2.habitacionID FROM habitacion h2 JOIN (SELECT * from reserva r2 where r2.reservaID != ?) r ON h2.habitacionID = r.habitacionID WHERE (DATE(?) >= r.fechaInicio AND DATE(?) < r.fechaFin) OR (DATE(?) > r.fechaInicio AND DATE(?) <= r.fechaFin) OR (DATE(?) < r.fechaInicio AND DATE(?) > r.fechaFin))");
			ps.setString(1, numHabitacion);
			ps.setString(2, fechaInit);
			ps.setString(3, fechaInit);
			ps.setString(4, fechaFin);
			ps.setString(5, fechaFin);
			ps.setString(6, fechaInit);
			ps.setString(7, fechaFin);
			rs = ps.executeQuery();
			while(rs.next()) {	
				listaHabitacionID.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.listaHabitacionID;
	}
	
	
	/**
	 * Método que sirve para obtener una lista de reservas.
	 * Se puede hacer la busqueda sin filtrar, filtrado por número de habitación, filtrado por fecha
	 * o filtrado por número de habitación y fecha.
	 * @param numHabitacion - número de la habitación de la que se quiere hacer la consulta
	 * @param fecha - fecha de inicio y fecha de final en un string conjunto, para obtener 
	 * las habitaciones reservadas en esa fecha
	 *@author Jon
	 */
	@Override
	public List<Reserva> filtroBusqueda(String numHabitacion, String fecha) {
		int numH, numA, numN, numJ, id;
		String fechaInit, fechaFin;
		
		if(numHabitacion.equals("") && fecha.equals("")) {
			try {
				ps = MUsker.conn.prepareStatement("SELECT reservaID, habitacionID, fechaInicio, fechaFin, adultos, infantiles, jubilados FROM reserva WHERE CURDATE() < fechaInicio");
				rs = ps.executeQuery();
				while(rs.next()) {
					id = rs.getInt(1);
					numH = rs.getInt(2);
					fechaInit = String.valueOf(rs.getDate(3));
					fechaFin = String.valueOf(rs.getDate(4));
					numA = rs.getInt(5);
					numN = rs.getInt(6);
					numJ = rs.getInt(7);
					this.lista.add(new Reserva(id, numH, fechaInit, fechaFin, numA, numN, numJ));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(!numHabitacion.equals("") && fecha.equals("")) {
			try {
				ps = MUsker.conn.prepareStatement("SELECT reservaID, habitacionID, fechaInicio, fechaFin, adultos, infantiles, jubilados FROM reserva WHERE habitacionID = ? AND CURDATE() < fechaInicio");
				ps.setInt(1, Integer.parseInt(numHabitacion));
				rs = ps.executeQuery();
				while(rs.next()) {
					id = rs.getInt(1);
					numH = rs.getInt(2);
					fechaInit = String.valueOf(rs.getDate(3));
					fechaFin = String.valueOf(rs.getDate(4));
					numA = rs.getInt(5);
					numN = rs.getInt(6);
					numJ = rs.getInt(7);
					this.lista.add(new Reserva(id, numH, fechaInit, fechaFin, numA, numN, numJ));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(numHabitacion.equals("") && !fecha.equals("")) {
			try {
				ps = MUsker.conn.prepareStatement("SELECT reservaID, habitacionID, fechaInicio, fechaFin, adultos, infantiles, jubilados FROM reserva WHERE fechaInicio <= ? AND fechaFin >= ? AND CURDATE() < fechaInicio");
				ps.setString(1, fecha);
				ps.setString(2, fecha);
				rs = ps.executeQuery();
				while(rs.next()) {
					id = rs.getInt(1);
					numH = rs.getInt(2);
					fechaInit = String.valueOf(rs.getDate(3));
					fechaFin = String.valueOf(rs.getDate(4));
					numA = rs.getInt(5);
					numN = rs.getInt(6);
					numJ = rs.getInt(7);
					this.lista.add(new Reserva(id, numH, fechaInit, fechaFin, numA, numN, numJ));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(!numHabitacion.equals("") && !fecha.equals("")) {
			try {
				ps = MUsker.conn.prepareStatement("SELECT reservaID, habitacionID, fechaInicio, fechaFin, adultos, infantiles, jubilados FROM reserva WHERE fechaInicio <= ? AND fechaFin >= ? AND habitacionID = ? AND CURDATE() < fechaInicio");
				ps.setString(1, fecha);
				ps.setString(2, fecha);
				ps.setInt(3, Integer.parseInt(numHabitacion));
				rs = ps.executeQuery();
				while(rs.next()) {
					id = rs.getInt(1);
					numH = rs.getInt(2);
					fechaInit = String.valueOf(rs.getDate(3));
					fechaFin = String.valueOf(rs.getDate(4));
					numA = rs.getInt(5);
					numN = rs.getInt(6);
					numJ = rs.getInt(7);
					this.lista.add(new Reserva(id, numH, fechaInit, fechaFin, numA, numN, numJ));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return this.lista;
	}

	/**
	 * Método para eliminar una reserva
	 * @param reserva - Reserva que se desea eliminar
	 *@author Jon 
	 */
	@Override
	public void borrar(Reserva reserva) {
		try {
			ps = MUsker.conn.prepareStatement("DELETE FROM reserva WHERE reservaID = ?");
			ps.setInt(1, reserva.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 *Método para modificar una reserva
	 *@param reserva - Reserva que se desea modificar
	 *@author Jon 
	 */
	@Override
	public void modificar(Reserva reserva) {
		try {
			ps = MUsker.conn.prepareStatement("UPDATE reserva SET habitacionID = ?, fechaInicio = ?, fechaFin = ?, adultos = ?, infantiles = ?, jubilados = ?, precioPago = (SELECT calcularPrecio(DATE(?), DATE(?), ?)) WHERE reservaID = ?;");
			ps.setInt(1, reserva.getNumHabitacion());
			ps.setString(2, reserva.getFechaInicio());
			ps.setString(3, reserva.getFechaFinal());
			ps.setInt(4, reserva.getNumA());
			ps.setInt(5, reserva.getNumN());
			ps.setInt(6,  reserva.getNumJ());
			ps.setString(7, reserva.getFechaInicio());
			ps.setString(8, reserva.getFechaFinal());
			ps.setInt(9, reserva.getNumHabitacion());
			ps.setInt(10,  reserva.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que realiza una query de las habitaciones que terminan el dia actual
	 * @author Jon
	 */
	public List<Integer> queryHabitaciones() {
		try {
			ps = MUsker.conn.prepareStatement("SELECT r.habitacionID FROM reserva r WHERE r.fechaFin = CURDATE()");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.listaHabitacionID.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.listaHabitacionID;
	}

	/**
	 * Método para obtener el número de camas dobles
	 *@author Jon 
	 */
	@Override
	public int getCamasDobles() {
		return this.camasDobles;
	}
	
	/** Método para obtener el número de camas individuales
	 *@author Jon 
	 */
	@Override
	public int getCamasIndividuales() {
		return this.camasIndividuales;
	}
	
}
