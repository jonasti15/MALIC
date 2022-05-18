package com.malic.muskerrest.dao.consejo;

import com.malic.muskerrest.entities.Consejo;

import java.util.List;

public interface ConsejoDao {
    
    List<Consejo> getAllConsejos();
    Consejo getConsejo(Long id);
    void editConsejo(Consejo Consejo);
    void deleteConsejo(Long id);
    void deleteConsejo(Consejo Consejo);
    void addConsejo(Consejo Consejo);
    List<Consejo> getConsejosByEspecie(Long especieId);
}
