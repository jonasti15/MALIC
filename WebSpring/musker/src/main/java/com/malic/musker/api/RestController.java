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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RestController {
    public static String PATH = "http://localhost:8080";

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    public static <T> T RESTgetRequestHeaders(String requestUrl, HttpHeaders headers, Class<T> returnClass) {
        RestTemplate restTemplate = new RestTemplate();

        String url = PATH + requestUrl;

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, returnClass);
        } catch (Exception e) {
            if (e.getMessage().contains("Token has expired")) {
                if(refreshAccessToken().equals("")){
                    HttpHeaders newHeaders = new HttpHeaders();
                    newHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());
                    return RestController.RESTgetRequestHeaders(url.substring(PATH.length()), newHeaders, returnClass);
                }else{
                    throw new CustomException();
                }
            }
        }

        return responseEntity.getBody();
    }

    public static <T> List<T> RESTgetRequestListHeaders(String requestUrl, HttpHeaders headers, Class<T> returnClass) {
        RestTemplate restTemplate = new RestTemplate();

        String url = PATH + requestUrl;

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        Object[] objects = new Object[0];
        try {
            ResponseEntity<Object[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object[].class);
            objects = responseEntity.getBody();
        } catch (Exception e) {
            if (e.getMessage().contains("Token has expired")) {
                if(refreshAccessToken().equals("")){
                    HttpHeaders newHeaders = new HttpHeaders();
                    newHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + RestController.getRequest().getSession().getAttribute("access_token").toString());
                    return RestController.RESTgetRequestListHeaders(url.substring(PATH.length()), newHeaders, returnClass);
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

        String url = PATH + requestUrl;
        ResponseEntity<G> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, returnClass);
        return responseEntity.getBody();
    }

    public static <T, G> G RESTdeleteRequest(String requestUrl, T objToSend, Class<G> returnClass) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> requestEntity = new HttpEntity<>(objToSend, headers);

        String url = PATH + requestUrl;
        ResponseEntity<G> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, returnClass);
        return responseEntity.getBody();
    }

    public static <T, G> G RESTpostRequestForm(String requestUrl, MultiValueMap<T, T> mapToSend, Class<G> returnClass) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<T, T> map = mapToSend;

        HttpEntity<MultiValueMap<T, T>> request = new HttpEntity<MultiValueMap<T, T>>(map, headers);

        ResponseEntity<G> response = restTemplate.postForEntity(PATH + requestUrl, request, returnClass);

        return response.getBody();
    }

    private static String refreshAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String refreshUrl = PATH + "/user/refresh";
        String refresh_token = RestController.getRequest().getSession().getAttribute("refresh_token").toString();
        HttpHeaders refreshHeader = new HttpHeaders();
        refreshHeader.set(HttpHeaders.AUTHORIZATION, "Bearer " + refresh_token);
        HttpEntity<Void> requestRefresh = new HttpEntity<>(refreshHeader);
        try {
            ResponseEntity<String> responseTokens = restTemplate.exchange(refreshUrl, HttpMethod.GET, requestRefresh, String.class);
            String response = responseTokens.getBody();
            JSONObject obj = null;
            String access_token = "";
            try {
                obj = new JSONObject(response);
                access_token = obj.getString("access_token");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            RestController.getRequest().getSession().setAttribute("access_token", access_token);
        } catch (Exception e) {
            if(e.getMessage().contains("Refresh token expired")){
                return "expired";
            }
        }

        return "";
    }

}
