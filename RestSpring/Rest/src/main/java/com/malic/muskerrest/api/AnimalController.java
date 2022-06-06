package com.malic.muskerrest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malic.muskerrest.dao.animal.AnimalDao;
import com.malic.muskerrest.dao.estancia.EstanciaDao;
import com.malic.muskerrest.entities.Animal;
import com.malic.muskerrest.entities.Visita;
import com.malic.muskerrest.entitiesDTO.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @GetMapping("/last")
    public ResponseEntity<Animal> getLastAnimal(){
        return ResponseEntity.ok(animalDao.findLastAnimal());
    }

    @PostMapping("/add")
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalDTO animalDTO,
                                          HttpServletResponse response) throws IOException {
        Animal animal = new Animal();
        animal.setAnimalId(animalDTO.getAnimalId());
        animal.setEspecie(animalDTO.getEspecie());
        animal.setEstado(animalDTO.getEstado());
        animal.setEstadoIa(animalDTO.getEstadoIa());
        animal.setPath(animalDTO.getPath());
        try{

            animalDao.addAnimal(animal);
        }catch(Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());

            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        return ResponseEntity.ok(animal);
    }
    @PostMapping("/edit")
    public ResponseEntity<Animal> editAnimal(@RequestBody Animal animal,
                                            HttpServletResponse response) throws IOException {
        try{
            animalDao.editAnimal(animal);
        }catch(Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());

            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        return ResponseEntity.ok(animal);
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Animal> editAnimal(@PathVariable(value="id")  Long id_animal,
                                             HttpServletResponse response) throws IOException {
        try{
            animalDao.deleteAnimal(id_animal);
        }catch(Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());

            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        return ResponseEntity.ok().build();
    }


}
