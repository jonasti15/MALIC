package com.malic.musker.api;

import com.malic.musker.entities.Especie;
import com.malic.musker.entities.Estancia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ImageMethods imgMethods;

    @GetMapping(path="/animals")
    public String allShelterAnimals(Model model) throws IOException {
        List<Estancia> estancias;

        estancias = RestController.RESTgetRequestListHeaders("/estancias/shelter", new HttpHeaders(), Estancia.class);

        for(Estancia estancia : estancias){
            if(!imgMethods.imgExists(estancia.getAnimal().getPath())){
                imgMethods.createImgWithBytes(estancia.getAnimal().getPath());
            }
        }

        model.addAttribute("estancias", estancias);
        model.addAttribute("navPage", "animals");

        return "searchAnimals";
    }

    @GetMapping(path="/species")
    public String zoneEspecies(Model model) throws IOException {
        List<Especie> species;

        species = RestController.RESTgetRequestListHeaders("/especies/all", new HttpHeaders(), Especie.class);

        for(Especie especie : species){
            if(!imgMethods.imgExists(especie.getPath())){
                imgMethods.createImgWithBytes(especie.getPath());
            }
        }

        model.addAttribute("especies", species);
        model.addAttribute("navPage", "species");

        return "searchEspecies";
    }
}
