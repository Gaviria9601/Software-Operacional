package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Cliente")
@NamedQueries({
	@NamedQuery(name=Cliente.LISTAR_CLIENTES,query="select c from Cliente c"),
})
public class Cliente implements Serializable {
	
	public static final String LISTAR_CLIENTES = "ListarClientes";
	
	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="nombre", length=30)
	private String nombre;
	
	@Column(name="apellido", length=30)
	private String apellido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaNacimiento",nullable = false)
	private Date fechaNacimiento;

	@Column(name="cedula", length=10)
	private String cedula;


	@Column(name="genero", length=1)
	private String genero;
	
	@ManyToOne
	@JoinColumn(name = "municipioNacimiento", nullable=false)
	private Municipio municipioId;
	
	public Cliente(){
		
	}
	
	

	public static String getListarClientes() {
		return LISTAR_CLIENTES;
	}



	public Cliente(String nombre, String apellido, Date fechaNacimiento, String cedula, String genero,
			Municipio municipioId) {
		super();
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.cedula = cedula;
		this.genero = genero;
		this.municipioId = municipioId;
	}



	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the municipioId
	 */
	public Municipio getMunicipioId() {
		return municipioId;
	}

	/**
	 * @param municipioId the municipioId to set
	 */
	public void setMunicipioId(Municipio municipioId) {
		this.municipioId = municipioId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	

}
