package com.malic.musker.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malic.musker.exception.CustomException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class RestController {

    public static String getAccessToken(){
        try{
            return "Bearer " + Objects.requireNonNull(RestController.getRequest()).getSession().getAttribute("access_token").toString();
        }catch(NullPointerException e){
            return null;
        }
    }

    public static String getRefreshToken(){
        try{
            return "Bearer " + Objects.requireNonNull(RestController.getRequest()).getSession().getAttribute("refresh_token").toString();
        }catch(NullPointerException e){
            return null;
        }
    }

    public static void setAccessToken(String token){
        try{
            Objects.requireNonNull(RestController.getRequest()).getSession().setAttribute("access_token", token);
        }catch(NullPointerException ignored){

        }
    }

    public static HttpServletRequest getRequest() {
        try{
            return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                    .getRequest();
        }catch(NullPointerException e){
            return null;
        }
    }

    public static <T> T RESTgetRequestHeaders(String requestUrl, HttpHeaders headers, Class<T> returnClass) {
        RestTemplate restTemplate = new RestTemplate();

        String ruta = getPath();
        String url = ruta + requestUrl;


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, returnClass);
        } catch (Exception e) {
            if (e.getMessage().contains("Token has expired")) {
                if(refreshAccessToken().equals("")){
                    HttpHeaders newHeaders = new HttpHeaders();
                    newHeaders.set(HttpHeaders.AUTHORIZATION, RestController.getAccessToken());
                    return RestController.RESTgetRequestHeaders(url.substring(ruta.length()), newHeaders, returnClass);
                }else{
                    throw new CustomException();
                }
            }
        }

        if (responseEntity != null) {
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

    public static <T> List<T> RESTgetRequestListHeaders(String requestUrl, HttpHeaders headers, Class<T> returnClass) {
        RestTemplate restTemplate = new RestTemplate();

        String ruta = getPath();
        String url = ruta + requestUrl;

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        Object[] objects = new Object[0];
        try {
            ResponseEntity<Object[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object[].class);
            objects = responseEntity.getBody();
        } catch (Exception e) {
            if (e.getMessage().contains("Token has expired")) {
                if(refreshAccessToken().equals("")){
                    HttpHeaders newHeaders = new HttpHeaders();
                    newHeaders.set(HttpHeaders.AUTHORIZATION, RestController.getAccessToken());
                    return RestController.RESTgetRequestListHeaders(url.substring(ruta.length()), newHeaders, returnClass);
                }else{
                    throw new CustomException();
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, returnClass))
                .collect(Collectors.toList());
    }

    public static <T, G> G RESTpostRequest(String requestUrl, HttpHeaders headers, T objToSend, Class<G> returnClass) {
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> requestEntity = new HttpEntity<>(objToSend, headers);

        String url = getPath() + requestUrl;
        ResponseEntity<G> responseEntity = null;
        try{
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, returnClass);
        }catch (Exception e){
            if (e.getMessage().contains("Token has expired")) {
                if(refreshAccessToken().equals("")){
                    HttpHeaders newHeaders = new HttpHeaders();
                    newHeaders.set(HttpHeaders.AUTHORIZATION, RestController.getAccessToken());
                    return RESTpostRequest(requestUrl, newHeaders, objToSend, returnClass);
                }
            }else{
                throw new CustomException();
            }
        }
        if (responseEntity != null) {
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

    public static <T, G> G RESTgetRequestIA(String requestUrl, T objToSend, Class<G> returnClass) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> requestEntity = new HttpEntity<>(objToSend, headers);

        String url = "http://localhost:8081" + requestUrl;
        ResponseEntity<G> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, returnClass);
        return responseEntity.getBody();
    }

    public static <T, G> G RESTdeleteRequest(String requestUrl, HttpHeaders headers, T objToSend, Class<G> returnClass) {
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> requestEntity = new HttpEntity<>(objToSend, headers);

        String url = getPath() + requestUrl;
        ResponseEntity<G> responseEntity = null;
        try{
            responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, returnClass);
        }catch(Exception e){
            if (e.getMessage().contains("Token has expired")) {
                if(refreshAccessToken().equals("")) {
                    HttpHeaders newHeaders = new HttpHeaders();
                    newHeaders.set(HttpHeaders.AUTHORIZATION, RestController.getAccessToken());
                    return RESTdeleteRequest(requestUrl, newHeaders, objToSend, returnClass);
                }else{
                    throw new CustomException();
                }
            }
        }
        if (responseEntity != null) {
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

    public static <T, G> G RESTpostRequestForm(String requestUrl, MultiValueMap<T, T> mapToSend, Class<G> returnClass) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        String ruta = getPath();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<T, T> map = mapToSend;

        HttpEntity<MultiValueMap<T, T>> request = new HttpEntity<MultiValueMap<T, T>>(map, headers);

        ResponseEntity<G> response = restTemplate.postForEntity(ruta + requestUrl, request, returnClass);

        return response.getBody();
    }

    private static String refreshAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String ruta = getPath();
        String refreshUrl = ruta + "/user/refresh";
        HttpHeaders refreshHeader = new HttpHeaders();
        refreshHeader.set(HttpHeaders.AUTHORIZATION, RestController.getRefreshToken());
        HttpEntity<Void> requestRefresh = new HttpEntity<>(refreshHeader);
        try {
            ResponseEntity<String> responseTokens = restTemplate.exchange(refreshUrl, HttpMethod.GET, requestRefresh, String.class);
            String response = responseTokens.getBody();
            JSONObject obj = null;
            String access_token = "";
            try {
                obj = new JSONObject(response);
                access_token = obj.getString("access_token");
            } catch (JSONException ignored) {
            }
            RestController.setAccessToken(access_token);
        } catch (Exception e) {
            if(e.getMessage().contains("Refresh token expired")){
                return "expired";
            }
        }

        return "";
    }

    public static String getPath()  {
        Properties properties = new Properties();
        try
        {
            InputStream in = RestController.class.getClassLoader().getResourceAsStream("musker.properties");
            properties.load(in);
        } catch (IOException e) {
            System.out.println("No se puede leer musker.properties");
        }
        return properties.getProperty("URL");
    }

}
