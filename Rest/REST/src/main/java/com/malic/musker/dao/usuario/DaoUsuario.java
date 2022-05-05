package com.malic.musker.dao.usuario;

import com.malic.musker.dto.Usuario;

import java.util.ArrayList;

public interface DaoUsuario {

    public Usuario loadUsuario(int usuario_id);
    public ArrayList<Usuario> loadALlUsuarios();
    public Usuario loadUsuarioByUsername(String username);

}
