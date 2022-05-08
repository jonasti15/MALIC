package com.malic.muskerrest.dao.avistamiento;

import com.malic.muskerrest.entities.Avistamiento;

import java.util.List;

public interface AvistamientoDao {

    List<Avistamiento> getAllAvistamientos();
    Avistamiento getAvistamiento(long id);
    void editAvistamiento(Avistamiento avistamiento);
    void deleteAvistamiento(long id);
    void deleteAvistamiento(Avistamiento avistamiento);
    void addAvistamiento(Avistamiento avistamiento);

}
