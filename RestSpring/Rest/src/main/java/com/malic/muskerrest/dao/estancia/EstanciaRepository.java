package com.malic.muskerrest.dao.estancia;

import com.malic.muskerrest.entities.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstanciaRepository extends JpaRepository<Estancia, Long> {
    List<Estancia> getEstanciasByFechaSalidaIsNull();
}
