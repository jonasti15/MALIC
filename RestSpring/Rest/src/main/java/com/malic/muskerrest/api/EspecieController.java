package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.especie.EspecieDao;
import com.malic.muskerrest.entities.Especie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/especies")
public class EspecieController {

    @Autowired
    private EspecieDao especieDao;

    @GetMapping("/all")
    public ResponseEntity<List<Especie>> getEspecies() {
        return ResponseEntity.ok(especieDao.getAllEspecies());
    }

    @GetMapping("/especie/{id}")
    public ResponseEntity<Especie> getEspecie(@PathVariable long id) {
        return ResponseEntity.ok(especieDao.getEspecie(id));
    }

}
