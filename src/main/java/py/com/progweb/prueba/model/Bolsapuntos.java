package py.com.progweb.prueba.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bolsa_puntos")
public class Bolsapuntos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente id_cliente;

    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha_asignacion;

    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha_caducidad;

    @Column(name = "puntaje_asignado")
    @Basic(optional = false)
    private Integer puntaje_asignado;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntaje_utilizado;

    @Column(name = "saldo_puntos")
    @Basic(optional = false)
    private Integer saldo_puntos;

    @Column(name = "monto_operacion")
    @Basic(optional = false)
    private Integer monto_operacion;

    public Bolsapuntos (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getFecha_asignacion() {
        return fecha_asignacion;
    }

    public void setFecha_asignacion(Date fecha_asignacion) {
        this.fecha_asignacion = fecha_asignacion;
    }

    public Date getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public Integer getPuntaje_asignado() {
        return puntaje_asignado;
    }

    public void setPuntaje_asignado(Integer puntaje_asignado) {
        this.puntaje_asignado = puntaje_asignado;
    }

    public Integer getPuntaje_utilizado() {
        return puntaje_utilizado;
    }

    public void setPuntaje_utilizado(Integer puntaje_utilizado) {
        this.puntaje_utilizado = puntaje_utilizado;
    }

    public Integer getSaldo_puntos() {
        return saldo_puntos;
    }

    public void setSaldo_puntos(Integer saldo_puntos) {
        this.saldo_puntos = saldo_puntos;
    }

    public Integer getMonto_operacion() {
        return monto_operacion;
    }

    public void setMonto_operacion(Integer monto_operacion) {
        this.monto_operacion = monto_operacion;
    }
}
