package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Módulo 8. Cabecera de los registros de los puntos que se van utilizando
 */
@Entity
@Table(name = "cabecera_uso_puntos")
public class CabeceraUsoPuntos {


    @Id
    @GeneratedValue(generator = "cabecera_uso_puntosSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cabecera_uso_puntosSeq", sequenceName = "cabecera_uso_puntos_seq", allocationSize = 0)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntajeUtilizado;

    @Column(name = "fecha")
    @Basic(optional = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_concepto_uso_puntos")
    private ConceptoPuntos conceptoPuntos;

    public CabeceraUsoPuntos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ConceptoPuntos getConceptoPuntos() {
        return conceptoPuntos;
    }

    public void setConceptoUsoPunto(ConceptoPuntos conceptoUsoPunto) {
        this.conceptoPuntos = conceptoUsoPunto;
    }
}
