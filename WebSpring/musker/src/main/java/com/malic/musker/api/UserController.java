package com.malic.musker.api;

import com.malic.musker.entities.User;
import com.malic.musker.entities.UserType;
import com.malic.musker.security.SecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @PostMapping(path="/add")
        public @ResponseBody String addNewUser (Model model,
                                                @ModelAttribute User user,
                                                WebRequest request) {

        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder(SecurityConfiguration.ENCRYPT_STRENGTH);
        user.setPassword(encrypt.encode(request.getParameter("password")));
        user.setTipo_usuario(new UserType(3, "USUARIO"));

        String uri = "http://localhost:8080/user/add";
        RestController.RESTpostRequest(uri, user, User.class);

        return "redirect:/home";
    }

    @GetMapping(path="/add")
    public String addNewUser (Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(Model model) {
        // This returns a JSON or XML with the users
        return null;
    }

}