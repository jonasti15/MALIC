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
    private Long especieId;

    @Column(name = "path")
    private String path;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    public Especie(){}

    public Especie(Long especieId, String descripcion, Clase clase, String path) {
        this.especieId = especieId;
        this.descripcion = descripcion;
        this.clase = clase;
        this.path = path;
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

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
