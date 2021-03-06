package com.malic.musker.api;

import com.malic.musker.entities.Reserva;
import com.malic.musker.entities.User;
import com.malic.musker.entities.Visita;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/visitas")
public class VisitaController {

    @GetMapping(path="/all")
    public String getVisitas(Model model){
        String error = "";
        String returnStr = "/";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        List<Visita> visitas = RestController.RESTgetRequestListHeaders("/visitas/all", headers, Visita.class);

        if(visitas == null){
            error = "Error inesperado";
        }else{
            returnStr = "/visitas";
            model.addAttribute("navPage", "visitas");
            model.addAttribute("visitas", visitas);
        }

        return returnStr;
    }

}
