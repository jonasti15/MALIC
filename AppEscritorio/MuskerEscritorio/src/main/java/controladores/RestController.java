package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import elementos.Visita;
import interfaz.MUsker;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestController {
    private static final String REST_SERVICE_URL = "http://localhost:8080";
    private static Client client;

    public static <T> T RESTgetRequest(String path, HashMap<String, String> headers, Class<T> returnClass){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);

        headers.put("Authorization", "Bearer " + MUsker.access_token);

        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path(path);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            webResource.header(entry.getKey(), entry.getValue());
        }
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        return clientResponse.getEntity(returnClass);
    }

    public static <T> List<T> RESTgetListRequest(String path, HashMap<String, String> headers, Class<T> returnClass){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);

        headers.put("Authorization", "Bearer " + MUsker.access_token);

        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path(path);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            webResource.header(entry.getKey(), entry.getValue());
        }
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        List<T> lista=clientResponse.getEntity(new GenericType<List<T>>(){});
        List<T> adevolver=new ArrayList<>();
        for(int i=0;i<lista.size();i++){

            ObjectMapper mapper = new ObjectMapper();
            T pojo = mapper.convertValue(lista.get(i), returnClass);
            adevolver.add(pojo);
        }
        return adevolver;
    }

    public static <T> T RESTpostRequest(String path, HashMap<String, String> headers, T objToSend, Class<T> returnClass){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);

        headers.put("Authorization", "Bearer " + MUsker.access_token);

        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path(path);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            webResource.header(entry.getKey(), entry.getValue());
        }
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, objToSend);

        return clientResponse.getEntity(returnClass);
    }
    public static <T> void RESTdeleteRequest(String path, HashMap<String, String> headers){
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);

        headers.put("Authorization", "Bearer " + MUsker.access_token);

        WebResource webResource = client.resource(REST_SERVICE_URL)
                .path(path);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            webResource.header(entry.getKey(), entry.getValue());
        }
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("El objeto ha sido eliminado correctamente.");
        } else {
            System.out.println("La llamada no ha sido correcta.");
        }

    }
}
