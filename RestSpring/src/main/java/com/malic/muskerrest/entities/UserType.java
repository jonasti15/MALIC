package com.malic.muskerrest.entities;

import javax.persistence.*;

@Entity
@Table(name= "tipo_usuario")
public class UserType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "tipo_usuario_id")
    private Integer tipo_usuario_id;
    @Column(name = "descripcion")
    private String descripcion;

    public UserType() {
    }

    public UserType(Integer tipo_usuario_id, String descripcion) {
        this.tipo_usuario_id = tipo_usuario_id;
        this.descripcion = descripcion;
    }

    public Integer getTipo_usuario_id() {
        return tipo_usuario_id;
    }

    public void setTipo_usuario_id(Integer tipo_usuario_id) {
        this.tipo_usuario_id = tipo_usuario_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*public List<User> getUsers() {
        return users;
    }*/
}
