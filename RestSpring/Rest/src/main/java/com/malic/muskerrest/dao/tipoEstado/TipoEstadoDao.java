package com.malic.muskerrest.dao.tipoEstado;

import com.malic.muskerrest.entities.TipoEstado;

import java.util.List;

public interface TipoEstadoDao {

    List<TipoEstado> getAllTipoEstado();
    TipoEstado getTipoEstado(int id);
    void editTipoEstado(TipoEstado tipoEstado);
    void deleteTipoEstado(int id);
    void deleteTipoEstado(TipoEstado tipoEstado);
    void addTipoEstado(TipoEstado tipoEstado);
    TipoEstado getTipoEstadoByDescripcion(String descripcion);
    TipoEstado getTipoEstadoByAnimalId(long id);

}
