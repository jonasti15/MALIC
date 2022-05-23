package com.malic.muskerrest.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Entity
@Table(name= "usuario")
public class User {

    @Id
    @GenericGenerator(name="usuario" , strategy="increment")
    @GeneratedValue(generator = "usuario")
    @Column(name = "usuario_id")
    private Long userId;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "profile_img")
    private String profileImg;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private UserType tipo_usuario;

    public User (String username, String password, UserType tipo_usuario){
        this.username = username;
        this.password = password;
        this.tipo_usuario = tipo_usuario;
    }

    public User(HttpServletRequest request){
        this.tipo_usuario = new UserType(3, "USUARIO");
        this.nombre = (String) request.getAttribute("name");
        this.apellido = (String) request.getAttribute("surname");
        this.email = (String) request.getAttribute("email");
        this.username = (String) request.getAttribute("username");
        this.password = String.valueOf(request.getAttribute("password").hashCode());
        this.fecha_nacimiento = (Date) request.getAttribute("birthDate");
        this.profileImg = (String) request.getAttribute("profile_img");
    }

    public User() {}

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

    public void setUserId(Long usuerId) {
        this.userId = usuerId;
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

    public UserType getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(UserType tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
