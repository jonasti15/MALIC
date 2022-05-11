package com.malic.musker.api;

import com.malic.musker.entities.User;
import com.malic.musker.entities.UserType;
import com.malic.musker.security.SecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @PostMapping(path="/add")
        public ModelAndView addNewUser (Model model,
                                        @ModelAttribute User user,
                                        WebRequest request) {
        String returnStr = "register";
        String error = "";

        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder(SecurityConfiguration.ENCRYPT_STRENGTH);
        user.setPassword(encrypt.encode(request.getParameter("password")));
        user.setTipo_usuario(new UserType(3, "USUARIO"));

        if(!passwordsMatch(request)){
            error = "Password mismatch ";
        }else if(user.getNombre().length() == 0){
            error = error + "Name is required ";
        }else if(user.getApellido().length() == 0){
            error = error + "Surname is required ";
        }else if(user.getEmail().length() == 0){
            error = error + "Email ir required ";
        }

        if(error.length() == 0){
            returnStr = "redirect:/login";
            String uri = "/user/add";
            try{
                user = RestController.RESTpostRequest(uri, user, User.class);
            }catch (HttpClientErrorException e){
                if(e.getMessage().contains("username")){
                    error = error + "Username already in use ";
                }
                if(e.getMessage().contains("email")){
                    error = error + "Email already in use ";
                }

                returnStr = "register";
            }
        }

        if(error.length() != 0){
            model.addAttribute("error", error);
            model.addAttribute("user", user);
        }

        return new ModelAndView(returnStr, new ModelMap(model));
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

    private boolean passwordsMatch(WebRequest request) {
        boolean match = true;

        String psw = request.getParameter("password");
        if (psw != null && !psw.equals(request.getParameter("passwordRep"))) {
            match = false;
        }
        return match;
    }

}