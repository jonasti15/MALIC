package com.malic.muskerrest.dao.estancia;

import com.malic.muskerrest.entities.Estancia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstanciaDataAccessService implements EstanciaDao{

    @Autowired
    EstanciaRepository repository;

    @Override
    public List<Estancia> getAllEstancias() {
        return repository.findAll();
    }

    @Override
    public Estancia getEstancia(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editEstancia(Estancia estancia) {
        repository.save(estancia);
    }

    @Override
    public void deleteEstancia(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteEstancia(Estancia estancia) {
        repository.delete(estancia);
    }

    @Override
    public void addEstancia(Estancia estancia) {
        repository.save(estancia);
    }

    @Override
    public List<Estancia> getActiveEstancias() {
        return repository.getEstanciasByFechaSalidaIsNull();
    }
}
