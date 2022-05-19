package com.malic.musker.api;

import com.malic.musker.entities.Reserva;
import com.malic.musker.entities.User;
import com.malic.musker.entities.Visita;
import org.springframework.boot.Banner;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final static int MAX_PERSONAS = 10;

    @GetMapping(path="/user")
    public String getReservasUsuario(Model model){
        String returnStr = "misReservas";

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        List<Reserva> reservas = RestController.RESTgetRequestListHeaders("/reservas/user", header, Reserva.class);

        model.addAttribute("reservas", reservas);

        return returnStr;
    }

    @GetMapping(path="/visita/{visitaId}")
    public String getVisitaParaReserva(Model model,
                                       @PathVariable("visitaId") String visitaId,
                                       @RequestParam(value = "error", required = false) String error) {
        String returnStr = "/";

        Visita visita = RestController.RESTgetRequestHeaders("/visitas/visita/" + visitaId, new HttpHeaders(), Visita.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = authentication.getName();

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        User user = RestController.RESTgetRequestHeaders("/user/username/"+loggedUsername, header, User.class);

        if(user == null){
            error += " Usuario incorrecto";
            return "/";
        }

        Reserva reserva = new Reserva();

        reserva.setUser(user);
        reserva.setVisita(visita);
        reserva.setCantidad_personas(1);

        if(reserva == null){
            error += " Error inesperado";
        }else{
            model.addAttribute("reserva", reserva);
            model.addAttribute("error", error);
            int maxPersonas = 10 - RestController.RESTgetRequestHeaders("/reservas/count/"+visitaId, new HttpHeaders(), Integer.class);
            model.addAttribute("maximo", maxPersonas);
            returnStr = "/reservation";
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

        // Compruebo si el usuario ya tiene alguna reserva para esa visita
        List<Reserva> reservas = RestController.RESTgetRequestListHeaders("/reservas/user", header, Reserva.class);
        for(Reserva r : reservas){
            if(r.getVisita().getVisitaId() == reserva.getVisita().getVisitaId()){
                error = "Ya tienes una reserva para esta visita";
                returnStr = "redirect:/reservas/visita/"+reserva.getVisita().getVisitaId()+"?error="+error;
                return new ModelAndView(returnStr, new ModelMap(model));
            }
        }


        Visita visita = RestController.RESTgetRequestHeaders("/visitas/visita/"+reserva.getVisita().getVisitaId(), header, Visita.class);

        User user = RestController.RESTgetRequestHeaders("/user/user/"+reserva.getUser().getUserId(), header, User.class);

        reserva.setVisita(visita);
        reserva.setUser(user);

        // Obtengo el numero de personas que van a acudir a la visita. Si el numero de personas es mayor que 10, no se permite hacer la reserva
        if((reserva.getCantidad_personas() +
                RestController.RESTgetRequestHeaders("/reservas/count/"+reserva.getVisita().getVisitaId(), new HttpHeaders(), Integer.class)) > MAX_PERSONAS){
            error = "No pueden ir esa cantidad de personas";
            returnStr = "redirect:/reservas/visita/"+visita.getVisitaId()+"?error="+error;
            return new ModelAndView(returnStr, new ModelMap(model));
        }

        if(user == null){
            error += " Usuario incorrecto";
            returnStr = "redirect:/reservas/visita/"+visita.getVisitaId()+"?error="+error;
            return new ModelAndView(returnStr, new ModelMap(model));
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

    @PostMapping(path="/delete/{reservaId}")
    public ModelAndView deleteReserva(Model model,
                                      @PathVariable("reservaId") String reservaId){

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        Reserva reserva = RestController.RESTgetRequestHeaders("/reservas/reserva/"+reservaId, header, Reserva.class);

        String message = RestController.RESTdeleteRequest("/reservas/delete", reserva, String.class);

        return new ModelAndView("redirect:/", new ModelMap(model));
    }
}
