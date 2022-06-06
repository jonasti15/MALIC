public class ConstantesMensaje {

    int animalId;
    int especie;
    float latidos;
    float temperatura;
    float tensArterial;
    float frrespiracion;
    float saturacionO2;

    public ConstantesMensaje(int animal_id, int especie, float latidos, float temperatura, float tens_arterial, float frrespiracion, float saturacionO2) {
        this.animalId = animal_id;
        this.especie = especie;
        this.latidos = latidos;
        this.temperatura = temperatura;
        this.tensArterial = tens_arterial;
        this.frrespiracion = frrespiracion;
        this.saturacionO2 = saturacionO2;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getEspecie() {
        return especie;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }

    public float getLatidos() {
        return latidos;
    }

    public void setLatidos(float latidos) {
        this.latidos = latidos;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getTensArterial() {
        return tensArterial;
    }

    public void setTensArterial(float tensArterial) {
        this.tensArterial = tensArterial;
    }

    public float getFrrespiracion() {
        return frrespiracion;
    }

    public void setFrrespiracion(float frrespiracion) {
        this.frrespiracion = frrespiracion;
    }

    public float getSaturacionO2() {
        return saturacionO2;
    }

    public void setSaturacionO2(float saturacionO2) {
        this.saturacionO2 = saturacionO2;
    }
}
