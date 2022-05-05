package com.malic.musker.api;

import com.google.gson.Gson;
import com.malic.musker.entities.User;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
public class HomeController {

    @GetMapping(path = {"/", "/index"})
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "redirect:/login";
    }

    @GetMapping("/pruebarest")
    public String probarApi(@RequestParam(name = "username") String username) {
        String uri = "http://localhost:8080/REST/api/usuarios/usuarioUsername?username="+username;
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        Gson gson = new Gson();

        User user = new User();
        user = gson.fromJson(result, User.class);

        return "userForm";
    }

    @GetMapping("/normalUser")
    @ResponseBody
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/worker")
    @ResponseBody
    public String worker() {
        return ("<h1>Welcome worker</h1>");
    }

    @GetMapping("/mainPage")
    public String mainPage() {
        return "mainPage";
    }
}
