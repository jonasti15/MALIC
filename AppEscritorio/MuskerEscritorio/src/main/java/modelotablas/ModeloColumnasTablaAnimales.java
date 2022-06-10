package modelotablas;

import renderertablas.RenderTablaAnimales;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaAnimales extends DefaultTableColumnModel {
    RenderTablaAnimales renderer;

    public ModeloColumnasTablaAnimales(RenderTablaAnimales renderer) {
        this.renderer = renderer;
        this.addColumn(crearColumna("ID", 0, 100));
        this.addColumn(crearColumna("Especie", 1, 100));
        this.addColumn(crearColumna("Recinto", 2, 100));
        this.addColumn(crearColumna("Estado", 3, 100));
    }

    private TableColumn crearColumna(String string, int i, int j) {
        TableColumn columna = new TableColumn(i, j);

        columna.setHeaderValue(string);
        columna.setCellRenderer(renderer);

        return columna;
    }
}
