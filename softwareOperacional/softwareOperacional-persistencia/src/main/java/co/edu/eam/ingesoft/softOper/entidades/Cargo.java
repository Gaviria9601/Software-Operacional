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

@Entity
@Table(name = "Cargo")
@NamedQueries({ @NamedQuery(name = Cargo.LISTAR_CARGOS, query = "select a from Cargo a") })
public class Cargo implements Serializable {

	public static final String LISTAR_CARGOS = "ListarCargos";

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "cargo", length = 30)
	private String cargo;

	@Column(name = "salario")
	private int salario;

	@ManyToOne
	@JoinColumn(name = "area", nullable = false)
	private Area area;

	public Cargo() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the salario
	 */
	public int getSalario() {
		return salario;
	}

	/**
	 * @param salario
	 *            the salario to set
	 */
	public void setSalario(int salario) {
		this.salario = salario;
	}

	/**
	 * @return the areaId
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Cargo other = (Cargo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}
}
