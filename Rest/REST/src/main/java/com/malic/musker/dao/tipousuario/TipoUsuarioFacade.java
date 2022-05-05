package com.malic.musker.dao.tipousuario;

import com.malic.musker.dto.TipoUsuario;

import java.util.ArrayList;

public class TipoUsuarioFacade {
    DaoTipoUsuario daoTipoUsuario;

    public TipoUsuarioFacade(){
        daoTipoUsuario = new DaoTipoUsuarioMySQL();
    }

    public TipoUsuario loadTipoUsuario(int tipo_usuario_id){
        return daoTipoUsuario.loadTipoUsuario(tipo_usuario_id);
    }

    public ArrayList<TipoUsuario> loadAllTipoUsuario(){
        return daoTipoUsuario.loadAllTipoUsuario();
    }
}
