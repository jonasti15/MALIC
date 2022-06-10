package Estados;

import javax.xml.bind.annotation.*;

@XmlType(name = "mg", propOrder = {"min", "max"})
public class MuyGrave {
    float min;
    float max;

    public MuyGrave() {
    }

    public MuyGrave(float min, float max) {
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
