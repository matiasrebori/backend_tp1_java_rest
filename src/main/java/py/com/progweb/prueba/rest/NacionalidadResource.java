package py.com.progweb.prueba.rest;


import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.NacionalidadDAO;
import py.com.progweb.prueba.model.Nacionalidad;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/nacionalidad")
@Consumes("application/json")
@Produces("application/json")
public class NacionalidadResource {

    @Inject
    private NacionalidadDAO nacionalidadDAO;

    @GET
    public Response listar() {
        return Response.ok(nacionalidadDAO.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        return Response.ok(nacionalidadDAO.obtener(id)).build();
    }

    @POST
    public Response crear(Nacionalidad c) {
        nacionalidadDAO.crear(c);
        return Response.ok(c).build();
    }

    @PUT
    public Response actualizar(Nacionalidad c) {
        nacionalidadDAO.actualizar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        nacionalidadDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "Nacionalidad eliminada");
        return Response.ok(response.toString()).build();
    }
}
