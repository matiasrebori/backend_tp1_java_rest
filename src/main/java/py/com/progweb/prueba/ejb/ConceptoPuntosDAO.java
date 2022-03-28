package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.ConceptoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ConceptoPuntosDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<ConceptoPuntos> listar() {
        return em.createQuery("select c from ConceptoPuntos c", ConceptoPuntos.class).getResultList();
    }

    public ConceptoPuntos obtener(Integer id) {
        return em.find(ConceptoPuntos.class, id);
    }

    public void crear(ConceptoPuntos conceptoPuntos) {
        this.em.persist(conceptoPuntos);
    }

    public void actualizar(ConceptoPuntos conceptoPuntos) {
        this.em.merge(conceptoPuntos);
    }

    public void eliminar(Integer id) {
        ConceptoPuntos conceptoPuntos = this.em.find(ConceptoPuntos.class, id);
        this.em.remove(conceptoPuntos);
    }

}
