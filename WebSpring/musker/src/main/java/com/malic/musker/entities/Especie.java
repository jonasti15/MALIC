package com.malic.musker.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= "especie")
public class Especie {

    @Id
    @GenericGenerator(name="especie" , strategy="increment")
    @GeneratedValue(generator = "especie")
    @Column(name = "especie_id")
    private Long especie_id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    public Especie(){}

    public Especie(Long especie_id, String descripcion, Clase clase) {
        this.especie_id = especie_id;
        this.descripcion = descripcion;
        this.clase = clase;
    }

    public Long getEspecie_id() {
        return especie_id;
    }

    public void setEspecie_id(Long especie_id) {
        this.especie_id = especie_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }
}
