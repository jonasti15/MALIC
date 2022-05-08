package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.clase.ClaseDao;
import com.malic.muskerrest.dao.userType.UserTypeDao;
import com.malic.muskerrest.entities.Clase;
import com.malic.muskerrest.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/clases")
public class ClaseController {

    @Autowired
    private ClaseDao claseDao;

    @GetMapping("/all")
    public ResponseEntity<List<Clase>> getAllClases(){
        return ResponseEntity.ok(claseDao.getAllClases());
    }

    @GetMapping("/clase/{id}")
    public ResponseEntity<Clase> getClase(@PathVariable int id){
        return ResponseEntity.ok(claseDao.getClase(id));
    }
}
