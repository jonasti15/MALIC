package com.malic.muskerrest.dao.recinto;

import com.malic.muskerrest.entities.Recinto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecintoDataAccessService implements RecintoDao{

    @Autowired
    RecintoRepository repository;

    @Override
    public List<Recinto> getAllRecinto() {
        return repository.findAll();
    }

    @Override
    public Recinto getRecinto(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editRecinto(Recinto recinto) {
        repository.save(recinto);
    }

    @Override
    public void deleteRecinto(Recinto recinto) {
        repository.delete(recinto);
    }

    @Override
    public void deleteRecinto(int id) {
        repository.deleteById(id);
    }

    @Override
    public void addRecinto(Recinto recinto) {
        repository.save(recinto);
    }

    @Override
    public int getPlazasOcupadas(int id) {
        int num=0;
        try{
            num = repository.cantidadEstancias(id);
            return num;
        }catch (Exception e){
            return num;
        }


    }
}
