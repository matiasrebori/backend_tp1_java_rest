package py.com.progweb.prueba.rest;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.BolsapuntosDAO;
import py.com.progweb.prueba.model.Bolsapuntos;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/bolsapuntos")
@Consumes("application/json")
@Produces("application/json")
public class BolsapuntosRest {
    @Inject
    private BolsapuntosDAO bolsapuntosDAO;

    @GET
    public Response listar() {
        return Response.ok(bolsapuntosDAO.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        return Response.ok(bolsapuntosDAO.obtener(id)).build();
    }

    @POST
    public Response crear(Bolsapuntos c) {
        bolsapuntosDAO.crear(c);
        return Response.ok(c).build();
    }

    @PUT
    public Response actualizar(Bolsapuntos c) {
        bolsapuntosDAO.actualizar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        bolsapuntosDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "Bolsa de puntos eliminado");
        return Response.ok(response.toString()).build();
    }
}
