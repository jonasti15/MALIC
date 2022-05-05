package com.malic.musker.security;

import com.google.gson.Gson;
import com.malic.musker.dao.user.UserRepository;
import com.malic.musker.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String uri = "http://localhost:8080/REST/api/usuarios/usuarioUsername?username="+username;
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        Gson gson = new Gson();

        User user = new User();
        user = gson.fromJson(result, User.class);


        /* Esto lo teniamos con el hibernate
        User user = userRepository.findByUsername(username);*/
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }
}
