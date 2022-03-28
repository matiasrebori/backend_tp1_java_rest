package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
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

    public Date getFechaCaducidad() {

        List<VencimientoPuntos> filas = this.listar();
        VencimientoPuntos fila_1 = filas.get(filas.size()-1);

        Integer dias_duracion = fila_1.getDias_duracion();

        LocalDate fecha_fin = LocalDate.parse(new Date(System.currentTimeMillis()).toString());
        fecha_fin = fecha_fin.plusDays(dias_duracion);

        if ( fecha_fin.isBefore(LocalDate.parse(fila_1.getFecha_fin().toString())) ){
            return new Date(fecha_fin.toString());
        } else{
            return fila_1.getFecha_fin();
        }
    }
}
