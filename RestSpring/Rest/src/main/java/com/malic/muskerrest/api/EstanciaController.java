package com.malic.muskerrest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malic.muskerrest.dao.estancia.EstanciaDao;
import com.malic.muskerrest.entities.Animal;
import com.malic.muskerrest.entities.Estancia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/estancias")
public class EstanciaController {

    @Autowired
    private EstanciaDao estanciaDao;

    @GetMapping("/all")
    public ResponseEntity<List<Estancia>> getEstancias() {
        return ResponseEntity.ok(estanciaDao.getAllEstancias());
    }
    @PostMapping("/add")
    public ResponseEntity<Estancia> addEstancia(@RequestBody Estancia estancia,
                                            HttpServletResponse response) throws IOException {
        try{
            estanciaDao.addEstancia(estancia);
        }catch(Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());

            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        return ResponseEntity.ok(estancia);
    }

    @GetMapping("/estancia/{id}")
    public ResponseEntity<Estancia> getEstancia(@PathVariable long id) {
        return ResponseEntity.ok(estanciaDao.getEstancia(id));
    }
    @GetMapping("/animal/{animalId}")
    public ResponseEntity<Estancia> getEstanciaByAnimalId(@PathVariable(name = "animalId") long id) {
        return ResponseEntity.ok(estanciaDao.getEstanciaByAnimalId(id));
    }

    @GetMapping("/shelter")
    public ResponseEntity<List<Estancia>> getAllAnimalsShelter(){
        return ResponseEntity.ok(estanciaDao.getActiveEstancias());
    }

}
