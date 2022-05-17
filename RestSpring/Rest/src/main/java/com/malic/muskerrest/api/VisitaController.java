package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.visita.VisitaDao;
import com.malic.muskerrest.entities.User;
import com.malic.muskerrest.entities.Visita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/visitas")
public class VisitaController {
    @Autowired
    private VisitaDao visitaDao;

    @GetMapping("/all")
    public ResponseEntity<List<Visita>> getAllVisita(){
        List<Visita> visitas = visitaDao.getFechaAfterDate();

        for(Visita v : visitas){
            User user = new User();
            user.setUsuario_id(v.getGuia().getUsuario_id());
            user.setNombre(v.getGuia().getNombre());
            user.setApellido(v.getGuia().getApellido());
            v.setGuia(user);
        }

        return ResponseEntity.ok(visitas);
    }

    @GetMapping("/visita/{id}")
    public ResponseEntity<Visita> getVisita(@PathVariable int id){
        Visita visita = visitaDao.getVisita(id);
        if(visita != null){
            User user = new User();

            user.setUsuario_id(visita.getGuia().getUsuario_id());
            user.setNombre(visita.getGuia().getNombre());
            user.setApellido(visita.getGuia().getApellido());
            visita.setGuia(user);
        }

        return ResponseEntity.ok(visita);
    }
}
