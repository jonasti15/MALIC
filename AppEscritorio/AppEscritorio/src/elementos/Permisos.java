package elementos;

public enum Permisos {

	WORKER('W');
	
	char inicial;
	
	Permisos(char inicial) {
		this.inicial = inicial;
	}
	
	public static Permisos getPermiso(String nombre) {
		switch(nombre) {
		case "W":
		case "worker": return WORKER;

		default: return null;
		}
	}
	
	public char getInicial() {
		return this.inicial;
	}
}