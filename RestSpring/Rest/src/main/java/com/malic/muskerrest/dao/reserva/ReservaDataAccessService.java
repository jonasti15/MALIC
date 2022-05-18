package com.malic.muskerrest.dao.reserva;

import com.malic.muskerrest.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDataAccessService implements ReservaDao{

    @Autowired
    ReservaRepository repository;

    @Override
    public List<Reserva> getAllReserva() {
        return repository.findAll();
    }

    @Override
    public Reserva getReserva(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void editReserva(Reserva reserva) {
        repository.save(reserva);
    }

    @Override
    public void deleteReserva(Reserva reserva) {
        repository.delete(reserva);
    }

    @Override
    public void deleteReserva(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addReserva(Reserva reserva) {
        repository.save(reserva);
    }

    @Override
    public int countPersonsasVisita(Long visitaId) {
        return repository.countCantidadPersonas(visitaId);
    }
}
