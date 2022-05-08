package com.malic.muskerrest.dao.especie;

import com.malic.muskerrest.entities.Especie;

import java.util.List;

public interface EspecieDao {

    List<Especie> getAllEspecies();
    Especie getEspecie(long id);
    void editEspecie(Especie especie);
    void deleteEspecie(long id);
    void deleteEspecie(Especie especie);
    void addEspecie(Especie especie);

}
