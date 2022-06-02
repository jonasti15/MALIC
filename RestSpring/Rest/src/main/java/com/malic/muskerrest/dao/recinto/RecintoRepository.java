package com.malic.muskerrest.dao.recinto;

import com.malic.muskerrest.entities.Recinto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RecintoRepository extends JpaRepository<Recinto, Integer> {
    List<Recinto> findAll();
    @Query(value = "SELECT COUNT(a.animal_id) AS n_estancia FROM recinto r LEFT JOIN animal a ON a.recinto_id = r.recinto_id where r.recinto_id=?1 GROUP BY r.recinto_id", nativeQuery =  true)
    int cantidadEstancias(int id);
}
