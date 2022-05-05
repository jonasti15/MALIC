package com.malic.musker.dao.usuario;

import com.malic.musker.dto.Usuario;

import java.util.ArrayList;

public class UsuarioFacade {
    DaoUsuario daoUsuario;

    public UsuarioFacade(){
        daoUsuario = new DaoUsuarioMySQL();
    }

    public Usuario loadUsuario(int usuario_id){
        return daoUsuario.loadUsuario(usuario_id);
    }

    public Usuario loadUsuarioUsername(String username){
        return daoUsuario.loadUsuarioByUsername(username);
    }

    public ArrayList<Usuario> loadAllUsuarios(){
        return daoUsuario.loadALlUsuarios();
    }
}
