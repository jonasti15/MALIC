package com.malic.musker.dao.tipousuario;

import com.malic.musker.dto.TipoUsuario;

import java.util.ArrayList;

public interface DaoTipoUsuario {

    public TipoUsuario loadTipoUsuario(int tipo_usuario_id);
    public ArrayList<TipoUsuario> loadAllTipoUsuario();

}
