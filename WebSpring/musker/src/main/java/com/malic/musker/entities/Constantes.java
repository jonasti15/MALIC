package com.malic.musker.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "constantes")
public class Constantes {

    @Id
    @GenericGenerator(name="constantes" , strategy="increment")
    @GeneratedValue(generator = "constantes")
    @Column(name = "constante_id")
    private Long constante_id;

    @Column(name = "constante")
    private Integer constante;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    public Constantes() {
    }

    public Constantes(Long constante_id, Integer constante, Date fecha, Animal animal) {
        this.constante_id = constante_id;
        this.constante = constante;
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
}
