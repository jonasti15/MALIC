package elementos;


public class Clase {


    private Integer clase_id;

    private String descripcion;

    public Clase() {
    }

    public Clase(Integer clase_id, String descripcion) {
        this.clase_id = clase_id;
        this.descripcion = descripcion;
    }

    public Integer getClase_id() {
        return clase_id;
    }

    public void setClase_id(Integer clase_id) {
        this.clase_id = clase_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.getDescripcion();
    }
}
