package com.malic.muskerrest.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= "consejo")
public class Consejo {

    @Id
    @GenericGenerator(name="consejo" , strategy="increment")
    @GeneratedValue(generator = "consejo")
    @Column(name = "consejo_id")
    private Long consejo_id;

    @Column(name = "consejo")
    private String consejo;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    public Consejo(Long consejo_id, String consejo, Especie especie) {
        this.consejo_id = consejo_id;
        this.consejo = consejo;
        this.especie = especie;
    }

    public Consejo() {
    }

    public Long getConsejo_id() {
        return consejo_id;
    }

    public void setConsejo_id(Long consejo_id) {
        this.consejo_id = consejo_id;
    }

    public String getConsejo() {
        return consejo;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
}
