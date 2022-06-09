package com.malic.musker.api;

import com.malic.musker.entities.User;
import com.malic.musker.entities.UserType;
import com.malic.musker.entitiesDTO.UserDTO;
import com.malic.musker.security.SecurityConfiguration;
import jdk.jfr.Frequency;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;
import java.util.Objects;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @PostMapping(path="/add")
        public ModelAndView addNewUser (Model model,
                                        @ModelAttribute UserDTO userDto,
                                        WebRequest request) {
        String returnStr = "register";
        String error = "";

        User user = new User();
        user.setNombre(userDto.getNombre());
        user.setApellido(userDto.getApellido());
        user.setFecha_nacimiento(userDto.getFecha_nacimiento());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder(SecurityConfiguration.ENCRYPT_STRENGTH);
        user.setPassword(encrypt.encode(request.getParameter("password")));
        user.setTipoUsuario(new UserType(3, "USUARIO"));
        setRandomProfile(user);

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
                user = RestController.RESTpostRequest(uri, new HttpHeaders(), user, User.class);
            }catch (HttpClientErrorException e){
                try{
                    if(Objects.requireNonNull(e.getMessage()).contains("username")){
                        error = error + "Username already in use ";
                    }
                    if(Objects.requireNonNull(e.getMessage()).contains("email")){
                        error = error + "Email already in use ";
                    }

                    returnStr = "register";
                }catch(NullPointerException ignored){

                }
            }
        }

        if(error.length() != 0){
            model.addAttribute("error", error);
            model.addAttribute("user", user);
        }

        return new ModelAndView(returnStr, new ModelMap(model));
    }

    @GetMapping(path="/profile")
    public String editUser(Model model,
                           @RequestParam(value = "error", required = false) String error){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        User user = RestController.RESTgetRequestHeaders("/user/username/"+username, header, User.class);

        if(user != null){
            model.addAttribute("userEdit", user);
            model.addAttribute("error", error);
            return "userProfile";
        }else{
            return "index";
        }
    }

    @PostMapping(path="/edit")
    public ModelAndView userEdit(Model model,
                                 @ModelAttribute UserDTO userDto,
                                 WebRequest request){
        String error = "";
        User bdUser;
        String returnStr = "redirect:/";

        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setNombre(userDto.getNombre());
        user.setApellido(userDto.getApellido());
        user.setFecha_nacimiento(userDto.getFecha_nacimiento());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());

        bdUser = RestController.RESTgetRequestHeaders("/user/user/" + user.getUserId(), header, User.class);

        if(!passwordsMatch(request)){
            error = "Password mismatch";
            model.addAttribute("userEdit", user);
            returnStr = "redirect:/user/profile?error="+error;
        }else if(Objects.equals(getPassword(request, "password"), "") && Objects.equals(getPassword(request, "passwordRep"), "")){
            user.setPassword(bdUser.getPassword());
        }else{
            BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder(SecurityConfiguration.ENCRYPT_STRENGTH);
            user.setPassword(encrypt.encode(request.getParameter("password")));
            returnStr = "redirect:/logout";
        }

        if(!user.getUsername().equals(bdUser.getUsername()) && error.length() == 0){
            returnStr = "redirect:/logout";
        }

        user.setFecha_nacimiento(bdUser.getFecha_nacimiento());
        user.setTipoUsuario(bdUser.getTipoUsuario());
        user.setProfileImg(bdUser.getProfileImg());

        if(error.length() == 0){
            String uri = "/user/edit";
            try{
                RestController.RESTputRequest(uri, new HttpHeaders(), user, User.class);
            }catch (HttpClientErrorException e){
                try{
                    if(Objects.requireNonNull(e.getMessage()).contains("username")){
                        error = error + "Username already in use ";
                    }
                    if(Objects.requireNonNull(e.getMessage()).contains("email")){
                        error = error + "Email already in use ";
                    }

                    model.addAttribute("userEdit", user);
                    returnStr = "redirect:/user/profile?error="+error;
                }catch(NullPointerException ignored){

                }
            }
        }

        return new ModelAndView(returnStr, new ModelMap(model));
    }

    @GetMapping(path="/add")
    public String addNewUser (Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    private boolean passwordsMatch(WebRequest request) {
        boolean match = true;

        String psw = request.getParameter("password");
        if (psw != null && !psw.equals(request.getParameter("passwordRep"))) {
            match = false;
        }
        return match;
    }

    private void setRandomProfile(User user) {
        SecureRandom rand = new SecureRandom();
        int image = rand.nextInt(13);
        user.setProfileImg("/images/userProfiles/"+image+".png");
    }

    private String getPassword(WebRequest request, String key){
        try{
            return request.getParameter(key);
        }catch(NullPointerException e){
            return null;
        }
    }

}