import Constantes.*;

import javax.xml.bind.annotation.*;

@XmlType(name = "especie", propOrder = {"id", "nombre", "ECP", "frrespiratoria", "saturacionO2", "temperatura", "tension_arterial"})
public class Especie {
    int id;
    String nombre;
    ECP ECP;
    FrecuenciaRespiratoria frrespiratoria;
    SaturacionO2 saturacionO2;
    Temperatura temperatura;
    TensionArterial tension_arterial;

    public Especie() {
    }

    public Especie(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    @XmlAttribute
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public void setECP(Constantes.ECP ECP) {
        this.ECP = ECP;
    }

    @XmlElement
    public void setFrrespiratoria(FrecuenciaRespiratoria frrespiratoria) {
        this.frrespiratoria = frrespiratoria;
    }

    @XmlElement
    public void setSaturacionO2(SaturacionO2 saturacionO2) {
        this.saturacionO2 = saturacionO2;
    }

    @XmlElement
    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    @XmlElement
    public void setTension_arterial(TensionArterial tension_arterial) {
        this.tension_arterial = tension_arterial;
    }

    public Constantes.ECP getECP() {
        return ECP;
    }

    public FrecuenciaRespiratoria getFrrespiratoria() {
        return frrespiratoria;
    }

    public SaturacionO2 getSaturacionO2() {
        return saturacionO2;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public TensionArterial getTension_arterial() {
        return tension_arterial;
    }
}
