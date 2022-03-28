package py.com.progweb.prueba.rest;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.PuntosDAO;
import py.com.progweb.prueba.model.Puntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/puntos")
@Consumes("application/json")
@Produces("application/json")
public class PuntosRest {
    @Inject
    private PuntosDAO puntosDAO;

    @GET
    public Response listar() {
        return Response.ok(puntosDAO.listar()).build();
    }

    @POST
    public Response crear(Puntos p) {
        puntosDAO.crear(p);
        return Response.ok(p).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        return Response.ok(puntosDAO.obtener(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        puntosDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "Punto eliminado");
        return Response.ok(response.toString()).build();
    }

}
