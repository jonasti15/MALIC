package com.malic.musker.entitiesDTO;

import com.malic.musker.entities.UserType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Date;
import java.util.List;

public class UserDTO {

    private Long userId;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String email;
    private String username;
    private String password;
    private String profileImg;
    private UserType tipoUsuario;

    public UserDTO(HttpServletRequest request){
        this.tipoUsuario = new UserType(1, "USER");
        this.nombre = (String) request.getAttribute("name");
        this.apellido = (String) request.getAttribute("surname");
        this.email = (String) request.getAttribute("email");
        this.username = (String) request.getAttribute("username");
        this.password = String.valueOf(request.getAttribute("password").hashCode());
        this.fecha_nacimiento = (Date) request.getAttribute("birthDate");
        this.profileImg = (String) request.getAttribute("profile_img");
    }

    public UserDTO() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public UserType getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(UserType tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
