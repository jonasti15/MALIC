package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.avistamiento.AvistamientoDao;
import com.malic.muskerrest.entities.Avistamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/avistamientos")
public class AvistamientoController {

    @Autowired
    private AvistamientoDao avistamientoDao;

    @GetMapping("/all")
    public ResponseEntity<List<Avistamiento>> getAllAvistamientos(){
        return ResponseEntity.ok(avistamientoDao.getAllAvistamientos());
    }

    @GetMapping("/avistamiento/{id}")
    public ResponseEntity<Avistamiento> getAvistamiento(@PathVariable int id){
        return ResponseEntity.ok(avistamientoDao.getAvistamiento(id));
    }

}
