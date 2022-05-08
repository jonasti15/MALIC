package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.constantes.ConstantesDao;
import com.malic.muskerrest.entities.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/constantes")
public class ConstantesController {

    @Autowired
    private ConstantesDao constantesDao;

    @GetMapping("/all")
    public ResponseEntity<List<Constantes>> getAllConstantes(){
        return ResponseEntity.ok(constantesDao.getAllConstantes());
    }

    @GetMapping("/constante/{id}")
    public ResponseEntity<Constantes> getConstantes(@PathVariable int id){
        return ResponseEntity.ok(constantesDao.getConstantes(id));
    }

}
