package elementos;


import java.util.Date;

public class Avistamiento {

    private Long avistamiento_id;
    private String descripcion;
    private Date fecha;
    private String localizacion;
    private User user;
    private Especie especie;

    public Avistamiento() {
    }

    public Avistamiento(Long avistamiento_id, String descripcion, Date fecha, String localizacion, User user, Especie especie) {
        this.avistamiento_id = avistamiento_id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.user = user;
        this.especie = especie;
    }

    public Long getAvistamiento_id() {
        return avistamiento_id;
    }

    public void setAvistamiento_id(Long avistamiento_id) {
        this.avistamiento_id = avistamiento_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    public Object getFieldAt(int columna) {
        switch (columna){
            case 0: return avistamiento_id;
            case 1: return fecha;
            case 2: return descripcion;
            case 3: return especie.getDescripcion();
            case 4: return localizacion;
            case 5: return user.getNombre();
            default: return null;
        }
    }

    public static Class<?> getFieldClass(int indice){
        switch (indice){
            case 0: return Long.class;
            case 1: return Date.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return String.class;
            default: return String.class;
        }
    }
}
