package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "cliente")
//public class Cliente {
//    @Id
//    @GeneratedValue(generator = "clienteSeq", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "clienteSeq", sequenceName = "cliente_seq", allocationSize = 0)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(name = "nombre", length = 100)
//    @Basic(optional = false)
//    private String nombre;
//
//    @Column(name = "apellido", length = 100)
//    @Basic(optional = false)
//    private String apellido;
//
//    public Cliente(){
//
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getApellido() {
//        return apellido;
//    }
//
//    public void setApellido(String apellido) {
//        this.apellido = apellido;
//    }
//}



@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", length = 100)
    @Basic(optional = false)
    private String nombre;

    @Column(name = "apellido", length = 100)
    @Basic(optional = false)
    private String apellido;

    @Column(name = "nro_documento", length = 50)
    @Basic(optional = false)
    private String nroDocumento;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telefono", length = 100)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "nacionalidad_fk", referencedColumnName = "id")
    private Nacionalidad nacionalidad;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_fk", referencedColumnName = "id")
    private TipoDocumento tipoDocumento;

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

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
