package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cambios_dimension_wikieam")
@NamedQueries({
		@NamedQuery(name = CambiosDimensionWikieam.LISTAR_CDW, query = "select cde from CambiosDimensionWikieam cde")

})
public class CambiosDimensionWikieam implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LISTAR_CDW = "CambiosDimensionWikieam.Listarcde";

	@Id
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "fechaC")
	private Date fechaC;

	@Column(name = "fechaE")
	private Date fechaE;

	@Column(name = "numeroLineasC")
	private int numeroLineasC;

	@Column(name = "numeroLineasE")
	private int numeroLineasE;

	/*
	 * 
	 */
	public CambiosDimensionWikieam() {
		super();
	}

	/**
	 * @param codigo
	 * @param fechaC
	 * @param fechaE
	 * @param numeroLineasC
	 * @param numeroLineasE
	 */
	public CambiosDimensionWikieam(Integer codigo, Date fechaC, Date fechaE, int numeroLineasC, int numeroLineasE) {
		super();
		this.codigo = codigo;
		this.fechaC = fechaC;
		this.fechaE = fechaE;
		this.numeroLineasC = numeroLineasC;
		this.numeroLineasE = numeroLineasE;
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
	 * @return the fechaC
	 */
	public Date getFechaC() {
		return fechaC;
	}

	/**
	 * @param fechaC
	 *            the fechaC to set
	 */
	public void setFechaC(Date fechaC) {
		this.fechaC = fechaC;
	}

	/**
	 * @return the fechaE
	 */
	public Date getFechaE() {
		return fechaE;
	}

	/**
	 * @param fechaE
	 *            the fechaE to set
	 */
	public void setFechaE(Date fechaE) {
		this.fechaE = fechaE;
	}

	/**
	 * @return the numeroLineasC
	 */
	public int getNumeroLineasC() {
		return numeroLineasC;
	}

	/**
	 * @param numeroLineasC
	 *            the numeroLineasC to set
	 */
	public void setNumeroLineasC(int numeroLineasC) {
		this.numeroLineasC = numeroLineasC;
	}

	/**
	 * @return the numeroLineasE
	 */
	public int getNumeroLineasE() {
		return numeroLineasE;
	}

	/**
	 * @param numeroLineasE
	 *            the numeroLineasE to set
	 */
	public void setNumeroLineasE(int numeroLineasE) {
		this.numeroLineasE = numeroLineasE;
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
		CambiosDimensionWikieam other = (CambiosDimensionWikieam) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
