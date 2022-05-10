package elementos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import basedatos.IncidenciasBD;

public class ListaIncidencias {
	List<Incidencia> lista;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ListaIncidencias() {
		lista = new ArrayList<>();
		IncidenciasBD incidenciasDb = new IncidenciasBD();
		lista = incidenciasDb.cargarIncidencias();
	}

	public void removeAll() {
		lista.clear();
	}
	
	public void add(Incidencia i) {
		lista.add(i);
	}
	
	public void removeIncidencia(int index) {
		lista.remove(index);
	}
	
	public void removeIncidencia(Incidencia incidencia) {
		lista.remove(incidencia);
	}
	
	public List<Incidencia> getLista(){
		return lista;
	}
}
