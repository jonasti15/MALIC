import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Especies {
    List<Especie> especie;

    public Especies() {
    }

    public Especies(List<Especie> especies) {
        this.especie = especies;
    }

    public List<Especie> getEspecie() {
        return especie;
    }

    @XmlElement
    public void setEspecie(List<Especie> especie) {
        this.especie = especie;
    }
}
