package modelotablas;

import elementos.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModeloTablaAlertas extends AbstractTableModel {
    final static String [] NOMBRE_COLUMNAS = {"ID", "Especie", "Estado", "Recinto"};
    List<Alerta> alertas;

    public ModeloTablaAlertas() {
        alertas = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        for(int i=0;i<10;i++){
            this.alertas.add(new Alerta((long)i, new Especie((long)i, "Especie"+ i,new Clase(i, "Clase "+i)), new TipoEstado(i, "Estado "+i), new Recinto(i, "Recinto "+i,i)));
        }
    }

    @Override
    public int getColumnCount() {
        return NOMBRE_COLUMNAS.length;
    }

    @Override
    public int getRowCount() {
        return alertas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Alerta i = alertas.get(rowIndex);
        return i.getFieldAt(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Animal.getFieldClass(columnIndex);
    }

    public void insertar(Alerta alerta) {
        alertas.add(alerta);
        this.fireTableDataChanged();
    }

    public void borrar(int indice) {
        alertas.remove(indice);
        this.fireTableDataChanged();
    }

    public Alerta getAlerta(int indice) {
        return alertas.get(indice);
    }

}
