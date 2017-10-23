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
@Table(name = "Municipio")
@NamedQueries({
    @NamedQuery (name=Municipio.LISTAR_MUNI, query="select a from Municipio a"),
    @NamedQuery (name=Municipio.LISTAR_MUNIPorDepto, query="select a from Municipio a where a.departamento.nombre = ?1")
  
})
public class Municipio implements Serializable{
	
	
	public static final String LISTAR_MUNI = "ListarMunici";
	public static final String LISTAR_MUNIPorDepto = "listatMunixDepto";
	
	@Id
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="nombre", length=20)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "departamento", nullable=false)
	private Departamento departamento;
	
	public Municipio(){
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Municipio other = (Municipio) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return the departamento
	 */
	public Departamento getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
