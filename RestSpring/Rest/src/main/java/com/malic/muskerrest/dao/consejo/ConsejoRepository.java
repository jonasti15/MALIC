package com.malic.muskerrest.dao.consejo;

import com.malic.muskerrest.entities.Consejo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsejoRepository extends JpaRepository<Consejo, Long> {
    List<Consejo> getConsejosByEspecie_EspecieId(Long id);
}
