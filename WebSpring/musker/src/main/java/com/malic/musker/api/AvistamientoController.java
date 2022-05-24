package com.malic.musker.api;

import com.google.gson.Gson;
import com.malic.musker.comunication.MessagePublisher;
import com.malic.musker.entities.Avistamiento;
import com.malic.musker.entities.Especie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/avistamientos")
public class AvistamientoController {

    @Autowired
    MessagePublisher publisher;

    @GetMapping(path="/add")
    public String avistamientoForm(Model model,
                                   @RequestParam(value = "especie", required = false) String especie,
                                   @RequestParam(value = "error", required = false) String error) {

        Avistamiento avistamiento = new Avistamiento();

        if(especie != null){
            model.addAttribute("selectedEsp", especie);
        }else{
            model.addAttribute("selectedEsp", "-");
        }

        List<Especie> especies = RestController.RESTgetRequestListHeaders("/especies/all", new HttpHeaders(), Especie.class);

        model.addAttribute("avistamiento", avistamiento);
        model.addAttribute("especies", especies);
        model.addAttribute("error", error);
        model.addAttribute("navPage", "avistamiento");

        return "avistamiento";
    }

    @PostMapping(path="/foto")
    public ModelAndView getSpecieWithImg(Model model,
                                         @RequestParam(value = "img", required = true) MultipartFile file) throws IOException {
        String returnStr = "redirect:/avistamientos/add";

        BufferedImage bImage = ImageIO.read(file.getInputStream());
        Image img = bImage.getScaledInstance(255, 255, Image.SCALE_DEFAULT);
        BufferedImage bOutputImg = new BufferedImage(255, 255, BufferedImage.TYPE_INT_RGB);
        bOutputImg.getGraphics().drawImage(img, 0, 0, null);
        int w = bOutputImg.getWidth();
        int h = bOutputImg.getHeight();

        int[] dataBuffInt = bOutputImg.getRGB(0, 0, w, h, null, 0, w);

        int [][][] colors = new int[255][255][3];

        int index = 0;
        int i = 0;
        int j = 0;
        int k = 0;

        for(Integer in : dataBuffInt){
            Color c = new Color(in);
            colors[k][j][0] = c.getRed();
            colors[k][j][1] = c.getGreen();
            colors[k][j][2] = c.getBlue();

            j++;

            if(j == 255){
                j = 0;
                k++;
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(colors, int[][][].class);
        String mensaje = "{'data':" + json+"}";

        //Aqui le paso al rest de IA el array de bytes y me devuelve el nombre de la especie
        String response = "Gorrion";

        if(response.equals("")){
            returnStr += "?error=No se ha podido detectar la especie";
        }else{
            returnStr += "?especie="+response;
        }

        return new ModelAndView(returnStr, new ModelMap(model));
    }

    @PostMapping(path="/add")
    public ModelAndView addAvistamiento(Model model,
                                        @ModelAttribute Avistamiento avistamiento,
                                        WebRequest request){
        String returnStr = "";
        String error = "";

        if(avistamiento.getDescripcion().equals("")){
            error = "Debes introducir una descripción";
        }

        if(avistamiento.getLocalizacion().equals("")){
            error += " Debes introducir una localización";
        }

        String especieId = request.getParameter("especieId");

        if(especieId.equals("-")){
            error += " Debes seleccionar una especie";
        }

        if(error.length() == 0){
            Especie especie = RestController.RESTgetRequestHeaders("/especies/especie/"+Long.valueOf(especieId), new HttpHeaders(), Especie.class);
            avistamiento.setFecha(new Date());
            avistamiento.setEspecie(especie);
            Avistamiento avistamientoCreado = RestController.RESTpostRequest("/avistamientos/add", avistamiento, Avistamiento.class);

            publisher.publishMessage(avistamientoCreado);

            returnStr = "redirect:/especies/especie/"+especie.getEspecieId();
        }else{
            returnStr = "redirect:/avistamientos/add?error="+error;
        }

        return new ModelAndView(returnStr, new ModelMap(model));
    }

}
