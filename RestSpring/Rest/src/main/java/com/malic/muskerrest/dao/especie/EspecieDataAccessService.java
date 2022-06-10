package com.malic.muskerrest.dao.especie;

import com.malic.muskerrest.entities.Especie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecieDataAccessService implements EspecieDao{

    @Autowired
    EspecieRepository repository;

    @Override
    public List<Especie> getAllEspecies() {
        return repository.findAll();
    }

    @Override
    public Especie getEspecie(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editEspecie(Especie especie) {
        repository.save(especie);
    }

    @Override
    public void deleteEspecie(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteEspecie(Especie especie) {
        repository.delete(especie);
    }

    @Override
    public void addEspecie(Especie especie) {
        repository.save(especie);
    }
}
