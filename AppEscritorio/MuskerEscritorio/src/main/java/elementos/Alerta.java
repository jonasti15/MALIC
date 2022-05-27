package elementos;

public class Alerta {

    Long animalId;
    Especie especie;
    TipoEstado estado;
    TipoEstado estadoNuevo;
    Recinto recinto_id;

    public Alerta(Long animal_id, Especie especie, TipoEstado estado,TipoEstado estadoNuevo,  Recinto recinto_id) {
        this.animalId = animal_id;
        this.especie = especie;
        this.estado = estado;
        this.estadoNuevo=estadoNuevo;
        this.recinto_id = recinto_id;
    }

    public Alerta() {
    }

    public void setAnimalId(Long animal_id) {
        this.animalId = animal_id;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public void setRecinto_id(Recinto recinto_id) {
        this.recinto_id = recinto_id;
    }

    public TipoEstado getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(TipoEstado estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Long getAnimalId() {
        return animalId;
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
            case 0: return animalId;
            case 1: return getEspecie().getDescripcion();
            case 2: return getEstadoNuevo().getDescripcion();
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
