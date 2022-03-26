package py.com.progweb.prueba.rest;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/clientes")
@Consumes("application/json")
@Produces("application/json")
public class ClienteResource {

    @Inject
    private ClienteDAO clienteDAO;

    @GET
    public Response listar() {
        return Response.ok(clienteDAO.listar()).build();
    }

    @POST
    public Response crear(Cliente c) {
        clienteDAO.crear(c);
        return Response.ok(c).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Integer id) {
        return Response.ok(clienteDAO.obtener(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        clienteDAO.eliminar(id);
        JSONObject response = new JSONObject();
        response.put("message", "Persona eliminada");
        return Response.ok(response.toString()).build();
    }
}
