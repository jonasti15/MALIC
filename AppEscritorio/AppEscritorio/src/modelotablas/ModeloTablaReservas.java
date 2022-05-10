package modelotablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import elementos.Reserva;

public class ModeloTablaReservas extends AbstractTableModel{
	final static String [] NOMBRE_COLUMNAS = {"Número habitación", "Fecha inicio", "Fecha final", "Número asistentes"};
	List<Reserva> reservas;
	
	public ModeloTablaReservas() {
		reservas = new ArrayList<>();
	}
	
	@Override
	public int getColumnCount() {
		return NOMBRE_COLUMNAS.length;
	}

	@Override
	public int getRowCount() {
		return reservas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reserva r = reservas.get(rowIndex);
		return r.getFieldAt(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Reserva.getFieldClass(columnIndex);
	}
	
	public void insertar(Reserva reserva) {
		reservas.add(reserva);
		this.fireTableDataChanged();
	}
	
	public void borrar(int indice) {
		reservas.remove(indice);
		this.fireTableDataChanged();
	}
	
	public Reserva getReserva(int indice) {
		return reservas.get(indice);
	}
	
}
