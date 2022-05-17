package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.reserva.ReservaDao;
import com.malic.muskerrest.dao.visita.VisitaDao;
import com.malic.muskerrest.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/reservas")
public class ReservaController {

    @Autowired
    private ReservaDao reservaDao;

    @Autowired
    private VisitaDao visitaDao;

    @GetMapping("/all")
    public ResponseEntity<List<Reserva>> getReservas() {
        return ResponseEntity.ok(reservaDao.getAllReserva());
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<Reserva> getReserva(@PathVariable long id) {
        return ResponseEntity.ok(reservaDao.getReserva(id));
    }

    @GetMapping("/count/{visitaId}")
    public ResponseEntity<Integer> countPersonasVisita(@PathVariable Long visitaId){
        return ResponseEntity.ok(reservaDao.countPersonsasVisita(visitaId));
    }

    @PostMapping("/add")
    public ResponseEntity<Reserva> addReserva(@RequestBody Reserva reserva,
                                              HttpServletResponse response){

        reserva.setVisita(visitaDao.getVisita(reserva.getVisita().getVisitaId()));

        reservaDao.addReserva(reserva);
        return ResponseEntity.ok(reserva);
    }

}
