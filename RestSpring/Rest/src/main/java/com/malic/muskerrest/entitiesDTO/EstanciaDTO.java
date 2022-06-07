package com.malic.muskerrest.entitiesDTO;

import com.malic.muskerrest.entities.Animal;

import java.util.Date;

public class EstanciaDTO {

    private Long estancia_id;
    private Date fecha_entrada;
    private Date fechaSalida;
    private String motivo_entrada;
    private Animal animal;

    public EstanciaDTO() {
    }

    public EstanciaDTO(Long estancia_id, Date fecha_entrada, Date fechaSalida, String motivo_entrada, Animal animal) {
        this.estancia_id = estancia_id;
        this.fecha_entrada = fecha_entrada;
        this.fechaSalida = fechaSalida;
        this.motivo_entrada = motivo_entrada;
        this.animal = animal;
    }

    public Long getEstancia_id() {
        return estancia_id;
    }

    public void setEstancia_id(Long estancia_id) {
        this.estancia_id = estancia_id;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getMotivo_entrada() {
        return motivo_entrada;
    }

    public void setMotivo_entrada(String motivo_entrada) {
        this.motivo_entrada = motivo_entrada;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
