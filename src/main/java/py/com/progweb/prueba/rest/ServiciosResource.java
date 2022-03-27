package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.CabeceraUsoPuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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

    // TODO: POST carga de puntos, POST utilizar puntos, GET cuantos puntos equivale a un monto X

}
