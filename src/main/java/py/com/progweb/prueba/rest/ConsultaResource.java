package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.CabeceraUsoPuntosDAO;
import py.com.progweb.prueba.ejb.ClienteDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Módulo 7. Encargado de algunas consultas (GET).
 * Reportes.
 */
@Path("/consultas/")
@Consumes("application/json")
@Produces("application/json")
public class ConsultaResource {

    @Inject
    private CabeceraUsoPuntosDAO cabeceraUsoPuntosDAO;

    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;

    @Inject
    private ClienteDAO clienteDAO;


    // ==== Uso de puntos ==== //

    /**
     * Devuelve una lista con los usos de los puntos filtrados por concepto de uso
     * */
    @GET
    @Path("/uso_puntos_por_concepto/{id_concepto}")
    public Response usoPuntosPorConcepto(@PathParam("id_concepto") Integer id_concpeto){
        return Response.ok(cabeceraUsoPuntosDAO.getCabecerasPorConcepto(id_concpeto)).build();
    }

    /**
     * Devuelve una lista con los usos de los puntos filtrados por concepto de uso
     * */
    @GET
    @Path("/uso_puntos_por_fecha_uso/{fecha}")
    public Response usoPuntosPorFechaUso(@PathParam("fecha") Date fecha){
        return Response.ok(cabeceraUsoPuntosDAO.getCabecerasPorFechaUso(fecha)).build();
    }

    /**
     * Devuelve una lista con los usos de los puntos filtrados por cliente
     * */
    @GET
    @Path("/uso_puntos_por_clientes/{id}")
    public Response usoPuntosPorFechaClientes(@PathParam("id") Integer id){
        return Response.ok(cabeceraUsoPuntosDAO.getCabecerasPorCliente(id)).build();
    }


    // ==== Bolsa de puntos ==== //

    // TODO: Ponerle el nombre correcto de BolsaPuntosDAO cuando esté el módulo 5
    // TODO: También armar dentro de la clase el método para realizar esta funcionalidad bolsaPuntosPorCliente
    /**
     * Devuelve una lista con las bolsas de puntos por clientes
     * */
    @GET
    @Path("/bolsa_puntos_por_cliente/{id}")
    public Response bolsaPuntosPorCliente(@PathParam("id") Integer id){
        return Response.ok(bolsaPuntosDAO.getBolsasPorCliente(id)).build();
    }

    // TODO: Ponerle el nombre correcto de BolsaPuntosDAO cuando esté el módulo 5
    // TODO: También armar dentro de la clase el método para realizar esta funcionalidad bolsaPuntosPorRango
    /**
     * Consume un JSON y devuelve una lista con las bolsas de puntos por clientes
     * JSON: [{ "inicio": i, "fin": f }]
     * */
    @GET
    @Path("/bolsa_puntos_por_rango_puntos/")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response bolsaPuntosPorRango(RequestBody requestBody){
        return Response.ok(bolsaPuntosDAO.getBolsasPorRangoPuntos(requestBody.inicio, requestBody.fin)).build();
    }

    // Bolsa de Puntos - Clientes

    // TODO: Ponerle el nombre correcto de BolsaPuntosDAO cuando esté el módulo 5
    // TODO: También armar dentro de la clase el método para realizar esta funcionalidad bolsaPuntosPorCliente
    /**
     * Devuelve una lista con los clientes con puntos a vencer en x días
     * */
    @GET
    @Path("/clientes_con_puntos_a_vencer_en/{x}")
    public Response clientesConPuntosVencerEnX(@PathParam("x") Integer id){
        return Response.ok(bolsaPuntosDAO.getClientesConPuntosVencerEnX(x)).build();
    }


    // ==== Clientes ==== //

    /**
     * Devuelve los clientes por nombre (aproximación)
     * */
    @GET
    @Path("/clientes_nombre/{nombre}")
    public Response clientesPorNombre(@PathParam("nombre") String nombre){
        return Response.ok(clienteDAO.getClientesPorNombre(nombre)).build();
    }

    /**
     * Devuelve los clientes por apellido (aproximación)
     * */
    @GET
    @Path("/clientes_apellido/{apellido}")
    public Response clientesPorApellido(@PathParam("apellido") String nombre){
        return Response.ok(clienteDAO.getClientesPorApellido(apellido)).build();
    }

    /**
     * Devuelve los clientes por cumpleaños
     * */
    @GET
    @Path("/clientes_por_cumpleanhos/{cumpleanhos}")
    public Response clientesPorApellido(@PathParam("cumpleanhos") String cumpleanhos){
        return Response.ok(clienteDAO.getClientesPorCumpleanhos(cumpleanhos)).build();
    }
}
