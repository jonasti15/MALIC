package com.malic.musker.api;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RestController {
    public static String PATH = "http://localhost:8080";

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    public static <T> T RESTgetRequest(String requestUrl, Class<T> returnClass) {
        RestTemplate restTemplate = new RestTemplate();

        String url = PATH + requestUrl;
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, returnClass);
        return  responseEntity.getBody();
    }

    public static <T> T RESTgetRequestHeaders(String requestUrl, HttpHeaders headers, Class<T> returnClass){
        RestTemplate restTemplate = new RestTemplate();

        String url = PATH + requestUrl;

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, returnClass);

        return responseEntity.getBody();
    }

    public static <T, G> G RESTpostRequest(String requestUrl, T objToSend, Class<G> returnClass) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> requestEntity = new HttpEntity<>(objToSend, headers);

        String url = PATH + requestUrl;
        ResponseEntity<G> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, returnClass);
        return  responseEntity.getBody();
    }

    public static <T, G> G RESTpostRequestSelfForm(String requestUrl, MultiValueMap<T, T> mapToSend, Class<G> returnClass) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<T, T> map = mapToSend;

        HttpEntity<MultiValueMap<T, T>> request = new HttpEntity<MultiValueMap<T, T>>(map, headers);

        ResponseEntity<G> response = restTemplate.postForEntity("http://localhost:80/" + requestUrl, request, returnClass);

        return response.getBody();
    }

    public static <T, G> G RESTpostRequestForm(String requestUrl, MultiValueMap<T, T> mapToSend, Class<G> returnClass){
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<T, T> map = mapToSend;

        HttpEntity<MultiValueMap<T, T>> request = new HttpEntity<MultiValueMap<T, T>>(map, headers);

        ResponseEntity<G> response = restTemplate.postForEntity(PATH + requestUrl, request, returnClass);

        return response.getBody();
    }

}
