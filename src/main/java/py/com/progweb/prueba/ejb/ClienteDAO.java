package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.CabeceraUsoPuntos;
import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ClienteDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<Cliente> listar() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    public Cliente obtener(Integer id) {
        return em.find(Cliente.class, id);
    }

    public void crear(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void eliminar(Integer id) {
        Cliente cliente = this.em.find(Cliente.class, id);
        this.em.remove(cliente);
    }

    /**
     * Módulo 7. Devuelve los clientes por nombre (aproximación)
     * */
    public static List<Cliente> getClientesPorNombre(String nombre) {
        return em.createQuery("" +
                                "select c " +
                                "from cliente c " +
                                "where c.nombre like :nombre",
                        CabeceraUsoPuntos.class)
                .setParameter("nombre", nombre)
                .getResultList();
    }

    /**
     * Módulo 7. Devuelve los clientes por apellido (aproximación)
     * */
    public static List<Cliente> getClientesPorApellido(String apellido) {
        return em.createQuery("" +
                                "select c " +
                                "from cliente c " +
                                "where c.apellido like :apellido",
                        CabeceraUsoPuntos.class)
                .setParameter("apellido", nombre)
                .getResultList();
    }

    /**
     * Módulo 7. Devuelve los clientes por cumpleaños
     * */
    public static Object getClientesPorCumpleanhos(String cumpleanhos) {
        return em.createQuery("" +
                                "select c " +
                                "from cliente c " +
                                "where c.fecha_nacimiento=:cumpleanhos",
                        CabeceraUsoPuntos.class)
                .setParameter("cumpleanhos", nombre)
                .getResultList();
    }
}
