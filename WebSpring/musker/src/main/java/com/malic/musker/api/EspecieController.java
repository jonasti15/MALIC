package com.malic.musker.api;

import com.malic.musker.entities.Consejo;
import com.malic.musker.entities.Especie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/especies")
public class EspecieController {

    @GetMapping(path="/especie/{especieId}")
    public String getEspecie(Model model,
                             @PathVariable("especieId") String especieId){
        String error = "";
        String returnStr = "/";

        Especie especie = RestController.RESTgetRequestHeaders("/especies/especie/" + especieId, new HttpHeaders(), Especie.class);
        List<Consejo> consejos = RestController.RESTgetRequestListHeaders("/consejos/especie/" + especieId, new HttpHeaders(), Consejo.class);

        if(especie == null || consejos == null){
            error = "Error inesperado";
        }else{
            returnStr = "/especie";
            model.addAttribute("especie", especie);
            model.addAttribute("consejos", consejos);
        }

        return returnStr;
    }
}
