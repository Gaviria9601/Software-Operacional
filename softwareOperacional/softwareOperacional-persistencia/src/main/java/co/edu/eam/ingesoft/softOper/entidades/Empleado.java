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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Empleado")
@NamedQueries({
	@NamedQuery(name=Empleado.BUSQUEDA__POR_USUARIO,query="select emp from Empleado emp where emp.Usuario.id= ?1")
})
public class Empleado implements Serializable {

	public static final String BUSQUEDA__POR_USUARIO = "Empleado.BusquedaPorUsuario";
	
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaIngresoEmpresa",nullable = false)
	private Date fechaIngresoEmpresa;

	@Column(name="cedula", length=10)
	private String cedula;
	
	@Column(name="genero", length=1)
	private String genero;
	
	@ManyToOne
	@JoinColumn(name = "municipio", nullable=false)
	private Municipio municipio;
	
	@ManyToOne
	@JoinColumn(name = "cargo", nullable=false)
	private Cargo cargo;

	@OneToOne
	@JoinColumn(name = "usuario", nullable=false)
	private Usuario Usuario;

	public Empleado(){
	}
	
	public Empleado(String nombre, String apellido, Date fechaNacimiento, Date fechaIngresoEmpresa,
			String cedula, String genero, Municipio municipio, Cargo cargo,
			co.edu.eam.ingesoft.softOper.entidades.Usuario usuario) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngresoEmpresa = fechaIngresoEmpresa;
		this.cedula = cedula;
		this.genero = genero;
		this.municipio = municipio;
		this.cargo = cargo;
		Usuario = usuario;
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
	 * @return the fechaIngresoEmpresa
	 */
	public Date getFechaIngresoEmpresa() {
		return fechaIngresoEmpresa;
	}

	/**
	 * @param fechaIngresoEmpresa the fechaIngresoEmpresa to set
	 */
	public void setFechaIngresoEmpresa(Date fechaIngresoEmpresa) {
		this.fechaIngresoEmpresa = fechaIngresoEmpresa;
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
		Empleado other = (Empleado) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	/**
	 * @return the municipio
	 */
	public Municipio getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the cargo
	 */
	public Cargo getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return Usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	
	
}
