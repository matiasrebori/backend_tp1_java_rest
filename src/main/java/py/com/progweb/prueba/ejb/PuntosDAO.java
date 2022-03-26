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
}
