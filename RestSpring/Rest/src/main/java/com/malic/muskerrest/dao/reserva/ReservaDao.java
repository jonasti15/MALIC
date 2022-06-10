package com.malic.muskerrest.dao.reserva;

import com.malic.muskerrest.entities.Reserva;

import java.util.List;

public interface ReservaDao {

    List<Reserva> getAllReserva();
    Reserva getReserva(Long id);
    void editReserva(Reserva reserva);
    void deleteReserva(Reserva reserva);
    void deleteReserva(Long id);
    void addReserva(Reserva reserva);
    int countPersonsasVisita(Long visitaId);
    List<Reserva> getReservasByUserAndFecha(Long userId);

    List<Reserva> getReservasDeVisita(Long id_visita);
}
