package py.com.progweb.prueba.model;
import javax.persistence.*;

@Entity
@Table(name = "puntos")
public class Puntos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puntos")
    private Integer id;

    @Column(name = "lim_inf")
    @Basic(optional = false)
    private Integer lim_inf;

    @Column(name = "lim_sup")
    @Basic(optional = false)
    private Integer lim_sup;

    @Column(name = "monto_equivalencia")
    @Basic(optional = false)
    private Integer monto_equivalencia;

    public Puntos(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLim_inf() {
        return lim_inf;
    }

    public void setLim_inf(Integer lim_inf) {
        this.lim_inf = lim_inf;
    }

    public Integer getLim_sup() {
        return lim_sup;
    }

    public void setLim_sup(Integer lim_sup) {
        this.lim_sup = lim_sup;
    }

    public Integer getMonto_equivalencia() {
        return monto_equivalencia;
    }

    public void setMonto_equivalencia(Integer monto_equivalencia) {
        this.monto_equivalencia = monto_equivalencia;
    }
}
