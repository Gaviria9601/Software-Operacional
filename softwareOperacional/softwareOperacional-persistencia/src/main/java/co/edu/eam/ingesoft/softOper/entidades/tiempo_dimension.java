package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tiempo_dimension")
@NamedQueries({ @NamedQuery(name = tiempo_dimension.LISTAR_TIEMPO, query = "select t from tiempo_dimension t") })
public class tiempo_dimension implements Serializable {

	public static final String LISTAR_TIEMPO = "tiempo_dimension.listarTiempo";

	@Id
	@Column(name = "codigo", nullable = false)
	private int codigo;

	@Column(name = "trimestre")
	private String trimestre;

	@Column(name = "mes")
	private String mes;

	public tiempo_dimension() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigo
	 */
	public tiempo_dimension(int codigo) {
		super();
		this.codigo = codigo;
	}

	public tiempo_dimension(int codigo, String trimestre, String mes) {
		super();
		this.codigo = codigo;
		this.trimestre = trimestre;
		this.mes = mes;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		tiempo_dimension other = (tiempo_dimension) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}
