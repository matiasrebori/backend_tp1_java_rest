package py.com.progweb.prueba.ejb;

import org.json.simple.JSONObject;
import py.com.progweb.prueba.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Stateless
public class BolsapuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<Bolsapuntos> listar() {
        return em.createQuery("select b from Bolsapuntos b", Bolsapuntos.class).getResultList();
    }

    public Bolsapuntos obtener(Integer id) {
        return em.find(Bolsapuntos.class, id);
    }

    public void crear(Bolsapuntos bolsapuntos) {
        this.em.persist(bolsapuntos);
    }

    public void actualizar(Bolsapuntos bolsapuntos) {
        this.em.merge(bolsapuntos);
    }

    public void eliminar(Integer id) {
        Bolsapuntos bolsapuntos = this.em.find(Bolsapuntos.class, id);
        this.em.remove(bolsapuntos);
    }

    public List<Bolsapuntos> getBolsasPorCliente(Integer id) {
        return em.createQuery("" +
                                "select b " +
                                "from Bolsapuntos b " +
                                "where b.id_cliente=:id",
                        Bolsapuntos.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Bolsapuntos> getBolsasPorRangoPuntos(Integer inicio, Integer fin) {
        return em.createQuery("" +
                                "select b " +
                                "from Bolsapuntos b " +
                                "where b.puntaje_asignado between :inicio and :fin",
                        Bolsapuntos.class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .getResultList();
    }

    public List<Cliente> getClientesConPuntosVencerEnX(Integer x, ClienteDAO clienteDAO) {

        LocalDate fecha_hoy = LocalDate.now();
        Date en_x_dias = java.sql.Date.valueOf(fecha_hoy.plusDays(x));
        Date hoy = java.sql.Date.valueOf(fecha_hoy);

        List<Integer> clientesIdLista = em.createQuery("" +
                                "select distinct b.id_cliente " +
                                "from Bolsapuntos b " +
                                "where b.fecha_caducidad between :hoy and :en_x_dias",
                        Integer.class)
                .setParameter("en_x_dias", en_x_dias)
                .setParameter("hoy", hoy)
                .getResultList();

        List<Cliente> clientesLista = new ArrayList<>();

        for (Integer cliente_id: clientesIdLista) {
            clientesLista.add(clienteDAO.obtener(cliente_id));
        }

        return clientesLista;
    }

    /**
     * Recibe la soliciutd JSON para utilizar los puntos de un cliente
     * JSON: [{ "id_cliente": id_cliente, "id_concepto": id_concepto }]
     * */
    public Boolean descontarPuntosCliente(JSONObject json, Integer puntos_necesarios, Cliente cliente,
                                          ConceptoPuntos concepto, CabeceraUsoPuntosDAO cabeceraUsoPuntosDAO,
                                          DetalleUsoPuntosDAO detalleUsoPuntosDAO, BolsapuntosDAO bolsapuntosDAO){

        // obtener las bolsas de puntos del cliente ordenadas por ingreso al sistema
        List<Bolsapuntos> listabolsas = em.createQuery("" +
                                "select b " +
                                "from Bolsapuntos b " +
                                "where b.id_cliente=:id_cliente and b.saldo_puntos <> 0 " +
                                "order by b.id",
                        Bolsapuntos.class)
                .setParameter("id_cliente", json.get("id_cliente"))
                .getResultList();

        // recorrer las bolsas hasta alcanzar el puntaje necesario para el concepto
        List<Bolsapuntos> bolsasUtilizadas = new ArrayList<Bolsapuntos>();
        JSONObject montosUtilizadosPorBolsa = new JSONObject(); // para guardar cuántos puntos se van utilizando por bolsa

        int monto = puntos_necesarios;
        for (Bolsapuntos bolsa : listabolsas) {
            this.em.detach(bolsa);
            bolsa.setSaldo_puntos(bolsa.getSaldo_puntos() - monto);
            bolsasUtilizadas.add(bolsa);

            // si el saldo fue suficiente
            if (bolsa.getSaldo_puntos() >= 0) {
                montosUtilizadosPorBolsa.put(bolsa.getId(), monto);
                bolsa.setPuntaje_utilizado(bolsa.getPuntaje_utilizado() + monto); // agregar lo que se utilizó
                monto = 0;
                break;

            } else { // si fue menor, entonces volver a intentar con la siguiente bolsa
                montosUtilizadosPorBolsa.put(bolsa.getId(), monto + bolsa.getSaldo_puntos());
                monto = bolsa.getSaldo_puntos() * -1; // hacer positivo lo que le falta para alcanzar
                bolsa.setSaldo_puntos(0); // descontar el saldo de esta bolsa
                bolsa.setPuntaje_utilizado(bolsa.getPuntaje_asignado()); // ya se usó totalmente el saldo de la bolsa
            }
        }

        // en este punto, si el monto quedó en 0, quiere decir que el saldo fue suficiente, entonces
        // registrar el cambio
        if (monto == 0) {
            // generar la cabecera
            CabeceraUsoPuntos cabecera = new CabeceraUsoPuntos();
            cabecera.setIdCliente(cliente);
            cabecera.setPuntajeUtilizado(puntos_necesarios);
            cabecera.setFecha(new Date(System.currentTimeMillis()));
            cabecera.setConceptoUsoPunto(concepto);
            cabeceraUsoPuntosDAO.crear(cabecera);

            // generar los detalles por cada bolsa utilizada
            for (Bolsapuntos bolsa : bolsasUtilizadas) {
                DetalleUsoPuntos detalle = new DetalleUsoPuntos();
                detalle.setIdCabecera(cabecera);
                detalle.setPuntajeUtilizado(Integer.parseInt(montosUtilizadosPorBolsa.get(bolsa.getId()).toString()));
                detalle.setIdBolsaDePuntos(bolsa);
                detalleUsoPuntosDAO.crear(detalle);
                bolsapuntosDAO.actualizar(bolsa);
            }

            return true;

            // caso saldo insuficiente
        } else {
            return false;
        }
    }
}
