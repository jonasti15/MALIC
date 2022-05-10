package modelotablas;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import renderertablas.RenderTablaIncidencias;

public class ModeloColumnasTablaIncidencias extends DefaultTableColumnModel{
	RenderTablaIncidencias renderer;
	
	public ModeloColumnasTablaIncidencias(RenderTablaIncidencias renderer) {
		this.renderer = renderer;
		this.addColumn(crearColumna("Número incidencia", 0, 100));
		this.addColumn(crearColumna("Número habitación", 1, 100));
		this.addColumn(crearColumna("Fecha", 2, 100));
		this.addColumn(crearColumna("Hora", 3, 100));
	}

	private TableColumn crearColumna(String string, int i, int j) {
		TableColumn columna = new TableColumn(i, j);
		
		columna.setHeaderValue(string);
		columna.setCellRenderer(renderer);
		
		return columna;
	}
}