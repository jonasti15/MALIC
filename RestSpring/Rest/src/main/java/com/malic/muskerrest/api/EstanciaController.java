package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.estancia.EstanciaDao;
import com.malic.muskerrest.entities.Estancia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estancias")
public class EstanciaController {

    @Autowired
    private EstanciaDao estanciaDao;

    @GetMapping("/all")
    public ResponseEntity<List<Estancia>> getEstancias() {
        return ResponseEntity.ok(estanciaDao.getAllEstancias());
    }

    @GetMapping("/estancia/{id}")
    public ResponseEntity<Estancia> getEstancia(@PathVariable long id) {
        return ResponseEntity.ok(estanciaDao.getEstancia(id));
    }

}
