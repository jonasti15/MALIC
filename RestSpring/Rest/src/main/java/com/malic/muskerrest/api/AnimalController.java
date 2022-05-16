package com.malic.muskerrest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malic.muskerrest.dao.animal.AnimalDao;
import com.malic.muskerrest.dao.estancia.EstanciaDao;
import com.malic.muskerrest.entities.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/animals")
public class AnimalController {

    @Autowired
    private AnimalDao animalDao;

    @GetMapping("/all")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        return ResponseEntity.ok(animalDao.getAllAnimals());
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable int id){
        return ResponseEntity.ok(animalDao.getAnimal(id));
    }

}
