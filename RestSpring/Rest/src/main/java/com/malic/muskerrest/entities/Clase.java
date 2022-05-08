package com.malic.muskerrest.entities;

import javax.persistence.*;

@Entity
@Table(name= "clase")
public class Clase {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "clase_id")
    private Integer clase_id;
    @Column(name = "descripcion")
    private String descripcion;

    public Clase() {
    }

    public Clase(Integer clase_id, String descripcion) {
        this.clase_id = clase_id;
        this.descripcion = descripcion;
    }

    public Integer getClase_id() {
        return clase_id;
    }

    public void setClase_id(Integer clase_id) {
        this.clase_id = clase_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
