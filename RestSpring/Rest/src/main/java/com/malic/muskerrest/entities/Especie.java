package com.malic.muskerrest.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= "especie")
public class Especie {

    @Id
    @GenericGenerator(name="especie" , strategy="increment")
    @GeneratedValue(generator = "especie")
    @Column(name = "especie_id")
    private Long especieId;


    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    public Especie(){}

    public Especie(Long especieId, String descripcion, String path, Clase clase) {
        this.especieId = especieId;
        this.descripcion = descripcion;
        this.path = path;
        this.clase = clase;
    }

    public Long getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Long especieId) {
        this.especieId = especieId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }
}
