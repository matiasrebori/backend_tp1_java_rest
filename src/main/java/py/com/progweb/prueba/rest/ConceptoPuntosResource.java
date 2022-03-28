package py.com.progweb.prueba.rest;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.ConceptoPuntosDAO;
import py.com.progweb.prueba.model.ConceptoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/conceptoPuntos")
@Consumes("application/json")
@Produces("application/json")
public class ConceptoPuntosResource {

    @Inject
    private ConceptoPuntosDAO conceptoPuntosDAO;

    @GET
    public Response listar() {
        return Response.ok(conceptoPuntosDAO.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        return Response.ok(conceptoPuntosDAO.obtener(id)).build();
    }

    @POST
    public Response crear(ConceptoPuntos c) {
        conceptoPuntosDAO.crear(c);
        return Response.ok(c).build();
    }

    @PUT
    public Response actualizar(ConceptoPuntos c) {
        conceptoPuntosDAO.actualizar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        conceptoPuntosDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "ConceptoPuntos eliminado");
        return Response.ok(response.toString()).build();
    }
}
