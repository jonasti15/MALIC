package com.malic.muskerrest.entitiesDTO;

import com.malic.muskerrest.entities.Especie;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

public class AvistamientoDTO {

    private Long avistamiento_id;
    private String descripcion;
    private Date fecha;
    private String localizacion;
    private Especie especie;

    public AvistamientoDTO() {
    }

    public AvistamientoDTO(Long avistamiento_id, String descripcion, Date fecha, String localizacion, Especie especie) {
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
