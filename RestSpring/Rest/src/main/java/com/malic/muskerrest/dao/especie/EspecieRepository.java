package com.malic.muskerrest.dao.especie;

import com.malic.muskerrest.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
