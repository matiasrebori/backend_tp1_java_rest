package py.com.progweb.prueba.rest;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.ejb.*;
import py.com.progweb.prueba.model.Bolsapuntos;
import py.com.progweb.prueba.model.CabeceraUsoPuntos;
import py.com.progweb.prueba.model.DetalleUsoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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


    /**
     * Consume un JSON para cargar los puntos a un cliente a través del monto de su operación
     * JSON: [{ "id_cliente": id_cliente, "monto_operacion": monto }]
     * */
    @POST
    @Path("/cargar_puntos/")
    public Response cargarPuntos(JSONObject json){

        int monto = Integer.parseInt(json.get("monto").toString());

        // ver cuántos puntos genera este monto (futuro método conceptoUsoPuntosDAO.puntosNecesariosConcepto())
        int puntos = puntosDAO.puntosTotalesGenerados(monto);

        // asignarle estos puntos al cliente
        Bolsapuntos bolsa = new Bolsapuntos();
        bolsa.setFecha_asignacion(new Date(System.currentTimeMillis()));
        bolsa.setId_cliente(Integer.parseInt(json.get("id_cliente").toString()));
        bolsa.setMonto_operacion(monto);
        bolsa.setPuntaje_asignado(monto);
        bolsa.setPuntaje_utilizado(0);
        bolsa.setSaldo_puntos(monto);
        bolsa.setFecha_caducidad(vencimientoPuntosDAO.getFechaCaducidad());

        bolsapuntosDAO.crear(bolsa);

        JSONObject response = new JSONObject();
        response.put("message", "Puntos asignados correctamente. Se asignaron: " + puntos + " puntos.");
        return Response.ok(response.toString()).build();
    }

    // TODO: Cambiar todos los nombres a sus respectivos nombres cuando estén los demás módulos
//    /**
//     * Consume un JSON para utilizar los puntos de un cliente
//     * JSON: [{ "id_cliente": id_cliente, "id_concepto": id_concepto }]
//     * */
//    @POST
//    @Path("/utilizar_puntos/")
//    public Response utilizarPuntos(JSONObject json){
//
//        // obtener cuántos puntos requiere el concepto
//        int puntos_necesarios = conceptousoPuntosDAO.puntosNecesariosConcepto(json.get(id_concepto));
//
//        // intentar descontar los puntos del cliente
//
//        // obtener las bolsas de puntos del cliente ordenadas por fecha caducidad
//        List<BolsaPuntos> listabolsas = em.createQuery("" +
//                                "select b " +
//                                "from bolsa_puntos b " +
//                                "where b.id_cliente=:id_cliente " +
//                                "order by b.fecha_caducidad",
//                        CabeceraUsoPuntos.class)
//                .setParameter("id_cliente", json.get("id_cliente"))
//                .getResultList();
//
//        // recorrer las bolsas hasta alcanzar el puntaje necesario para el concepto
//        List<BolsaPuntos> bolsasUtilizadas = new List<BolsaPuntos>();
//        JSONObject montosUtilizadosPorBolsa = new JSONObject(); // para guardar cuántos puntos se van utilizando por bolsa
//
//        int monto = puntos_necesarios;
//        for (BolsaPuntos bolsa : listabolsas) {
//            bolsa.saldo -= monto;
//            bolsasUtilizadas.add(bolsa);
//
//            // si el saldo fue suficiente
//            if (bolsa.saldo >= 0) {
//                bolsa.puntosUtilizados += monto;
//                montosUtilizadosPorBolsa.put(bolsa.id, monto);
//                break;
//
//            } else { // si fue menor, entonces volver a intentar con la siguiente bolsa
//                montosUtilizadosPorBolsa.put(bolsa.id, monto + bolsa.saldo);
//                monto = bolsa.saldo * -1; // hacer positivo lo que le falta para alcanzar
//                bolsa.saldo = 0; // descontar el saldo de esta bolsa
//                bolsa.puntosUtilizados = bolsa.puntosAsignados; // ya se usó totalmente el saldo de la bolsa
//            }
//        }
//
//        // en este punto, si el monto quedó en 0, quiere decir que el saldo fue suficiente, entonces
//        // registrar el cambio
//        if (monto == 0) {
//            // generar la cabecera
//            CabeceraUsoPuntos cabecera = new CabeceraUsoPuntos();
//            cabecera.setIdCliente(id_cliente);
//            cabecera.setPuntajeUtilizado(puntos_necesarios);
//            cabecera.setFecha(new Date(System.currentTimeMillis()));
//            cabecera.setConceptoUsoPunto(json.get(id_concepto));
//
//            // generar los detalles por cada bolsa utilizada
//            List<DetalleUsoPuntos> detalles = new List<DetalleUsoPuntos>();
//            for (BolsaPuntos bolsa : bolsasUtilizadas) {
//                DetalleUsoPuntos detalle = new DetalleUsoPuntos();
//                detalle.setIdCabecera(cabecera);
//                detalle.setPuntajeUtilizado(montosUtilizadosPorBolsa.get(bolsa.id));
//                detalle.setIdBolsaPuntos(bolsa);
//                detalles.add(detalle);
//            }
//
//            JSONObject response = new JSONObject();
//            response.put("message", "Operación realizada con éxito");
//            return Response.ok(response.toString()).build();
//
//        // caso saldo insuficiente
//        } else {
//            return Response.notModified("El cliente posee puntos insuficientes para la operación").build();
//        }
//
//    }

//    /***
//     * Devuelve a cuántos puntos equivale el monto recibido
//     */
//    @GET
//    @Path("/puntos_equivalentes/{monto}")
//    public Response puntosEquivalentes(@PathParam("monto") Integer monto){
//
//        //TODO: llamar al DAO de la regla de puntos
//        puntos = ReglaPuntosDAO.puntajeEquivalente(monto);
//
//        JSONObject response = new JSONObject();
//        response.put("message",
//        "Los puntos equivalentes al monto " + monto + " son de " + puntaje_equivalente + "puntos.");
//        return Response.ok(response.toString()).build();
//    }
}
