package elementos;

public class Incidencia {
	int id, numHabitacion, finalizado;
	String descripcion, trabajador, fecha, hora;
	
	public Incidencia(int id, int numHabitacion, String fecha, String hora) {
		this.id = id;
		this.numHabitacion = numHabitacion;
		this.fecha = fecha;
		this.hora = hora;
		this.descripcion = "";
		this.trabajador = "";
	}

	public int getId() {
		return id;
	}

	public int getNumHabitacion() {
		return numHabitacion;
	}

	public int getFinalizado() {
		return finalizado;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTrabajador() {
		return trabajador;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTrabajador(String trabajador) {
		this.trabajador = trabajador;
	}
	
	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return new Integer(id);
		case 1: return new Integer(numHabitacion);
		case 2: return fecha;
		case 3: return hora;
		default: return null; 
		}
	}
	
	public static Class<?> getFieldClass(int indice){
		switch (indice){
		case 0: return Integer.class;
		case 1: return Integer.class;
		default: return String.class; 
		}
	}
	
}
