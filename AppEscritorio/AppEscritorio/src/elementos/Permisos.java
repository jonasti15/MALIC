package elementos;

public enum Permisos {

	WORKER('W');
	
	char inicial;
	
	Permisos(char inicial) {
		this.inicial = inicial;
	}
	
	public static Permisos getPermiso(String nombre) {
		switch(nombre) {
		case "T":
		case "WORKER": return WORKER;
		default: return null;
		}
	}
	
	public char getInicial() {
		return this.inicial;
	}
}