package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.tipoEstado.TipoEstadoDao;
import com.malic.muskerrest.dao.tipoEstado.TipoEstadoRepository;
import com.malic.muskerrest.dao.userType.UserTypeDao;
import com.malic.muskerrest.entities.TipoEstado;
import com.malic.muskerrest.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tipoestado")
public class TipoEstadoController {

    @Autowired
    private TipoEstadoDao tipoEstadoDao;

    @GetMapping("/all")
    public ResponseEntity<List<TipoEstado>> getAllTipoEstado(){
        return ResponseEntity.ok(tipoEstadoDao.getAllTipoEstado());
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<TipoEstado> getTipoEstado(@PathVariable int id){
        return ResponseEntity.ok(tipoEstadoDao.getTipoEstado(id));
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<TipoEstado> getTipoEstadoByAnimalId(@PathVariable long id){
        return ResponseEntity.ok(tipoEstadoDao.getTipoEstadoByAnimalId(id));
    }
}
