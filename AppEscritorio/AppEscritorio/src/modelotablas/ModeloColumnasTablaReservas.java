package modelotablas;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import renderertablas.RenderTablaReservas;

public class ModeloColumnasTablaReservas extends DefaultTableColumnModel{
	RenderTablaReservas renderer;
	
	public ModeloColumnasTablaReservas(RenderTablaReservas renderer) {
		this.renderer = renderer;
		this.addColumn(crearColumna("Numero habitación", 0, 100));
		this.addColumn(crearColumna("Fecha inicio", 1, 100));
		this.addColumn(crearColumna("Fecha final", 2, 100));
		this.addColumn(crearColumna("Numero asistentes", 3, 100));
	}

	private TableColumn crearColumna(String string, int i, int j) {
		TableColumn columna = new TableColumn(i, j);
		
		columna.setHeaderValue(string);
		columna.setCellRenderer(renderer);
		
		return columna;
	}
}