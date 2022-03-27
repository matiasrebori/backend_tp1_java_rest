package py.com.progweb.prueba.ejb;


import py.com.progweb.prueba.model.Nacionalidad;
import py.com.progweb.prueba.model.TipoDocumento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class NacionalidadDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<Nacionalidad> listar() {
        return em.createQuery("select c from Nacionalidad c", Nacionalidad.class).getResultList();
    }

    public Nacionalidad obtener(Integer id) {
        return em.find(Nacionalidad.class, id);
    }

    public void crear(Nacionalidad nacionalidad) {
        this.em.persist(nacionalidad);
    }

    public void eliminar(Integer id) {
        Nacionalidad nacionalidad = this.em.find(Nacionalidad.class, id);
        this.em.remove(nacionalidad);
    }
}
