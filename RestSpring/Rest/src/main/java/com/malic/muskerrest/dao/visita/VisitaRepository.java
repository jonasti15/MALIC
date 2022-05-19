package com.malic.muskerrest.dao.visita;

import com.malic.muskerrest.entities.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface VisitaRepository extends JpaRepository<Visita, Long> {

    @Query(value = "SELECT v.* " +
            "FROM visita v " +
            "JOIN reserva r on v.visita_id = r.visita_id " +
            "WHERE v.fecha > CURDATE() " +
            "GROUP BY v.visita_id " +
            "HAVING SUM(r.cantidad_personas) < 10 ", nativeQuery =  true)
    List<Visita> visitasDisponibles();
}
