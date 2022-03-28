package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Bolsapuntos;
import py.com.progweb.prueba.model.CabeceraUsoPuntos;
import py.com.progweb.prueba.model.Puntos;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
                                "from bolsa_puntos b " +
                                "where b.id_cliente=:id",
                        Bolsapuntos.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Bolsapuntos> getBolsasPorRangoPuntos(Integer inicio, Integer fin) {
        return em.createQuery("" +
                                "select b " +
                                "from bolsa_puntos b " +
                                "where b.puntaje_asignado between :inicio and :fin",
                        Bolsapuntos.class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .getResultList();
    }

    public Object getClientesConPuntosVencerEnX(Integer x) {

        return em.createQuery("" +
                                "select b " +
                                "from bolsa_puntos b " +
                                "where b.fecha_caducidad >= (select CURRENT_DATE) and b.fecha_caducidad <= (select CURRENT_DATE + interval '1' day * :x)",
                        Bolsapuntos.class)
                .setParameter("x", x)
                .getResultList();
    }
}
