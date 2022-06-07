package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.avistamiento.AvistamientoDao;
import com.malic.muskerrest.entities.Avistamiento;
import com.malic.muskerrest.entitiesDTO.AvistamientoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<Avistamiento> addAvistamiento(@RequestBody AvistamientoDTO avistamientoDto){
        Avistamiento avistamiento = new Avistamiento();
        avistamiento.setDescripcion(avistamientoDto.getDescripcion());
        avistamiento.setEspecie(avistamientoDto.getEspecie());
        avistamiento.setLocalizacion(avistamientoDto.getLocalizacion());
        avistamiento.setFecha(avistamientoDto.getFecha());

        avistamientoDao.addAvistamiento(avistamiento);

        return ResponseEntity.ok(avistamiento);
    }

}
