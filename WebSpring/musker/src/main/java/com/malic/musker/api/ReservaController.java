package com.malic.musker.api;

import com.malic.musker.entities.Reserva;
import com.malic.musker.entities.User;
import com.malic.musker.entities.Visita;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final static int MAX_PERSONAS = 10;

    @GetMapping(path="/visita/{visitaId}")
    public String getVisitaParaReserva(Model model,
                                        @PathVariable("visitaId") String visitaId){
        String error = "";
        String returnStr = "/";

        Visita visita = RestController.RESTgetRequestHeaders("/visitas/visita/" + visitaId, new HttpHeaders(), Visita.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = authentication.getName();

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        User user = RestController.RESTgetRequestHeaders("/user/username/"+loggedUsername, header, User.class);

        if(user == null){
            error = "Usuario incorrecto";
            return "/";
        }

        Reserva reserva = new Reserva();

        reserva.setUser(user);
        reserva.setVisita(visita);
        reserva.setCantidad_personas(1);

        if(reserva == null){
            error = "Error inesperado";
        }else{
            returnStr = "/reservation";
            model.addAttribute("reserva", reserva);
        }

        return returnStr;
    }

    @PostMapping(path="/add")
    public ModelAndView addNewReservation (Model model,
                                           @ModelAttribute Reserva reserva) {
        String error = "";
        String returnStr = "redirect:/";

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        Visita visita = RestController.RESTgetRequestHeaders("/visitas/visita/"+reserva.getVisita().getVisitaId(), header, Visita.class);

        User user = RestController.RESTgetRequestHeaders("/user/user/"+reserva.getUser().getUsuario_id(), header, User.class);

        reserva.setVisita(visita);
        reserva.setUser(user);

        // Obtengo el numero de personas que van a acudir a la visita. Si el numero de personas es mayor que 10, no se permite hacer la reserva
        if((reserva.getCantidad_personas() +
                RestController.RESTgetRequestHeaders("/reservas/count/"+reserva.getVisita().getVisitaId(), new HttpHeaders(), Integer.class)) >= 10){
            error = "No pueden ir esa cantidad de personas";
            returnStr = "redirect:/reservation";
            model.addAttribute("reserva", reserva);
            model.addAttribute("error", error);
            return new ModelAndView(returnStr, new ModelMap(model));
        }

        if(user == null){
            error += " Usuario incorrecto";
        }

        if(error.length() == 0){
            Reserva reservaCreado = RestController.RESTpostRequest("/reservas/add", reserva, Reserva.class);
            if(reservaCreado != null){
                error = "";
                returnStr = "redirect:/";
            }
        }

        return new ModelAndView(returnStr, new ModelMap(model));
    }

}
