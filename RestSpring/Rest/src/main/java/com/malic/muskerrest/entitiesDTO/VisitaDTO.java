package com.malic.muskerrest.entitiesDTO;

import com.malic.muskerrest.entities.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

public class VisitaDTO {

    private Long visitaId;
    private Date fecha;
    private String descripcion;
    private User guia;

    public VisitaDTO(){}

    public VisitaDTO(Long visitaId, Date fecha, User guia, String descripcion) {
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
