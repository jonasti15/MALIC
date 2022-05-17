package com.malic.muskerrest.dao.reserva;

import com.malic.muskerrest.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query(value = "SELECT SUM(r.cantidad_personas) FROM reserva r where r.visita_id = ?1", nativeQuery =  true)
    Integer countCantidadPersonas(Long visitaId);
}
