package RabbitMQ;

import com.google.gson.Gson;
import controladores.ControladorAlertas;
import elementos.*;
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
            avistamiento.setFecha(new Date(Long.parseLong(obj.getString("fecha"))));
            avistamiento.setDescripcion(obj.getString("descripcion"));
            avistamiento.setLocalizacion(obj.getString("localizacion"));
            avistamiento.setEspecie(gson.fromJson(obj.getString("especie"), Especie.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return avistamiento;
    }

    public Alerta generateAlerta(String json, ControladorAlertas controladorAlertas) {
        JSONObject obj = null;
        Alerta alerta = new Alerta();
        try {
            obj = new JSONObject(json);
            alerta.setAnimalId(obj.getLong("animalId"));
            Animal animal=controladorAlertas.getAnimal(alerta.getAnimalId());

            TipoEstado estadoactual= controladorAlertas.getEstado(Long.valueOf(obj.getString("estadoActual")));

            alerta.setMensageId(obj.getString("_msgid"));
            alerta.setEstado(animal.getEstado());
            alerta.setEstadoNuevo(estadoactual);
            alerta.setRecinto_id(animal.getRecinto_id());
            alerta.setEspecie(animal.getEspecie());
            animal.setEstado(estadoactual);
            controladorAlertas.editarAnimal(animal.getAnimalId(), animal.getEspecie(), animal.getEstado(), animal.getRecinto_id(), animal.getEstadoIa());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return alerta;
    }
}