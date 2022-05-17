package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.consejo.ConsejoDao;
import com.malic.muskerrest.entities.Consejo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/consejos")
public class ConsejoController {
    @Autowired
    private ConsejoDao consejoDao;

    @GetMapping("/all")
    public ResponseEntity<List<Consejo>> getAllConsejos(){
        return ResponseEntity.ok(consejoDao.getAllConsejos());
    }

    @GetMapping("/consejo/{id}")
    public ResponseEntity<Consejo> getConsejo(@PathVariable Long id){
        return ResponseEntity.ok(consejoDao.getConsejo(id));
    }

    @GetMapping("/especie/{id}")
    public ResponseEntity<List<Consejo>> getConsejosByEspecie(@PathVariable Long id){
        return ResponseEntity.ok(consejoDao.getConsejosByEspecie(id));
    }
}
