package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.DetalleUsoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Módulo 8. DAO del Detalle de la cabecera de uso de puntos
 */
@Stateless
public class DetalleUsoPuntosDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<DetalleUsoPuntos> listar(){
        return em.createQuery("select detalle from detalle_uso_puntos", DetalleUsoPuntos.class).getResultList();
    }

    public DetalleUsoPuntos obtener(Integer id){
        return em.find(DetalleUsoPuntos.class, id);
    }

    public void crear(DetalleUsoPuntos detalle){
        this.em.persist(detalle);
    }

    public void eliminar(Integer id){
        DetalleUsoPuntos detalle = this.em.find(DetalleUsoPuntos.class, id);
        this.em.remove(detalle);
    }

}
