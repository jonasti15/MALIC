package com.malic.muskerrest.dao.reserva;

import com.malic.muskerrest.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
