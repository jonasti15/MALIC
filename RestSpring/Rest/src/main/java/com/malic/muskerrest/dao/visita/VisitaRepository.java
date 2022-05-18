package com.malic.muskerrest.dao.visita;

import com.malic.muskerrest.entities.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
    List<Visita> findAllByFechaAfterOrderByFechaAsc(Date date);
}
