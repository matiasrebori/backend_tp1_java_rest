package py.com.progweb.prueba.rest;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.*;
import py.com.progweb.prueba.model.Bolsapuntos;
import py.com.progweb.prueba.model.CabeceraUsoPuntos;
import py.com.progweb.prueba.model.DetalleUsoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Módulo 7. Encargado de algunos servicios (POST, GET).
 * Reportes.
 */
@Path("/servicios/")
@Consumes("application/json")
@Produces("application/json")
public class ServiciosResource {

    @Inject
    private CabeceraUsoPuntosDAO cabeceraUsoPuntosDAO;

    @Inject
    private ConceptoPuntosDAO conceptousoPuntosDAO;

    @Inject
    private PuntosDAO puntosDAO;

    @Inject
    private VencimientoPuntosDAO vencimientoPuntosDAO;

    @Inject
    private BolsapuntosDAO bolsapuntosDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private DetalleUsoPuntosDAO detalleUsoPuntosDAO;


    /**
     * Consume un JSON para cargar los puntos a un cliente a través del monto de su operación
     * JSON: [{ "id_cliente": id_cliente, "monto_operacion": monto }]
     * */
    @POST
    @Path("/cargar_puntos/")
    public Response cargarPuntos(JSONObject json){

        int monto = Integer.parseInt(json.get("monto_operacion").toString());

        // ver cuántos puntos genera este monto (futuro método conceptoUsoPuntosDAO.puntosNecesariosConcepto())
        int puntos = puntosDAO.puntosTotalesGenerados(monto);

        // asignarle estos puntos al cliente
        Bolsapuntos bolsa = new Bolsapuntos();
        bolsa.setFecha_asignacion(new Date(System.currentTimeMillis()));
        bolsa.setId_cliente(Integer.parseInt(json.get("id_cliente").toString()));
        bolsa.setMonto_operacion(monto);
        bolsa.setPuntaje_asignado(puntos);
        bolsa.setPuntaje_utilizado(0);
        bolsa.setSaldo_puntos(puntos);
        bolsa.setFecha_caducidad(vencimientoPuntosDAO.getFechaCaducidad());

        bolsapuntosDAO.crear(bolsa);

        JSONObject response = new JSONObject();
        response.put("message", "Puntos asignados correctamente. Se asignaron: " + puntos + " puntos.");
        return Response.ok(response.toString()).build();
    }

    /**
     * Consume un JSON para utilizar los puntos de un cliente
     * JSON: [{ "id_cliente": id_cliente, "id_concepto": id_concepto }]
     * */
    @POST
    @Path("/utilizar_puntos/")
    public Response utilizarPuntos(JSONObject json){

        // obtener cuántos puntos requiere el concepto
        int puntos_necesarios = conceptousoPuntosDAO.puntosNecesariosConcepto(
                Integer.parseInt(json.get("id_concepto").toString()));

        // intentar utilizar los puntos
        Boolean exito = bolsapuntosDAO.descontarPuntosCliente(
                json, puntos_necesarios, clienteDAO.obtener(Integer.parseInt(json.get("id_cliente").toString())),
                conceptousoPuntosDAO.obtener(Integer.parseInt(json.get("id_concepto").toString())),
                cabeceraUsoPuntosDAO, detalleUsoPuntosDAO, bolsapuntosDAO);

        if (exito){
            JSONObject response = new JSONObject();
            response.put("message", "Operación realizada con éxito (" +
                    conceptousoPuntosDAO.obtener(Integer.parseInt(json.get("id_concepto").toString())).getDescripcion()
            +")");
            return Response.ok(response.toString()).build();
        } else{
            return Response.notModified("El cliente posee puntos insuficientes para la operación").build();
        }

    }

    /***
     * Devuelve a cuántos puntos equivale el monto recibido
     */
    @GET
    @Path("/puntos_equivalentes/{monto}")
    public Response puntosEquivalentes(@PathParam("monto") Integer monto){

        Integer puntos = puntosDAO.puntosTotalesGenerados(monto);

        JSONObject response = new JSONObject();
        response.put("message",
        "Los puntos equivalentes al monto " + monto + " son de " + puntos + " puntos.");
        return Response.ok(response.toString()).build();
    }
}
