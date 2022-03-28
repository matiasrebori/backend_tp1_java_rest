package py.com.progweb.prueba.rest;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/puntos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoPuntosRest {
    @Inject
    private VencimientoPuntosDAO vencimientoPuntosDAO;

    @GET
    public Response listar() {
        return Response.ok(vencimientoPuntosDAO.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        return Response.ok(vencimientoPuntosDAO.obtener(id)).build();
    }

    @POST
    public Response crear(VencimientoPuntos c) {
        vencimientoPuntosDAO.crear(c);
        return Response.ok(c).build();
    }

    @PUT
    public Response actualizar(VencimientoPuntos c) {
        vencimientoPuntosDAO.actualizar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        vencimientoPuntosDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "Vencimiento de puntos eliminado");
        return Response.ok(response.toString()).build();
    }
}
