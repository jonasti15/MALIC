package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.visita.VisitaDao;
import com.malic.muskerrest.entities.Visita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/visitas")
public class VisitaController {
    @Autowired
    private VisitaDao visitaDao;

    @GetMapping("/all")
    public ResponseEntity<List<Visita>> getAllVisita(){
        return ResponseEntity.ok(visitaDao.getAllVisitas());
    }

    @GetMapping("/visita/{id}")
    public ResponseEntity<Visita> getVisita(@PathVariable int id){
        return ResponseEntity.ok(visitaDao.getVisita(id));
    }
}
