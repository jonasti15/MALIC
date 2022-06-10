package elementos;

public class Alerta {

    Long animal_id;
    Especie especie;
    TipoEstado estado;
    Recinto recinto_id;

    public Alerta(Long animal_id, Especie especie, TipoEstado estado, Recinto recinto_id) {
        this.animal_id = animal_id;
        this.especie = especie;
        this.estado = estado;
        this.recinto_id = recinto_id;
    }

    public Long getAnimal_id() {
        return animal_id;
    }

    public Especie getEspecie() {
        return especie;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public Recinto getRecinto_id() {
        return recinto_id;
    }
    public Object getFieldAt(int columna) {
        switch (columna){
            case 0: return animal_id;
            case 1: return getEspecie().getDescripcion();
            case 2: return getEstado().getDescripcion();
            case 3: return getRecinto_id().getRecinto_id();
            default: return null;
        }
    }

    public static Class<?> getFieldClass(int indice){
        switch (indice){
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return Integer.class;
            default: return String.class;
        }
    }
}
