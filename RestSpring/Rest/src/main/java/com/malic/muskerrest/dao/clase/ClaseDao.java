package com.malic.muskerrest.dao.clase;

import com.malic.muskerrest.entities.Clase;

import java.util.List;

public interface ClaseDao {

    List<Clase> getAllClases();
    Clase getClase(int id);
    void editClase(Clase clase);
    void deleteClase(int id);
    void deleteClase(Clase clase);
    void addClase(Clase clase);

}
