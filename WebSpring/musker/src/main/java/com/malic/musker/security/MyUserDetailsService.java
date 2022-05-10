package com.malic.musker.security;

import com.malic.musker.api.RestController;
import com.malic.musker.entities.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = RestController.getRequest().getParameter("txtPassword");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);

        String response = RestController.RESTpostRequestForm("/login", map, String.class);
        JSONObject obj = null;
        String access_token = "", refresh_token = "";
        try {
            obj = new JSONObject(response);
            access_token = obj.getString("access_token");
            refresh_token = obj.getString("refresh_token");
        } catch (JSONException e) {
            System.out.println("Error Parsing tokens");
        }

        map.add("access_token", access_token);
        map.add("refresh_token", refresh_token);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + access_token);

        User user = RestController.RESTgetRequestHeaders("/user/username/"+username, headers, User.class);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }
}
