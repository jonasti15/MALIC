package com.malic.muskerrest.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "visita")
public class Visita {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "visita_id")
    private Long visita_id;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "guia", nullable = false)
    private User user;

    public Visita(){}

    public Visita(Long visita_id, Date fecha, User user) {
        this.visita_id = visita_id;
        this.fecha = fecha;
        this.user = user;
    }

    public Long getVisita_id() {
        return visita_id;
    }

    public void setVisita_id(Long visita_id) {
        this.visita_id = visita_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
