package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.recinto.RecintoDao;
import com.malic.muskerrest.entities.Recinto;
import com.malic.muskerrest.entities.TipoEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/recintos")
public class RecintoController {

    @Autowired
    private RecintoDao recintoDao;

    @GetMapping("/all")
    public ResponseEntity<List<Recinto>> getAllRecinto(){
        return ResponseEntity.ok(recintoDao.getAllRecinto());
    }

    @GetMapping("/recinto/{id}")
    public ResponseEntity<Recinto> getRecinto(@PathVariable int id){
        return ResponseEntity.ok(recintoDao.getRecinto(id));
    }

}
