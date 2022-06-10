import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;
import elementos.Animal;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class HiloConsumidor implements Callable<String> {
    private static final String REST_SERVICE_URL = "https://musker.duckdns.org/api";
    private static Client client;

    List<Long> listaTiempos;

    public HiloConsumidor(){
        this.listaTiempos=new ArrayList<>();
    }

    @Override
    public String call() {
            login();
            String animal=RESTgetRequest("/animals/all", new HashMap<>(), String.class);
            //System.out.println(animal);
            return animal;


    }
    public static <T> T RESTgetRequest(String path, HashMap<String, Object> headers, Class<T> returnClass){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);

        headers.put("Authorization", "Bearer " + Consumidores.access_token);

        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path(path);
        WebResource.Builder tmp = null;
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            tmp = webResource.header(entry.getKey(), entry.getValue());
        }

        ClientResponse clientResponse = tmp.accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        MultivaluedMap<String, String> response = clientResponse.getHeaders();
        if(response.containsKey("error")){
            String errorValue = String.valueOf(response.get("error"));
            if(errorValue.contains("Token has expired")){
                if(refreshToken().equals("")){
                    return RESTgetRequest(path, headers, returnClass);
                }else if(refreshToken().equals("expired")){
                    try {
                        Consumidores.restartProgram();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return clientResponse.getEntity(returnClass);
    }

    private static String refreshToken(){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);

        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path("user/refresh");
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + Consumidores.refresh_token).get(ClientResponse.class);
        MultivaluedMap<String, String> response = clientResponse.getHeaders();
        if(response.containsKey("error")){
            return "expired";
        }else{
            String jsonResponse = clientResponse.getEntity(String.class);
            JSONObject obj = null;
            String access_token = "";
            try {
                obj = new JSONObject(jsonResponse);
                access_token = obj.getString("access_token");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            Consumidores.access_token = access_token;
        }
        return "";
    }
    private void login() {
        Form form = new Form();
        form.add("username", "worker");
        form.add("password","worker");

        String jsonToken = RESTpostRequestLogin("/login", form);
        if (!jsonToken.equals("")) {
            JSONObject obj = null;
            String access_token = "", refresh_token = "";
            try {
                obj = new JSONObject(jsonToken);
                Consumidores.access_token = obj.getString("access_token");
                Consumidores.refresh_token = obj.getString("refresh_token");
                System.out.println("Sesion iniciada");
            } catch (JSONException e) {
                System.out.println("Error Parsing tokens");
            }
        } else {
            System.out.println("Usuario no válido");

        }
    }
    public static String RESTpostRequestLogin(String path, Form form){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);

        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path(path);
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
        String result = clientResponse.getEntity(new GenericType<>(String.class));
        if(result.contains("access_token")){
            return result;
        }else{
            return "";
        }
    }
}
