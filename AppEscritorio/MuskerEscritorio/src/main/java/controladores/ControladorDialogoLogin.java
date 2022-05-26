package controladores;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;
import dialogo.DialogoLogin;
import elementos.Permisos;
import elementos.User;
import interfaz.MUsker;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorDialogoLogin implements ActionListener {
    PreparedStatement ps = null;
    ResultSet rs = null;

    DialogoLogin login;

    private Client client;
    private static final String REST_SERVICE_URL = "http://localhost:8080";

    public ControladorDialogoLogin(DialogoLogin login) {
        this.login = login;
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  // <----- set the json configuration POJO MAPPING for JSON reponse paring
        client = Client.create(clientConfig);
    }

    /**
     * Método que sirve para iniciar sesión al pulsar el botón
     * Casos:
     * mostrar - Muestra la contraseá con caracteres o con '*'
     * confirmar - Se conecta a la base de datos con la cuenta introducida,
     * si no se conecta significa que no tiene cuenta, si se conecta, puede acceder a la aplicación
     *
     * @param evt - Nombre de la accion
     * @author Jon
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        String accion = evt.getActionCommand();
        Permisos permiso = null;

        switch (accion) {
            case "confirmar":

                Form form = new Form();
                form.add("username", login.getUsuario().getText());
                form.add("password", String.valueOf(login.getPassword().getPassword()));

                WebResource webResource = client.resource(REST_SERVICE_URL)
                        .path("/login");
                ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
                String jsonToken = clientResponse.getEntity(new GenericType<String>() {
                });
                if (!jsonToken.equals("")) {
                    JSONObject obj = null;
                    String access_token = "", refresh_token = "";
                    try {
                        obj = new JSONObject(jsonToken);
                        MUsker.access_token = obj.getString("access_token");
                        MUsker.refresh_token = obj.getString("refresh_token");
                    } catch (JSONException e) {
                        System.out.println("Error Parsing tokens");
                    }

                    User user = null;
                    webResource = client.resource(REST_SERVICE_URL)
                            .path("/user/username").path(login.getUsuario().getText());
                    clientResponse = webResource.accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + MUsker.access_token).get(ClientResponse.class);
                    if (clientResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                        user = clientResponse.getEntity(new GenericType<User>() {
                        });
                    }

                    if (user != null && user.getTipoUsuario().getTipoUsuarioId() != 3) {
                        switch (user.getTipoUsuario().getTipoUsuarioId()) {

                            case 1:
                                permiso = Permisos.ADMIN;
                                break;

                            case 2:
                                permiso = Permisos.WORKER;
                                break;

                        }

                        login.setUser(user);
                        login.dispose();

                    } else {
                        JOptionPane.showConfirmDialog(login, "Usuario no válido", "Usuario no válido", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                        login.getPassword().setText("");
                    }

                } else {
                    JOptionPane.showConfirmDialog(login, "El usuario o la contraseña no son correctos", "Usuario inválido", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                    login.getPassword().setText("");
                }
                break;

            case "mostrar":
                if (login.getMostrar().isSelected()) login.getPassword().setEchoChar((char) 0);
                else login.getPassword().setEchoChar('*');
                break;
        }
    }

}
