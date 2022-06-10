package com.malic.musker.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= "tipo_estado")
public class TipoEstado {
    @Id
    @GenericGenerator(name="tipo_estado" , strategy="increment")
    @GeneratedValue(generator = "tipo_estado")
    @Column(name = "estado_id")
    private Integer estado_id;
    @Column(name = "descripcion")
    private String descripcion;

    public TipoEstado(){}

    public TipoEstado(Integer estado_id, String descripcion) {
        this.estado_id = estado_id;
        this.descripcion = descripcion;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
