package dao;

public interface DaoRegistrosHabitacion {
	public int getLastIncidencia();
	public void insertIncidencia(int id, String fecha, String hora);
	public void insertMedicion(String numHabitacion, String temperatura, String fecha, String hora, String temperaturaDeseada);
}
