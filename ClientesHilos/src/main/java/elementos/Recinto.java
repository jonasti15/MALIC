package elementos;




public class Recinto {


    private Integer recinto_id;

    private String descripcion;

    private Integer cantidad_animales;

    public Recinto(){}

    public Recinto(Integer recinto_id, String descripcion, int cantidad_animales) {
        this.recinto_id = recinto_id;
        this.descripcion = descripcion;
        this.cantidad_animales = cantidad_animales;
    }

    public Integer getRecinto_id() {
        return recinto_id;
    }

    public void setRecinto_id(Integer recinto_id) {
        this.recinto_id = recinto_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad_animales() {
        return cantidad_animales;
    }

    public void setCantidad_animales(int cantidad_animales) {
        this.cantidad_animales = cantidad_animales;
    }
    @Override
    public String toString() {
        return this.getRecinto_id()+": "+this.getDescripcion();
    }
}