package modelotablas;

import elementos.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloTablaAvistamientos extends AbstractTableModel {
    final static String [] NOMBRE_COLUMNAS = {"ID", "Fecha", "Descripcion", "Especie","Localizacion","Usuario"};
    List<Avistamiento> avistamientos;

    public ModeloTablaAvistamientos() {
        avistamientos = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        for(int i=0;i<10;i++){
            this.avistamientos.add(new Avistamiento((long)i,"Avistamiento numero "+i,new Date(2020,2,2),"Localizacion "+i,new User("User "+i,"123",new UserType(2,"Worker")), new Especie((long)i, "Especie"+ i,new Clase(i, "Clase "+i))));
        }
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
