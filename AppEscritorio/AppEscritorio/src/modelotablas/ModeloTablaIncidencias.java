package modelotablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import elementos.Incidencia;
import elementos.Reserva;

public class ModeloTablaIncidencias extends AbstractTableModel{
	final static String [] NOMBRE_COLUMNAS = {"Número incidencia", "Número habitación", "Fecha", "Hora"};
	List<Incidencia> incidencias;
	
	public ModeloTablaIncidencias() {
		incidencias = new ArrayList<>();
	}
	
	@Override
	public int getColumnCount() {
		return NOMBRE_COLUMNAS.length;
	}

	@Override
	public int getRowCount() {
		return incidencias.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Incidencia i = incidencias.get(rowIndex);
		return i.getFieldAt(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Reserva.getFieldClass(columnIndex);
	}
	
	public void insertar(Incidencia incidencia) {
		incidencias.add(incidencia);
		this.fireTableDataChanged();
	}
	
	public void borrar(int indice) {
		incidencias.remove(indice);
		this.fireTableDataChanged();
	}
	
	public Incidencia getIncidencia(int indice) {
		return incidencias.get(indice);
	}
	
}
