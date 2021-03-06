package modelotablas;

import elementos.*;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ModeloTablaVisitas  extends AbstractTableModel {
    final static String [] NOMBRE_COLUMNAS = {"ID", "Fecha", "Guia"};
    List<Visita> visitas;

    public ModeloTablaVisitas() {
        visitas = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        for(int i=0;i<100;i++){
            this.visitas.add(new Visita((long)i,new Date(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()), new User("User "+i, Permisos.WORKER)));
        }
    }

    @Override
    public int getColumnCount() {
        return NOMBRE_COLUMNAS.length;
    }

    @Override
    public int getRowCount() {
        return visitas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Visita i = visitas.get(rowIndex);
        return i.getFieldAt(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Visita.getFieldClass(columnIndex);
    }

    public void insertar(Visita visita) {
        visitas.add(visita);
        this.fireTableDataChanged();
    }

    public void borrar(int indice) {
        visitas.remove(indice);
        this.fireTableDataChanged();
    }

    public Visita getVisita(int indice) {
        return visitas.get(indice);
    }

}
