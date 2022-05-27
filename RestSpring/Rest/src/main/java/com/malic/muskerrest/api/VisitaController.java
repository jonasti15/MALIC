package com.malic.muskerrest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malic.muskerrest.dao.reserva.ReservaDao;
import com.malic.muskerrest.dao.visita.VisitaDao;
import com.malic.muskerrest.entities.Animal;
import com.malic.muskerrest.entities.Reserva;
import com.malic.muskerrest.entities.User;
import com.malic.muskerrest.entities.Visita;
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
@RequestMapping(path = "/visitas")
public class VisitaController {
    @Autowired
    private VisitaDao visitaDao;
    @Autowired
    private ReservaDao reservaDao;

    @GetMapping("/all")
    public ResponseEntity<List<Visita>> getAllVisita(){
        List<Visita> visitas = visitaDao.getVisitasDisponibles();

        for(Visita v : visitas){
            v.setGuia(cleanGuia(v.getGuia()));
        }

        return ResponseEntity.ok(visitas);
    }
    @GetMapping("/editables")
    public ResponseEntity<List<Visita>> getAllVisitaEditables(){
        List<Visita> visitas = visitaDao.getVisitasEditables();

        for(Visita v : visitas){
            v.setGuia(cleanGuia(v.getGuia()));
        }

        return ResponseEntity.ok(visitas);
    }
    @PostMapping("/edit")
    public ResponseEntity<Visita> editAnimal(@RequestBody Visita visita,
                                             HttpServletResponse response) throws IOException {
        try{
            visitaDao.editVisita(visita);
        }catch(Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());

            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        return ResponseEntity.ok(visita);
    }

    @GetMapping("/visita/{id}")
    public ResponseEntity<Visita> getVisita(@PathVariable int id){
        Visita visita = visitaDao.getVisita(id);
        if(visita != null){
            visita.setGuia(cleanGuia(visita.getGuia()));
        }

        return ResponseEntity.ok(visita);
    }
    @PostMapping("/add")
    public ResponseEntity<Visita> addVisita(@RequestBody Visita visita,
                                        HttpServletResponse response) throws IOException {
        try{
            visitaDao.addVisita(visita);
        }catch(Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());

            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        return ResponseEntity.ok(visita);
    }
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Visita> editAnimal(@PathVariable(value="id")  Long id_visita,
                                             HttpServletResponse response) throws IOException {
        try{
            List<Reserva> listaRelacionados=reservaDao.getReservasDeVisita(id_visita);
            listaRelacionados.forEach(v->reservaDao.deleteReserva(v.getReserva_id()));
            visitaDao.deleteVisita(id_visita);
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


    public static User cleanGuia(User user){
        User newUser = new User();

        newUser.setUserId(user.getUserId());
        newUser.setNombre(user.getNombre());
        newUser.setApellido(user.getApellido());

        return newUser;
    }
}
