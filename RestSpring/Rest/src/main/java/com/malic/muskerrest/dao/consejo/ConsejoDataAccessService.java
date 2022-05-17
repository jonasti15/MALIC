package com.malic.muskerrest.dao.consejo;

import com.malic.muskerrest.entities.Consejo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsejoDataAccessService implements ConsejoDao{

    @Autowired
    ConsejoRepository repository;

    @Override
    public List<Consejo> getAllConsejos() {
        return repository.findAll();
    }

    @Override
    public Consejo getConsejo(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editConsejo(Consejo consejo) {
        repository.save(consejo);
    }

    @Override
    public void deleteConsejo(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteConsejo(Consejo consejo) {
        repository.delete(consejo);
    }

    @Override
    public void addConsejo(Consejo consejo) {
        repository.save(consejo);
    }

    @Override
    public List<Consejo> getConsejosByEspecie(Long especieId) {
        return repository.getConsejosByEspecie_EspecieId(especieId);
    }
}
