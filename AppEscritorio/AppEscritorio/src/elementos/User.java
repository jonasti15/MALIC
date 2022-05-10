package elementos;

public class User {

	String name;
	Permisos permisos;
	
	public User(String name, Permisos permisos) {
		this.name = name;
		this.permisos = permisos;
	}
	
	public String getName() {
		return name;
	}
	
	public Permisos getPermisos() {
		return permisos;
	}
	
}
