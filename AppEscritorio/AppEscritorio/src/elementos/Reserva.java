package elementos;

public class Reserva {
	int numHabitacion, numAsistentes, id, numA, numN, numJ;
	String fechaInicio, fechaFinal;
	
	public Reserva(int id, int numHabitacion, String fechaInicio, String fechaFinal, int numA, int numN, int numJ) {
		this.id = id;
		this.numHabitacion = numHabitacion;
		this.numA = numA;
		this.numN = numN;
		this.numJ = numJ;
		this.numAsistentes = numA + numN + numJ;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}

	public int getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(int numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public int getNumAsistentes() {
		return numAsistentes;
	}

	public void setNumAsistentes(int numAsistentes) {
		this.numAsistentes = numAsistentes;
	}
	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumA() {
		return numA;
	}

	public void setNumA(int numA) {
		this.numA = numA;
	}

	public int getNumN() {
		return numN;
	}

	public void setNumN(int numN) {
		this.numN = numN;
	}

	public int getNumJ() {
		return numJ;
	}

	public void setNumJ(int numJ) {
		this.numJ = numJ;
	}

	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return new Integer(numHabitacion);
		case 1: return fechaInicio;
		case 2: return fechaFinal;
		case 3: return new Integer(numAsistentes);
		default: return null; 
		}
	}
	
	public static Class<?> getFieldClass(int indice){
		switch (indice){
		case 0: return Integer.class;
		case 3: return Integer.class;
		default: return String.class; 
		}
	}
	
}
