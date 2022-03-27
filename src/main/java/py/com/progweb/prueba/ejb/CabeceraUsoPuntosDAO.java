package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.CabeceraUsoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Módulo 8. DAO de la Cabecera de los registros de los puntos que se van utilizando
 */
@Stateless
public class CabeceraUsoPuntosDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<CabeceraUsoPuntos> listar(){
        return em.createQuery("select c from cabecera_uso_puntos c", CabeceraUsoPuntos.class).getResultList();
    }


    public CabeceraUsoPuntos obtener(Integer id){
        return em.find(CabeceraUsoPuntos.class, id);
    }

    public void crear(CabeceraUsoPuntos cabecera){
        this.em.persist(cabecera);
    }

    public void eliminar(Integer id){
        CabeceraUsoPuntos cabecera = this.em.find(CabeceraUsoPuntos.class, id);
        this.em.remove(cabecera);
    }


    /**
     * Devuelve una lista con los usos de los puntos filtrados por concepto de uso
     * */
    public static List<CabeceraUsoPuntos> getCabecerasPorConcepto(Integer id_concepto){
        return em.createQuery("" +
                        "select c " +
                        "from cabecera_uso_puntos c " +
                        "where c.concepto_uso_puntos=:idConcepto",
                        CabeceraUsoPuntos.class)
                .setParameter("idConcepto", id_concepto)
                .getResultList();
    }

    /**
     * Devuelve una lista con los usos de los puntos filtrados por fecha de uso
     * */
    public static List<CabeceraUsoPuntos> getCabecerasPorFechaUso(Date fecha_uso){
        return em.createQuery("" +
                        "select c " +
                        "from cabecera_uso_puntos c " +
                        "where c.fecha=:fechaUso",
                        CabeceraUsoPuntos.class)
                .setParameter("fechaUso", fecha_uso)
                .getResultList();
    }

    /**
     * Devuelve una lista con los usos de los puntos filtrados por cliente
     * */
    public static List<CabeceraUsoPuntos> getCabecerasPorCliente(Integer id){
        return em.createQuery("" +
                        "select c " +
                        "from cabecera_uso_puntos c " +
                        "where c.id_cliente=:id",
                        CabeceraUsoPuntos.class)
                .setParameter("id", id)
                .getResultList();
    }
}
