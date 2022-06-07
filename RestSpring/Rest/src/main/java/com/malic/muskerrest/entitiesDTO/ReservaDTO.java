package com.malic.muskerrest.entitiesDTO;

import com.malic.muskerrest.entities.User;
import com.malic.muskerrest.entities.Visita;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public class ReservaDTO {

    private Long reserva_id;
    private Integer cantidad_personas;
    private User user;
    private Visita visita;

    public ReservaDTO() {
    }

    public ReservaDTO(Long reserva_id, Integer cantidad_personas, User user, Visita visita) {
        this.reserva_id = reserva_id;
        this.cantidad_personas = cantidad_personas;
        this.user = user;
        this.visita = visita;
    }

    public Long getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(Long reserva_id) {
        this.reserva_id = reserva_id;
    }

    public Integer getCantidad_personas() {
        return cantidad_personas;
    }

    public void setCantidad_personas(Integer cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
}
