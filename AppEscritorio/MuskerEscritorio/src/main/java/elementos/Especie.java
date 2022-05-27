package elementos;



import javax.persistence.*;

@Entity
@Table(name= "especie")
public class Especie {

    @Id
    @GeneratedValue(generator = "especie")
    @Column(name = "especie_id")
    private Long especieId;


    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @Column(name = "path")
    private String path;

    public Especie(){}

    public Especie(Long especieId, String descripcion, String path, Clase clase) {
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

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
