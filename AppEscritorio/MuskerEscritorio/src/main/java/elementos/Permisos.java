package elementos;

public enum Permisos {

	WORKER('W'), ADMIN('A');
	
	char inicial;
	
	Permisos(char inicial) {
		this.inicial = inicial;
	}
	
	public static Permisos getPermiso(String nombre) {
		switch(nombre) {
		case "W":
		case "worker": return WORKER;
		case "A":
		case "admin": return ADMIN;

		default: return null;
		}
	}
	
	public char getInicial() {
		return this.inicial;
	}
}