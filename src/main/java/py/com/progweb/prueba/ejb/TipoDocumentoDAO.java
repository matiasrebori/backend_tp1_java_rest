package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.TipoDocumento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TipoDocumentoDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<TipoDocumento> listar() {
        return em.createQuery("select c from TipoDocumento c", TipoDocumento.class).getResultList();
    }

    public TipoDocumento obtener(Integer id) {
        return em.find(TipoDocumento.class, id);
    }

    public void crear(TipoDocumento tipoDocumento) {
        this.em.persist(tipoDocumento);
    }

    public void eliminar(Integer id) {
        TipoDocumento tipoDocumento = this.em.find(TipoDocumento.class, id);
        this.em.remove(tipoDocumento);
    }
}
