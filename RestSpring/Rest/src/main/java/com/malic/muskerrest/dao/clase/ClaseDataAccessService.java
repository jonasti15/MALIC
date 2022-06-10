package com.malic.muskerrest.dao.clase;

import com.malic.muskerrest.entities.Clase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseDataAccessService implements ClaseDao{

    @Autowired
    ClaseRepository repository;

    @Override
    public List<Clase> getAllClases() {
        return repository.findAll();
    }

    @Override
    public Clase getClase(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editClase(Clase clase) {
        repository.save(clase);
    }

    @Override
    public void deleteClase(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteClase(Clase clase) {
        repository.delete(clase);
    }

    @Override
    public void addClase(Clase clase) {
        repository.save(clase);
    }
}
