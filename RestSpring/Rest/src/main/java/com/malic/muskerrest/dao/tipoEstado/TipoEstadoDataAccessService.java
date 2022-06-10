package com.malic.muskerrest.dao.tipoEstado;

import com.malic.muskerrest.entities.TipoEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEstadoDataAccessService implements TipoEstadoDao{

    @Autowired
    private TipoEstadoRepository repository;

    @Override
    public List<TipoEstado> getAllTipoEstado() {
        return repository.findAll();
    }

    @Override
    public TipoEstado getTipoEstado(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editTipoEstado(TipoEstado tipoEstado) {
        repository.save(tipoEstado);
    }

    @Override
    public void deleteTipoEstado(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteTipoEstado(TipoEstado tipoEstado) {
        repository.delete(tipoEstado);
    }

    @Override
    public void addTipoEstado(TipoEstado tipoEstado) {
        repository.save(tipoEstado);
    }

    @Override
    public TipoEstado getTipoEstadoByDescripcion(String descripcion) {
        return repository.findTipoEstadoByDescripcion(descripcion);
    }

    @Override
    public TipoEstado getTipoEstadoByAnimalId(long id) {
        return repository.findTipoEstadoByAnimalId(id);
    }
}
