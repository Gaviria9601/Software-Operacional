package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_dimension_wikieam")
@NamedQueries({
		@NamedQuery(name = UsuarioDimensionWikieam.LISTAR_USW, query = "select usw from UsuarioDimensionWikieam usw")

})
public class UsuarioDimensionWikieam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LISTAR_USW = "UsuarioDimensionWikieam.listarusw";

	@Id
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "nombreusuario")
	private String nombreUsuario;

	@Column(name = "nombre")
	private String nombre;

	/**
	 * 
	 */
	public UsuarioDimensionWikieam() {
		super();
	}

	/**
	 * @param codigo
	 * @param nombreUsuario
	 * @param nombre
	 */
	public UsuarioDimensionWikieam(Integer codigo, String nombreUsuario, String nombre) {
		super();
		this.codigo = codigo;
		this.nombreUsuario = nombreUsuario;
		this.nombre = nombre;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario
	 *            the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		UsuarioDimensionWikieam other = (UsuarioDimensionWikieam) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
