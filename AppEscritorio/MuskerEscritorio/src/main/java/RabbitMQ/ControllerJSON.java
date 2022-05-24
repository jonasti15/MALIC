package RabbitMQ;

import com.google.gson.Gson;

public class ControllerJSON {
    Gson gson;

    public ControllerJSON() {
        gson = new Gson();
    }

    public Constantes generateConstantes(String json) {
        return gson.fromJson(json, Constantes.class);
    }

    public String generateConstantesJSON(Constantes constantes) {
        return gson.toJson(constantes);
    }
}