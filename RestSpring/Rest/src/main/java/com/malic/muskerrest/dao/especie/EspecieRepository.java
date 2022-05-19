package com.malic.muskerrest.dao.especie;

import com.malic.muskerrest.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
    List<Especie> findAll();
}
