package com.malic.musker.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "tipo_usuario")
public class UserType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "tipo_usuario_id")
    private Integer userTypeId;
    @Column(name = "descripcion")
    private String description;

    public void setUserTypeId(Integer id) {
        this.userTypeId = id;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /*public List<User> getUsers() {
        return users;
    }*/
}
