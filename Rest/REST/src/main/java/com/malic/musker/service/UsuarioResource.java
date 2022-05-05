package com.malic.musker.service;

import com.malic.musker.dao.tipousuario.TipoUsuarioFacade;
import com.malic.musker.dao.usuario.UsuarioFacade;
import com.malic.musker.dto.TipoUsuario;
import com.malic.musker.dto.Usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Objects;

@Path("usuarios")
public class UsuarioResource {

    @Context
    private UriInfo context;

    public UsuarioResource() {
    }

    @GET
    @Path("todos")
    @Produces({"application/json", "application/xml"})
    public Response getTiposUsuarios() {
        UsuarioFacade aif = new UsuarioFacade();
        List<Usuario> lista = aif.loadAllUsuarios();
        // Al responder utilizando un objeto Response necesitamos utilizar GenericEntity para que el parseo XML no de error
        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(lista) {
        };
        if (lista.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().entity(entity).build();
        }
    }

    @GET
    @Path("usuarioId")
    @Produces({"application/json", "application/xml"})
    public Response getUsuario(@QueryParam("id") int id) {
        Response res;
        Usuario usuario = new Usuario();
        UsuarioFacade aif = new UsuarioFacade();
        usuario = aif.loadUsuario(id);
        if (Objects.isNull(usuario)) {
            res = Response.status(Response.Status.NOT_FOUND).build();
            return res;
        } else {
            res = Response.ok().entity(usuario).build();
            return res;
        }
    }

    @GET
    @Path("usuarioUsername")
    @Produces({"application/json", "application/xml"})
    public Response getUsuarioUsername(@QueryParam("username") String username) {
        Response res;
        Usuario usuario = new Usuario();
        UsuarioFacade aif = new UsuarioFacade();
        usuario = aif.loadUsuarioUsername(username);
        if (Objects.isNull(usuario)) {
            res = Response.status(Response.Status.NOT_FOUND).build();
            return res;
        } else {
            res = Response.ok().entity(usuario).build();
            return res;
        }
    }
}
