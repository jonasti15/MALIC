import java.util.Date;

public class Constantes {
    //En la base de datos
    Date fecha;
    float constanteId;

    //Mandar por node-red
    float animalId;
    int latidos;
    int presion;
    int temperatura;
    int frrespiracion;
    String estado;

    public Constantes(Date fecha, float constanteId, float animalId, int latidos, int presion, int temperatura, int frrespiracion, String estado) {
        this.fecha = fecha;
        this.constanteId = constanteId;
        this.animalId = animalId;
        this.latidos = latidos;
        this.presion = presion;
        this.temperatura = temperatura;
        this.frrespiracion = frrespiracion;
        this.estado=estado;
    }

    public Constantes() {

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getConstanteId() {
        return constanteId;
    }

    public void setConstanteId(float constanteId) {
        this.constanteId = constanteId;
    }

    public float getAnimalId() {
        return animalId;
    }

    public void setAnimalId(float animalId) {
        this.animalId = animalId;
    }

    public int getLatidos() {
        return latidos;
    }

    public void setLatidos(int latidos) {
        this.latidos = latidos;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getFrrespiracion() {
        return frrespiracion;
    }

    public void setFrrespiracion(int frrespiracion) {
        this.frrespiracion = frrespiracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Constantes{" +
                "animalId=" + animalId +
                ", latidos=" + latidos +
                ", presion=" + presion +
                ", temperatura=" + temperatura +
                ", frrespiracion=" + frrespiracion +
                ", estado=" + estado +
                '}';
    }
}
