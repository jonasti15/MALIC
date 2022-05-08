package com.malic.muskerrest.entities;

import javax.persistence.*;

@Entity
@Table(name= "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "reserva_id")
    private Long reserva_id;

    @Column(name = "cantidad_personas")
    private Integer cantidad_personas;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "visita_id", nullable = false)
    private Visita visita;

    public Reserva() {
    }

    public Reserva(Long reserva_id, Integer cantidad_personas, User user, Visita visita) {
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
