package modelotablas;

import elementos.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModeloTablaAnimales extends AbstractTableModel {
    final static String [] NOMBRE_COLUMNAS = {"ID", "Especie", "Recinto", "Estado"};
    List<Animal> animales;

    public ModeloTablaAnimales() {
        animales = new ArrayList<>();
        inicializar();
    }

    private void inicializar() {
        /* for(int i=0;i<10;i++){
            this.animales.add(new Animal((long)i, new Especie((long)i, "Especie"+ i,new Clase(i, "Clase "+i)), new TipoEstado(i, "Estado "+i), new Recinto(i, "Recinto "+i,i)));
        }*/
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }

    @Override
    public int getColumnCount() {
        return NOMBRE_COLUMNAS.length;
    }

    @Override
    public int getRowCount() {
        return animales.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal i = animales.get(rowIndex);
        return i.getFieldAt(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Animal.getFieldClass(columnIndex);
    }

    public void insertar(Animal animal) {
        animales.add(animal);
        this.fireTableDataChanged();
    }

    public void borrar(int indice) {
        animales.remove(indice);
        this.fireTableDataChanged();
    }

    public Animal getAnimal(int indice) {
        return animales.get(indice);
    }

}
