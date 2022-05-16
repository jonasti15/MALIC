package com.malic.muskerrest.dao.estancia;

import com.malic.muskerrest.entities.Estancia;

import java.util.List;

public interface EstanciaDao {

    List<Estancia> getAllEstancias();
    Estancia getEstancia(long id);
    void editEstancia(Estancia estancia);
    void deleteEstancia(long id);
    void deleteEstancia(Estancia estancia);
    void addEstancia(Estancia estancia);
    List<Estancia> getActiveEstancias();
}
