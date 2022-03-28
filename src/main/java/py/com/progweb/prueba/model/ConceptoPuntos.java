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

    public ConceptoPuntos(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
