package elementos;




public class TipoEstado {

    private Integer estado_id;

    private String descripcion;

    public TipoEstado(){}

    public TipoEstado(Integer estado_id, String descripcion) {
        this.estado_id = estado_id;
        this.descripcion = descripcion;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
