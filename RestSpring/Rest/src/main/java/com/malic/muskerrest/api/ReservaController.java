package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.reserva.ReservaDao;
import com.malic.muskerrest.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/reservas")
public class ReservaController {

    @Autowired
    private ReservaDao reservaDao;

    @GetMapping("/all")
    public ResponseEntity<List<Reserva>> getReservas() {
        return ResponseEntity.ok(reservaDao.getAllReserva());
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<Reserva> getReserva(@PathVariable long id) {
        return ResponseEntity.ok(reservaDao.getReserva(id));
    }

}
