package elementos;

import java.util.ArrayList;
import java.util.List;

public class ListaReservas {
	List<Reserva> lista;
	
	public ListaReservas() {
		lista = new ArrayList<>();
	}
	
	public void removeAll() {
		lista.clear();
	}
	
	public void add(Reserva r) {
		lista.add(r);
	}

	public List<Reserva> getLista() {
		return lista;
	}

	public void setLista(List<Reserva> lista) {
		this.lista = lista;
	}
	
}
