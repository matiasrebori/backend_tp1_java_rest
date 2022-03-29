package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.CabeceraUsoPuntos;
import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public void actualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    public void eliminar(Integer id) {
        Cliente cliente = this.em.find(Cliente.class, id);
        this.em.remove(cliente);
    }

    /**
     * Módulo 7. Devuelve los clientes por nombre (aproximación)
     *
     * @return List
     * */
    public List<Cliente> getClientesPorNombre(String nombre) {
        return em.createQuery("" +
                                "select c " +
                                "from Cliente c " +
                                "where lower(c.nombre) like :nombre",
                        Cliente.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
    }

    /**
     * Módulo 7. Devuelve los clientes por apellido (aproximación)
     *
     * @return List
     * */
    public List<Cliente> getClientesPorApellido(String apellido) {
        return em.createQuery("" +
                                "select c " +
                                "from Cliente c " +
                                "where lower(c.apellido) like :apellido",
                        Cliente.class)
                .setParameter("apellido", "%" + apellido + "%")
                .getResultList();
    }

    /**
     * Módulo 7. Devuelve los clientes por cumpleaños
     *
     * @return List
     * */
    public List<Cliente> getClientesPorCumpleanhos(String cumpleanhos) throws ParseException {
        Date fecha = new SimpleDateFormat("dd-MM-yyyy").parse(cumpleanhos);
        return em.createQuery("" +
                                "select c " +
                                "from Cliente c " +
                                "where c.fechaNacimiento=:cumpleanhos",
                        Cliente.class)
                .setParameter("cumpleanhos", fecha)
                .getResultList();
    }
}
