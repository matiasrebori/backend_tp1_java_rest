package py.com.progweb.prueba.model;
import javax.persistence.*;

@Entity
@Table(name = "puntos")
public class Puntos {
    @Id
    @GeneratedValue(generator = "puntosSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "puntosSeq", sequenceName = "puntos_Seq", allocationSize = 0)
    @Basic(optional = false)
    @Column(name = "id_puntos")
    private Integer id;

    @Column(name = "lim_inf")
    @Basic(optional = false)
    private Integer lim_inf;

    @Column(name = "lim_sup")
    @Basic(optional = false)
    private String lim_sup;

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

    public String getLim_sup() {
        return lim_sup;
    }

    public void setLim_sup(String lim_sup) {
        this.lim_sup = lim_sup;
    }
}
