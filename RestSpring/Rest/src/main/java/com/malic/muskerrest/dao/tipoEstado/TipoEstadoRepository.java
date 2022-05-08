package com.malic.muskerrest.dao.tipoEstado;

import com.malic.muskerrest.entities.TipoEstado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEstadoRepository extends JpaRepository<TipoEstado, Integer> {
    TipoEstado findTipoEstadoByDescripcion(String descripcion);
}
