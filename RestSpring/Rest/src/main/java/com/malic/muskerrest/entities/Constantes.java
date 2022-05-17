package com.malic.muskerrest.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "constantes")
public class Constantes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "constante_id")
    private Long constante_id;

    @Column(name = "constante")
    private Integer constante;

    @Column(name = "latidos")
    private Integer latidos;

    @Column(name = "presion")
    private Integer presion;

    @Column(name = "temperatura")
    private Integer temperatura;

    @Column(name = "frrespiracion")
    private Integer frrespiracion;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    public Constantes() {
    }

    public Constantes(Long constante_id, Integer constante, Integer latidos, Integer presion, Integer temperatura,
                      Integer frrespiracion, Date fecha, Animal animal) {
        this.constante_id = constante_id;
        this.constante = constante;
        this.latidos = latidos;
        this.presion = presion;
        this.temperatura = temperatura;
        this.frrespiracion = frrespiracion;
        this.fecha = fecha;
        this.animal = animal;
    }

    public Long getConstante_id() {
        return constante_id;
    }

    public void setConstante_id(Long constante_id) {
        this.constante_id = constante_id;
    }

    public Integer getConstante() {
        return constante;
    }

    public void setConstante(Integer constante) {
        this.constante = constante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Integer getLatidos() {
        return latidos;
    }

    public void setLatidos(Integer latidos) {
        this.latidos = latidos;
    }

    public Integer getPresion() {
        return presion;
    }

    public void setPresion(Integer presion) {
        this.presion = presion;
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
                ", constante=" + constante +
                ", latidos=" + latidos +
                ", presion=" + presion +
                ", temperatura=" + temperatura +
                ", frrespiracion=" + frrespiracion +
                ", fecha=" + fecha +
                ", animal=" + animal +
                '}';
    }
}
