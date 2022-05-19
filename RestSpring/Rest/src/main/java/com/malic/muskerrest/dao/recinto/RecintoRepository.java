package com.malic.muskerrest.dao.recinto;

import com.malic.muskerrest.entities.Recinto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RecintoRepository extends JpaRepository<Recinto, Integer> {
    List<Recinto> findAll();
}
