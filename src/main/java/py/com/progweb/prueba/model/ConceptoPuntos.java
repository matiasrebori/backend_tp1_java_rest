package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "concepto_puntos")
public class ConceptoPuntos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descripcion", length = 100)
    @Basic(optional = false)
    private String descripcion;

    @Column(name = "puntos_requeridos")
    @Basic(optional = false)
    private Integer puntosRequeridos;
}
