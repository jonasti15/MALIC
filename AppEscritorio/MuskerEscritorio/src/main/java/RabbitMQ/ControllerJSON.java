package RabbitMQ;

import com.google.gson.Gson;
import elementos.Avistamiento;
import elementos.Especie;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class ControllerJSON {
    Gson gson;

    public ControllerJSON() {
        gson = new Gson();
    }

    public Avistamiento generateAvistamiento(String json) {
        JSONObject obj = null;
        Avistamiento avistamiento = new Avistamiento();
        try {
            obj = new JSONObject(json);
            avistamiento.setAvistamiento_id(obj.getLong("avistamiento_id"));
            avistamiento.setFecha(new Date(Long.valueOf(obj.getString("fecha"))));
            avistamiento.setDescripcion(obj.getString("descripcion"));
            avistamiento.setLocalizacion(obj.getString("localizacion"));
            avistamiento.setEspecie(gson.fromJson(obj.getString("especie"), Especie.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return avistamiento;
    }

    public String generateConstantesJSON(Constantes constantes) {
        return gson.toJson(constantes);
    }
}