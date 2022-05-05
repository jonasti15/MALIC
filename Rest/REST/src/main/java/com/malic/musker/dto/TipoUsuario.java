package com.malic.musker.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"tipo_usuario_id", "descripcion"})
public class TipoUsuario {

    private int tipo_usuario_id;
    private String descripcion;

    public TipoUsuario() {
    }

    public TipoUsuario(int tipo_usuario_id, String descripcion) {
        this.tipo_usuario_id = tipo_usuario_id  ;
        this.descripcion = descripcion;
    }

    public int getTipo_usuario_id() {
        return tipo_usuario_id;
    }

    public void setTipo_usuario_id(int tipo_usuario_id) {
        this.tipo_usuario_id = tipo_usuario_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
