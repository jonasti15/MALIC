package Constantes;

import Estados.Grave;
import Estados.Liberable;
import Estados.MuyGrave;
import Estados.Saludable;

import javax.xml.bind.annotation.*;

@XmlType(name = "saturacionO2", propOrder = {"unidad","mg","g","b","l"})
public class SaturacionO2 {
    String unidad;
    MuyGrave mg;
    Grave g;
    Saludable b;
    Liberable l;

    public SaturacionO2() {
    }

    public SaturacionO2(String unidad, MuyGrave mg, Grave g, Saludable b, Liberable l) {
        this.unidad = unidad;
        this.mg = mg;
        this.g = g;
        this.b = b;
        this.l = l;
    }

    public String getUnidad() {
        return unidad;
    }

    @XmlAttribute
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public MuyGrave getMg() {
        return mg;
    }

    @XmlElement
    public void setMg(MuyGrave mg) {
        this.mg = mg;
    }

    public Grave getG() {
        return g;
    }

    @XmlElement
    public void setG(Grave g) {
        this.g = g;
    }

    public Saludable getB() {
        return b;
    }

    @XmlElement
    public void setB(Saludable b) {
        this.b = b;
    }

    public Liberable getL() {
        return l;
    }

    @XmlElement
    public void setL(Liberable l) {
        this.l = l;
    }
}
