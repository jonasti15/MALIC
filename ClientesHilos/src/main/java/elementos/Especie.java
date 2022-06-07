package elementos;




public class Especie {

    private Long especieId;


    private String descripcion;


    private elementos.Clase clase;

    private String path;

    public Especie(){}

    public Especie(Long especieId, String descripcion, String path, elementos.Clase clase) {
        this.especieId = especieId;
        this.descripcion = descripcion;
        this.path = path;
        this.clase = clase;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Long especieId) {
        this.especieId = especieId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public elementos.Clase getClase() {
        return clase;
    }

    public void setClase(elementos.Clase clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
