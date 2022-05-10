package dao;

import java.util.List;

import elementos.Reserva;


/**
 * Interfaz donde se encuentran todos los métodos para interactuar con las reservas
 * @author Jon Clase
 */
public interface DaoReservas {
	public int getCamasDobles();
	public int getCamasIndividuales();
	public List<Reserva> filtroBusqueda(String numHabitacion, String fecha);
	public List<Integer> getReservasFecha(String numHabitacion, String fechaInit, String fechaFin);
	public void getNumCamas(String numHabitacion);
	public void borrar(Reserva reserva);
	public void modificar(Reserva reserva);
	public List<Integer> queryHabitaciones();
}
