package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.constantes.ConstantesDao;
import com.malic.muskerrest.entities.Constantes;
import com.malic.muskerrest.entitiesDTO.ConstantesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/post")
    @ResponseBody
    public void postConstantes(@RequestBody ConstantesDTO constantesDto){
        Constantes constantes = new Constantes();
        constantes.setAnimalId(constantesDto.getAnimalId());
        constantes.setLatidos(constantesDto.getLatidos());
        constantes.setTemperatura(constantesDto.getTemperatura());
        constantes.setTensArterial(constantesDto.getTensArterial());
        constantes.setFecha(constantesDto.getFecha());
        constantes.setFrrespiracion(constantesDto.getFrrespiracion());
        constantes.setSaturacionO2(constantesDto.getSaturacionO2());
        System.out.println(constantes);
        constantesDao.addConstantes(constantes);
    }
}
