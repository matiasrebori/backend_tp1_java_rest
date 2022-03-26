package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(generator = "clienteSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clienteSeq", sequenceName = "cliente_seq", allocationSize = 0)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", length = 100)
    @Basic(optional = false)
    private String nombre;

    @Column(name = "apellido", length = 100)
    @Basic(optional = false)
    private String apellido;

    public Cliente(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
