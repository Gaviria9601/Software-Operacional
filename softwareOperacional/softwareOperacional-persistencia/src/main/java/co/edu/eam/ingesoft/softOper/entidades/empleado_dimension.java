package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado_dimension")
public class empleado_dimension implements Serializable {

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="genero")
	private String genero;
	
	@Column(name="nombrecargo")
	private String nombrecargo;
	
	public empleado_dimension() {
		// TODO Auto-generated constructor stub
	}

	public empleado_dimension(int codigo, String nombre, String genero, String nombrecargo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.genero = genero;
		this.nombrecargo = nombrecargo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombrecargo() {
		return nombrecargo;
	}

	public void setNombrecargo(String nombrecargo) {
		this.nombrecargo = nombrecargo;
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
		empleado_dimension other = (empleado_dimension) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
}
