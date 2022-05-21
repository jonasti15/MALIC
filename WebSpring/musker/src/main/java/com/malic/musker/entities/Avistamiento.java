package com.malic.musker.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "avistamiento")
public class Avistamiento {

    @Id
    @GenericGenerator(name="avistamiento" , strategy="increment")
    @GeneratedValue(generator = "avistamiento")
    @Column(name = "avistamiento_id")
    private Long avistamiento_id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "localizacion")
    private String localizacion;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    public Avistamiento() {
    }

    public Avistamiento(Long avistamiento_id, String descripcion, Date fecha, String localizacion, Especie especie) {
        this.avistamiento_id = avistamiento_id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.especie = especie;
    }

    public Long getAvistamiento_id() {
        return avistamiento_id;
    }

    public void setAvistamiento_id(Long avistamiento_id) {
        this.avistamiento_id = avistamiento_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
}
