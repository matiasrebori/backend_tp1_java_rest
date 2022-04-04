package py.com.progweb.prueba.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vencimiento_puntos")
public class VencimientoPuntos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha_inicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha_fin;

    @Column(name = "dias_duracion")
    @Basic(optional = false)
    private Integer dias_duracion;

    public VencimientoPuntos (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Integer getDias_duracion() {
        return dias_duracion;
    }

    public void setDias_duracion(Integer dias_duracion) {
        this.dias_duracion = dias_duracion;
    }
}
