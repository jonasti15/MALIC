package com.malic.musker.service;


import com.malic.musker.dao.tipousuario.TipoUsuarioFacade;
import com.malic.musker.dto.TipoUsuario;

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

@Path("tipousuario")
public class TipoUsuarioResource {
    @Context
    private UriInfo context;

    public TipoUsuarioResource() {
    }

    @GET
    @Path("todos")
    @Produces({"application/json", "application/xml"})
    public Response getTiposUsuarios() {
        TipoUsuarioFacade aif = new TipoUsuarioFacade();
        List<TipoUsuario> lista = aif.loadAllTipoUsuario();
        // Al responder utilizando un objeto Response necesitamos utilizar GenericEntity para que el parseo XML no de error
        GenericEntity<List<TipoUsuario>> entity = new GenericEntity<List<TipoUsuario>>(lista) {
        };
        if (lista.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().entity(entity).build();
        }
    }

    @GET
    @Path("tipo")
    @Produces({"application/json", "application/xml"})
    public Response getTipoUsuario(@QueryParam("id") int id) {
        Response res;
        TipoUsuario tipoUsuario = new TipoUsuario();
        TipoUsuarioFacade aif = new TipoUsuarioFacade();
        tipoUsuario = aif.loadTipoUsuario(id);
        if (Objects.isNull(tipoUsuario)) {
            res = Response.status(Response.Status.NOT_FOUND).build();
            return res;
        } else {
            res = Response.ok().entity(tipoUsuario).build();
            return res;
        }
    }

}
