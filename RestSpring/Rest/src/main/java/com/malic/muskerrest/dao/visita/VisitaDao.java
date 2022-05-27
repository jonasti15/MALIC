package com.malic.muskerrest.dao.visita;

import com.malic.muskerrest.entities.Reserva;
import com.malic.muskerrest.entities.Visita;

import java.util.List;

public interface VisitaDao {

    List<Visita> getAllVisitas();
    Visita getVisita(long id);
    void editVisita(Visita visita);
    void deleteVisita(long id);
    void deleteVisita(Visita visita);
    void addVisita(Visita visita);
    List<Visita> getVisitasDisponibles();
    List<Visita> getVisitasEditables();


}
