package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Puntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class PuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<Puntos> listar() {
        return em.createQuery("select p from Puntos p", Puntos.class).getResultList();
    }

    public Puntos obtener(Integer id) {
        return em.find(Puntos.class, id);
    }

    public void crear(Puntos punto) {
        this.em.persist(punto);
    }

    public void eliminar(Integer id) {
        Puntos puntos = this.em.find(Puntos.class, id);
        this.em.remove(puntos);
    }

    public Integer puntosTotalesGenerados(Integer monto) {

        // ver cuántos puntos genera este monto
        List<Puntos> listaReglas = em.createQuery("" +
                                "select r " +
                                "from Puntos r " +
                                "where r.lim_inf is null " +
                                "or :monto between r.lim_inf and r.lim_sup",
                        Puntos.class)
                .setParameter("monto", monto)
                .getResultList();
        // calcular puntos. Se utiliza la función floor para asignar los puntos
        int puntos = 0;
        for (Puntos r: listaReglas) {
            puntos += Math.floorDiv(monto,r.getMonto_equivalencia());
        }

        return puntos;
    }
}
