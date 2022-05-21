package com.malic.muskerrest.dao.avistamiento;

import com.malic.muskerrest.entities.Avistamiento;
import com.malic.muskerrest.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvistamientoRepository extends JpaRepository<Avistamiento, Long> {
}
