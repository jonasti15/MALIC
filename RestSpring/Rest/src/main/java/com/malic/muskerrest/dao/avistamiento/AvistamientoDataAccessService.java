package com.malic.muskerrest.dao.avistamiento;

import com.malic.muskerrest.entities.Avistamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvistamientoDataAccessService implements AvistamientoDao{

    @Autowired
    AvistamientoRepository repository;

    @Override
    public List<Avistamiento> getAllAvistamientos() {
        return repository.findAll();
    }

    @Override
    public Avistamiento getAvistamiento(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editAvistamiento(Avistamiento avistamiento) {
        repository.save(avistamiento);
    }

    @Override
    public void deleteAvistamiento(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAvistamiento(Avistamiento avistamiento) {
        repository.delete(avistamiento);
    }

    @Override
    public void addAvistamiento(Avistamiento avistamiento) {
        repository.save(avistamiento);
    }
}
