package elementos;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Avistamiento {

    private Long avistamiento_id;
    private String descripcion;
    private Date fecha;
    private String localizacion;
    private Especie especie;

    public Avistamiento() {
    }

    public Avistamiento(Long avistamiento_id, String descripcion, Date fecha, String localizacion,  Especie especie) {
        this.avistamiento_id = avistamiento_id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.localizacion = localizacion;
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
    public String getFechaString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(this.getFecha());
        return format;
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


    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    public Object getFieldAt(int columna) {
        switch (columna){
            case 0: return avistamiento_id;
            case 1: return this.getFechaString();
            case 2: return descripcion;
            case 3: return especie.getDescripcion();
            case 4: return localizacion;
            default: return null;
        }
    }

    public static Class<?> getFieldClass(int indice){
        switch (indice){
            case 0: return Long.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            default: return String.class;
        }
    }

}
