package modelotablas;

import renderertablas.RenderTablaAlertas;
import renderertablas.RenderTablaAvistamientos;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaAvistamientos extends DefaultTableColumnModel {
    RenderTablaAvistamientos renderer;

    public ModeloColumnasTablaAvistamientos(RenderTablaAvistamientos renderer) {
        this.renderer = renderer;
        this.addColumn(crearColumna("ID", 0, 100));
        this.addColumn(crearColumna("Fecha", 1, 100));
        this.addColumn(crearColumna("Descripcion", 2, 100));
        this.addColumn(crearColumna("Especie", 3, 100));
        this.addColumn(crearColumna("Localizacion", 3, 100));
        this.addColumn(crearColumna("Usuario", 3, 100));

    }

    private TableColumn crearColumna(String string, int i, int j) {
        TableColumn columna = new TableColumn(i, j);

        columna.setHeaderValue(string);
        columna.setCellRenderer(renderer);

        return columna;
    }
}
