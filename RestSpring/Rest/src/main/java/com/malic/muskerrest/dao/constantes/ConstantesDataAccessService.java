package com.malic.muskerrest.dao.constantes;

import com.malic.muskerrest.entities.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstantesDataAccessService implements ConstantesDao {

    @Autowired
    ConstantesRepository repository;

    @Override
    public List<Constantes> getAllConstantes() {
        return repository.findAll();
    }

    @Override
    public Constantes getConstantes(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editConstantes(Constantes constante) {
        repository.save(constante);
    }

    @Override
    public void deleteConstantes(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteConstantes(Constantes constante) {
        repository.delete(constante);
    }

    @Override
    public void addConstantes(Constantes constante) {
        repository.save(constante);
    }
}
