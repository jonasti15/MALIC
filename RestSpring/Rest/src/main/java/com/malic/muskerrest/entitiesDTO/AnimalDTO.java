package com.malic.muskerrest.entitiesDTO;

import com.malic.muskerrest.entities.Especie;
import com.malic.muskerrest.entities.Recinto;
import com.malic.muskerrest.entities.TipoEstado;

public class AnimalDTO {

    private Long animalId;
    private String path;
    private Especie especie;
    private TipoEstado estado;
    private TipoEstado estadoIa;
    private Recinto recinto_id;

    public AnimalDTO(){}

    public AnimalDTO(Long animalId, Especie especie, TipoEstado estado, TipoEstado estadoIa, Recinto recinto_id, String path) {
        this.animalId = animalId;
        this.especie = especie;
        this.estado = estado;
        this.recinto_id = recinto_id;
        this.path = path;
        this.estadoIa = estadoIa;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public Recinto getRecinto_id() {
        return recinto_id;
    }

    public void setRecinto_id(Recinto recinto_id) {
        this.recinto_id = recinto_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public TipoEstado getEstadoIa() {
        return estadoIa;
    }

    public void setEstadoIa(TipoEstado estadoIa) {
        this.estadoIa = estadoIa;
    }
}
