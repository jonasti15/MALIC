package com.malic.muskerrest.dao.tipoEstado;

import com.malic.muskerrest.entities.TipoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoEstadoRepository extends JpaRepository<TipoEstado, Integer> {
    TipoEstado findTipoEstadoByDescripcion(String descripcion);
    List<TipoEstado> findAll();
    @Query(value = "SELECT te.* " +
            "  FROM tipo_estado te " +
            "  JOIN animal a on te.estado_id = a.estado_ia_id " +
            " WHERE a.animal_id = ?1", nativeQuery = true)
    TipoEstado findTipoEstadoByAnimalId(long id);
}
