/**
 * 
 */
package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "pagina_dimension_wikieam")
@NamedQueries({
		@NamedQuery(name = PaginaDimensionWikieam.LISTAR_PDW, query = "select pwe from PaginaDimensionWikieam pwe")

})
public class PaginaDimensionWikieam implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LISTAR_PDW = "PaginaDimensionWikieam.listarpwe";

	@Id
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "tituloPagina")
	private String tituloPagina;

	/*
	 * Constructor por defecto.
	 */
	public PaginaDimensionWikieam() {
		super();
	}

	/**
	 * 
	 * @param codigo
	 * @param tituloPagina
	 */
	public PaginaDimensionWikieam(Integer codigo, String tituloPagina) {
		super();
		this.codigo = codigo;
		this.tituloPagina = tituloPagina;
	}

	

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the tituloPagina
	 */
	public String getTituloPagina() {
		return tituloPagina;
	}

	/**
	 * @param tituloPagina the tituloPagina to set
	 */
	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
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
		PaginaDimensionWikieam other = (PaginaDimensionWikieam) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	

}
