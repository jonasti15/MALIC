package com.malic.musker.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "estancia")
public class Estancia {

    @Id
    @GenericGenerator(name="estancia" , strategy="increment")
    @GeneratedValue(generator = "estancia")
    @Column(name = "estancia_id")
    private Long estancia_id;

    @Column(name = "fecha_entrada")
    private Date fecha_entrada;

    @Column(name = "fecha_salida")
    private Date fecha_salida;

    @Column(name = "motivo_entrada")
    private String motivo_entrada;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    public Estancia() {
    }

    public Estancia(Long estancia_id, Date fecha_entrada, Date fecha_salida, String motivo_entrada, Animal animal) {
        this.estancia_id = estancia_id;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
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

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
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
