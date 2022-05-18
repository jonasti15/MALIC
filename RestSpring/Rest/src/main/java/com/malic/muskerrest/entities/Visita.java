package com.malic.muskerrest.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "visita")
public class Visita {

    @Id
    @GenericGenerator(name="visita" , strategy="increment")
    @GeneratedValue(generator = "visita")
    @Column(name = "visita_id")
    private Long visitaId;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "guia", nullable = false)
    private User guia;

    public Visita(){}

    public Visita(Long visitaId, Date fecha, User guia, String descripcion) {
        this.visitaId = visitaId;
        this.fecha = fecha;
        this.guia = guia;
        this.descripcion = descripcion;
    }

    public Long getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(Long visitaId) {
        this.visitaId = visitaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public User getGuia() {
        return guia;
    }

    public void setGuia(User guia) {
        this.guia = guia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
