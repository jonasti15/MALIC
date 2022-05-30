package com.malic.muskerrest.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "constantes")
public class Constantes {

    @Id
    @GenericGenerator(name="constantes" , strategy="increment")
    @GeneratedValue(generator = "constantes")
    @Column(name = "constante_id")
    private Long constante_id;


    @Column(name = "latidos")
    private Integer latidos;

    @Column(name = "saturacionO2")
    private Integer saturacionO2;

    @Column(name = "tens_arterial")
    private Integer tensArterial;

    @Column(name = "temperatura")
    private Integer temperatura;

    @Column(name = "frrespiracion")
    private Integer frrespiracion;

    @Column(name = "fecha")
    private Date fecha;

    @JoinColumn(name = "animal_id", nullable = false)
    private Long animalId;

    public Constantes() {
    }

    public Constantes(Long constante_id, Integer latidos, Integer saturacionO2, Integer tensArterial, Integer temperatura, Integer frrespiracion, Date fecha, Long animalId) {
        this.constante_id = constante_id;
        this.latidos = latidos;
        this.saturacionO2 = saturacionO2;
        this.tensArterial = tensArterial;
        this.temperatura = temperatura;
        this.frrespiracion = frrespiracion;
        this.fecha = fecha;
        this.animalId = animalId;
    }

    public Integer getSaturacionO2() {
        return saturacionO2;
    }

    public void setSaturacionO2(Integer saturacionO2) {
        this.saturacionO2 = saturacionO2;
    }

    public Integer getTensArterial() {
        return tensArterial;
    }

    public void setTensArterial(Integer tensArterial) {
        this.tensArterial = tensArterial;
    }

    public Long getConstante_id() {
        return constante_id;
    }

    public void setConstante_id(Long constante_id) {
        this.constante_id = constante_id;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animal) {
        this.animalId = animal;
    }

    public Integer getLatidos() {
        return latidos;
    }

    public void setLatidos(Integer latidos) {
        this.latidos = latidos;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getFrrespiracion() {
        return frrespiracion;
    }

    public void setFrrespiracion(Integer frrespiracion) {
        this.frrespiracion = frrespiracion;
    }

    @Override
    public String toString() {
        return "Constantes{" +
                "constante_id=" + constante_id +
                ", latidos=" + latidos +
                ", temperatura=" + temperatura +
                ", frrespiracion=" + frrespiracion +
                ", fecha=" + fecha +
                ", animal=" + animalId +
                '}';
    }
}
