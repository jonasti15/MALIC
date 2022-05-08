package com.malic.musker.entities;

import javax.persistence.*;

@Entity
@Table(name= "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "animal_id")
    private Long animal_id;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private TipoEstado estado;

    @ManyToOne
    @JoinColumn(name = "recinto_id", nullable = false)
    private Recinto recinto_id;

    public Animal(){}

    public Animal(Long animal_id, Especie especie, TipoEstado estado, Recinto recinto_id) {
        this.animal_id = animal_id;
        this.especie = especie;
        this.estado = estado;
        this.recinto_id = recinto_id;
    }

    public Long getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(Long animal_id) {
        this.animal_id = animal_id;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public Recinto getRecinto_id() {
        return recinto_id;
    }

    public void setRecinto_id(Recinto recinto_id) {
        this.recinto_id = recinto_id;
    }
}
