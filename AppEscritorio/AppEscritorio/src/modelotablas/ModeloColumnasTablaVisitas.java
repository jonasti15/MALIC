package modelotablas;

import renderertablas.RenderTablaAnimales;
import renderertablas.RenderTablaVisitas;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaVisitas extends DefaultTableColumnModel {
    RenderTablaVisitas renderer;

    public ModeloColumnasTablaVisitas(RenderTablaVisitas renderer) {
        this.renderer = renderer;
        this.addColumn(crearColumna("ID", 0, 100));
        this.addColumn(crearColumna("Fecha", 1, 100));
        this.addColumn(crearColumna("Guia", 2, 100));
    }

    private TableColumn crearColumna(String string, int i, int j) {
        TableColumn columna = new TableColumn(i, j);

        columna.setHeaderValue(string);
        columna.setCellRenderer(renderer);

        return columna;
    }
}
