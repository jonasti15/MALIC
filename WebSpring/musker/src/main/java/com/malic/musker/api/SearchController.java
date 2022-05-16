package com.malic.musker.api;

import com.malic.musker.entities.Animal;
import com.malic.musker.entities.Especie;
import com.malic.musker.entities.Estancia;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping(path="/animals")
    public String allShelterAnimals(Model model){
        List<Estancia> estancias;

        estancias = RestController.RESTgetRequestListHeaders("/estancias/shelter", new HttpHeaders(), Estancia.class);

        model.addAttribute("estancias", estancias);

        return "searchAnimals";
    }

    @GetMapping(path="/species")
    public String zoneEspecies(Model model){
        List<Especie> species;

        species = RestController.RESTgetRequestListHeaders("/especies/all", new HttpHeaders(), Especie.class);

        model.addAttribute("species", species);

        return "/";
    }
}
