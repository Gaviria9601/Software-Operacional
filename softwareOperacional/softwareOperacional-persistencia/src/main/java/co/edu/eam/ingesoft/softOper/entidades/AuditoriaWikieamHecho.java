/**
 * 
 */
package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * <Describir la Clase>
 *
 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
 * @date 24/02/2018
 * @version <Version de la clase>
 *
 */
@Entity
@Table(name = "auditoria_wikieam_hecho")
@NamedQueries({
		@NamedQuery(name = AuditoriaWikieamHecho.LISTAR_AUDITORIA_WIKI, query = "select aud from AuditoriaWikieamHecho aud") })
public class AuditoriaWikieamHecho implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LISTAR_AUDITORIA_WIKI = "AuditoriaWikieamHecho.Listar";

	@Id
	@Column(name = "codigo")
	private Integer codigo;

	@ManyToOne
	@JoinColumn(name = "pagina")
	private PaginaDimensionWikieam pagina;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private UsuarioDimensionWikieam usuario;

	@ManyToOne
	@JoinColumn(name = "cambiosrecientes")
	private CambiosDimensionWikieam cambio;

	/*
	 * 
	 */
	public AuditoriaWikieamHecho() {
		super();
	}

	/**
	 * @param codigo
	 * @param cantidadPaginas
	 * @param cantidadUsuarios
	 * @param cantidadCambios
	 * @param pagina
	 * @param usuario
	 * @param cambio
	 */
	public AuditoriaWikieamHecho(Integer codigo, PaginaDimensionWikieam pagina, UsuarioDimensionWikieam usuario,
			CambiosDimensionWikieam cambio) {
		super();
		this.codigo = codigo;
		this.pagina = pagina;
		this.usuario = usuario;
		this.cambio = cambio;
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
	 * @return the pagina
	 */
	public PaginaDimensionWikieam getPagina() {
		return pagina;
	}

	/**
	 * @param pagina
	 *            the pagina to set
	 */
	public void setPagina(PaginaDimensionWikieam pagina) {
		this.pagina = pagina;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioDimensionWikieam getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(UsuarioDimensionWikieam usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the cambio
	 */
	public CambiosDimensionWikieam getCambio() {
		return cambio;
	}

	/**
	 * @param cambio
	 *            the cambio to set
	 */
	public void setCambio(CambiosDimensionWikieam cambio) {
		this.cambio = cambio;
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
		result = prime * result + codigo;
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
		AuditoriaWikieamHecho other = (AuditoriaWikieamHecho) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}
