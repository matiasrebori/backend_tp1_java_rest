package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.ZoneId;
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

        LocalDate fecha_fin = LocalDate.now();
        fecha_fin = fecha_fin.plusDays(dias_duracion);
        LocalDate fecha_fin_fila_1 = fila_1.getFecha_fin()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if ( fecha_fin.isBefore(fecha_fin_fila_1)){
            return java.sql.Date.valueOf(fecha_fin);
        } else{
            return fila_1.getFecha_fin();
        }
    }
}
