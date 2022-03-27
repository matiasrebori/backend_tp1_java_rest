package py.com.progweb.prueba.rest;


import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.TipoDocumentoDAO;
import py.com.progweb.prueba.model.TipoDocumento;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/tipoDocumento")
@Consumes("application/json")
@Produces("application/json")
public class TipoDocumentoResource {

    @Inject
    private TipoDocumentoDAO tipoDocumentoDAO;

    @GET
    public Response listar() {
        return Response.ok(tipoDocumentoDAO.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        return Response.ok(tipoDocumentoDAO.obtener(id)).build();
    }

    @POST
    public Response crear(TipoDocumento c) {
        tipoDocumentoDAO.crear(c);
        return Response.ok(c).build();
    }

    @PUT
    public Response actualizar(TipoDocumento c) {
        tipoDocumentoDAO.actualizar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        tipoDocumentoDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "TipoDocumento eliminado");
        return Response.ok(response.toString()).build();
    }
}
