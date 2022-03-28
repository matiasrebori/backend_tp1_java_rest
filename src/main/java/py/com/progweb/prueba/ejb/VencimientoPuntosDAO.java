package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class VencimientoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<VencimientoPuntos> listar() {
        return em.createQuery("select v from VencimientoPuntos v", VencimientoPuntos.class).getResultList();
    }

    public VencimientoPuntos obtener(Integer id) {
        return em.find(VencimientoPuntos.class, id);
    }

    public void crear(VencimientoPuntos vencimientoPuntos) {
        this.em.persist(vencimientoPuntos);
    }

    public void actualizar(VencimientoPuntos vencimientoPuntos) {
        this.em.merge(vencimientoPuntos);
    }

    public void eliminar(Integer id) {
        VencimientoPuntos vencimientoPuntos = this.em.find(VencimientoPuntos.class, id);
        this.em.remove(vencimientoPuntos);
    }
}
