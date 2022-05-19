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
        List<Visita> visitas = visitaDao.getVisitasDisponibles();

        for(Visita v : visitas){
            v.setGuia(cleanGuia(v.getGuia()));
        }

        return ResponseEntity.ok(visitas);
    }

    @GetMapping("/visita/{id}")
    public ResponseEntity<Visita> getVisita(@PathVariable int id){
        Visita visita = visitaDao.getVisita(id);
        if(visita != null){
            visita.setGuia(cleanGuia(visita.getGuia()));
        }

        return ResponseEntity.ok(visita);
    }

    public static User cleanGuia(User user){
        User newUser = new User();

        newUser.setUserId(user.getUserId());
        newUser.setNombre(user.getNombre());
        newUser.setApellido(user.getApellido());

        return newUser;
    }
}
