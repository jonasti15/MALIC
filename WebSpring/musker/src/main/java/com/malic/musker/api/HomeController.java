package com.malic.musker.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.malic.musker.entities.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


@Controller
public class HomeController {

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping(path = {"/", "/index"})
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(HttpServletRequest httpRequest, WebRequest request) throws JSONException {
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);

        String response = RestController.RESTpostRequestForm("/login", map, String.class);
        JSONObject obj = new JSONObject(response);
        String access_token = obj.getString("access_token");
        String refresh_token = obj.getString("refresh_token");
        map.add("access_token", access_token);
        map.add("refresh_token", refresh_token);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + access_token);

        User user = RestController.RESTgetRequestHeaders("/user/username/"+username, headers, User.class);

        return "mainPage";
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

    @GetMapping("/aboutUs")
    public String aboutUs(Model model){
        model.addAttribute("navPage", "aboutUs");
        return "aboutUs";
    }

}

