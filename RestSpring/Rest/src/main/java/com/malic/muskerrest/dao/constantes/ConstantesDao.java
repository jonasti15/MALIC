package com.malic.muskerrest.dao.constantes;

import com.malic.muskerrest.entities.Constantes;

import java.util.List;

public interface ConstantesDao {

    List<Constantes> getAllConstantes();
    Constantes getConstantes(long id);
    void editConstantes(Constantes constante);
    void deleteConstantes(long id);
    void deleteConstantes(Constantes constante);
    void addConstantes(Constantes constante);

}
