package elementos;



import javax.persistence.*;

@Entity
@Table(name= "animal")
public class Animal {

    @Id
    @GeneratedValue(generator = "animal")
    @Column(name = "animal_id")
    private Long animalId;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private TipoEstado estado;

    @ManyToOne
    @JoinColumn(name = "recinto_id", nullable = false)
    private Recinto recinto_id;

    public Animal(){}

    public Animal(Long animalId, Especie especie, TipoEstado estado, Recinto recinto_id, String path) {
        this.animalId = animalId;
        this.especie = especie;
        this.estado = estado;
        this.recinto_id = recinto_id;
        this.path = path;
    }
    public Animal(Long animalId, Especie especie, TipoEstado estado, Recinto recinto_id) {
        this.animalId = animalId;
        this.especie = especie;
        this.estado = estado;
        this.recinto_id = recinto_id;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public Recinto getRecinto_id() {
        return recinto_id;
    }

    public void setRecinto_id(Recinto recinto_id) {
        this.recinto_id = recinto_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Object getFieldAt(int columna) {
        switch (columna){
            case 0: return animalId;
            case 1: return getEspecie().getDescripcion();
            case 2: return getRecinto_id().getRecinto_id();
            case 3: return getEstado().getDescripcion();
            default: return null;
        }
    }

    public static Class<?> getFieldClass(int indice){
        switch (indice){
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return Integer.class;
            case 3: return String.class;
            default: return String.class;
        }
    }
}
