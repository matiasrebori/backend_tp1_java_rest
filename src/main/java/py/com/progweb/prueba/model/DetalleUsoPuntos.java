package py.com.progweb.prueba.model;

import javax.persistence.*;

/**
 * Módulo 8. Detalle de la cabecera de uso de puntos
 */
@Entity
@Table(name = "detalle_uso_puntos")
public class DetalleUsoPuntos {
    @Id
    @GeneratedValue(generator = "detalle_uso_puntosSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "detalle_uso_puntosSeq", sequenceName = "detalle_uso_puntos_seq", allocationSize = 0)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cabecera")
    private CabeceraUsoPuntos idCabecera;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntajeUtilizado;

    // TODO: Ponerle el nombre correcto de la tabla a la que está apuntando
    @ManyToOne
    @JoinColumn(name = "id_bolsa_de_puntos")
    private BolsaDePuntos idBolsaDePuntos;
}
