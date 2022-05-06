package com.malic.musker.security;

import com.google.gson.Gson;
import com.malic.musker.api.RestController;
import com.malic.musker.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String uri = "http://localhost:8080/user/username/"+username;
        User user = new User();
        user = RestController.RESTgetRequest(uri, User.class);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }
}
