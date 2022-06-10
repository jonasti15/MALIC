package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.reserva.ReservaDao;
import com.malic.muskerrest.dao.user.UserDao;
import com.malic.muskerrest.dao.visita.VisitaDao;
import com.malic.muskerrest.entities.Reserva;
import com.malic.muskerrest.entities.User;
import com.malic.muskerrest.entitiesDTO.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserDao userDao;

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

    @GetMapping("/user")
    public ResponseEntity<List<Reserva>> getReservasUsuario(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = authentication.getName();

        User user = userDao.getUserByUsername(loggedUsername);
        List<Reserva> reservas = reservaDao.getReservasByUserAndFecha(user.getUserId());

        for(Reserva r : reservas){
            r.getVisita().setGuia(VisitaController.cleanGuia(r.getVisita().getGuia()));
        }

        return ResponseEntity.ok(reservas);
    }

    @PostMapping("/add")
    public ResponseEntity<Reserva> addReserva(@RequestBody ReservaDTO reservaDto,
                                              HttpServletResponse response){

        Reserva reserva = new Reserva();
        reserva.setUser(reservaDto.getUser());
        reserva.setVisita(reservaDto.getVisita());
        reserva.setCantidad_personas(reservaDto.getCantidad_personas());

        reserva.setVisita(visitaDao.getVisita(reserva.getVisita().getVisitaId()));

        reservaDao.addReserva(reserva);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReserva(@RequestBody ReservaDTO reservaDto){
        Reserva reserva = new Reserva();
        reserva.setReserva_id(reservaDto.getReserva_id());
        reserva.setUser(reservaDto.getUser());
        reserva.setVisita(reservaDto.getVisita());
        reserva.setCantidad_personas(reservaDto.getCantidad_personas());

        reservaDao.deleteReserva(reserva.getReserva_id());

        return ResponseEntity.ok("Reserva borrada");
    }

}
