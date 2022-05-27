package com.malic.muskerrest.dao.visita;

import com.malic.muskerrest.entities.Reserva;
import com.malic.muskerrest.entities.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
    List<Visita> findAllByFechaAfterOrderByFechaAsc(Date date);


    @Query(value = "select v.* " +
            "from visita v " +
            "LEFT JOIN reserva r on v.visita_id = r.visita_id " +
            "where v.fecha > curdate() " +
            "group by v.visita_id " +
            "having sum(r.cantidad_personas) < 10 or sum(r.cantidad_personas) IS NULL", nativeQuery =  true)
    List<Visita> visitasDisponibles();
}
