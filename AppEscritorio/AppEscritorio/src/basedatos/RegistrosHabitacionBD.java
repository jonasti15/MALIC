package basedatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DaoRegistrosHabitacion;
import interfaz.MUsker;

public class RegistrosHabitacionBD implements DaoRegistrosHabitacion{
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int getLastIncidencia() {
		int id = -1;
		
		try {
			ps = MUsker.conn.prepareStatement("SELECT MAX(incidenciaID) + 1 FROM incidencia;");
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public void insertIncidencia(int id, String fecha, String hora) {
		try {
			ps = MUsker.conn.prepareStatement("INSERT INTO incidencia (habitacionID, fecha, horaInicio) VALUES (?, ?, ?);");
			ps.setInt(1, id);
			ps.setString(2, fecha);
			ps.setString(3, hora);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insertMedicion(String numHabitacion, String temperatura, String fecha, String hora,
			String temperaturaDeseada) {
		try {
			ps = MUsker.conn.prepareStatement("INSERT INTO medicion (habitacionID, temperatura, fecha, hora, temperaturaDeseada) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, numHabitacion);
			ps.setString(2, temperatura);
			ps.setString(3, fecha);
			ps.setString(4, hora);
			ps.setString(5, temperaturaDeseada);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
