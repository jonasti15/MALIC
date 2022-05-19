package elementos;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
@Entity
@Table(name= "visita")
public class Visita {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "visita_id")
    private Long visitaId;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "guia", nullable = false)
    private User guia;

    public Visita(){}
    public Visita(Long visitaId, Date fecha, User guia, String descripcion) {
        this.visitaId = visitaId;
        this.fecha = fecha;
        this.guia = guia;
        this.descripcion = descripcion;
    }



    public Long getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(Long visita_id) {
        this.visitaId = visita_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public User getGuia() {
        return guia;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setGuia(User guia) {
        this.guia = guia;
    }
    public Object getFieldAt(int columna) {
        switch (columna){
            case 0: return visitaId;
            case 1: return fecha;
            case 2: return guia.getNombre();
            default: return null;
        }
    }

    public static Class<?> getFieldClass(int indice){
        switch (indice){
            case 0: return Long.class;
            case 1: return Date.class;
            case 2: return String.class;
            default: return String.class;
        }
    }
}
