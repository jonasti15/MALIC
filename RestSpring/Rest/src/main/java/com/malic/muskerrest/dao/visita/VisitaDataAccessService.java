package com.malic.muskerrest.dao.visita;

import com.malic.muskerrest.entities.Reserva;
import com.malic.muskerrest.entities.Visita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitaDataAccessService implements VisitaDao{

    @Autowired
    VisitaRepository repository;

    @Override
    public List<Visita> getAllVisitas() {
        return repository.findAll();
    }

    @Override
    public Visita getVisita(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editVisita(Visita visita) {
        repository.save(visita);
    }

    @Override
    public void deleteVisita(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteVisita(Visita visita) {
        repository.delete(visita);
    }

    @Override
    public void addVisita(Visita visita) {
        repository.save(visita);
    }

    @Override
    public List<Visita> getVisitasDisponibles() {
        return repository.visitasDisponibles();
    }

    @Override
    public List<Visita> getVisitasEditables() {
        java.util.Date utilDate = new  java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return repository.findAllByFechaAfterOrderByFechaAsc(sqlDate);
    }


}
