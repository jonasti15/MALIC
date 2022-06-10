package com.malic.muskerrest.dao.recinto;

import com.malic.muskerrest.entities.Recinto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RecintoRepository extends JpaRepository<Recinto, Integer> {
    List<Recinto> findAll();
    @Query(value = "SELECT COUNT(a.animal_id) AS n_estancia " +
            "FROM recinto r " +
            "LEFT JOIN animal a ON a.recinto_id = r.recinto_id " +
            "LEFT JOIN estancia e on a.animal_id = e.animal_id " +
            "where r.recinto_id=?1 " +
            "and e.estancia_id = (SELECT MAX(e2.estancia_id) from estancia e2 where e2.animal_id = a.animal_id) " +
            "and e.fecha_salida IS NULL " +
            "GROUP BY r.recinto_id;", nativeQuery =  true)
    int cantidadEstancias(int id);
}
