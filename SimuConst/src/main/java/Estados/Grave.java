package Estados;

import javax.xml.bind.annotation.*;

@XmlType(name = "g", propOrder = {"min", "max"})
public class Grave {
    float min;
    float max;

    public Grave() {
    }

    public Grave(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    @XmlElement
    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    @XmlElement
    public void setMax(float max) {
        this.max = max;
    }
}
