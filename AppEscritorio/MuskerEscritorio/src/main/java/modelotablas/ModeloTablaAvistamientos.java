package modelotablas;

import elementos.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ModeloTablaAvistamientos extends AbstractTableModel {
    final static String [] NOMBRE_COLUMNAS = {"ID", "Fecha", "Descripcion", "Especie","Localizacion","Usuario"};
    List<Avistamiento> avistamientos;

    public ModeloTablaAvistamientos(List<Avistamiento>lista) {
        avistamientos = lista;
    }


    @Override
    public int getColumnCount() {
        return NOMBRE_COLUMNAS.length;
    }

    @Override
    public int getRowCount() {
        return avistamientos.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Avistamiento i = avistamientos.get(rowIndex);
        return i.getFieldAt(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Animal.getFieldClass(columnIndex);
    }

    public void insertar(Avistamiento avistamiento) {
        avistamientos.add(avistamiento);
        this.fireTableDataChanged();
    }

    public void borrar(int indice) {
        avistamientos.remove(indice);
        this.fireTableDataChanged();
    }

    public Avistamiento getAvistamiento(int indice) {
        return avistamientos.get(indice);
    }

}
