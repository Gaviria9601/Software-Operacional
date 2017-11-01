package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_dimension")
@NamedQueries({
	@NamedQuery(name=cliente_dimension.BUSCAR_NOMBRE_CLIENTE,query="select cli from cliente_dimension cli where cli.nombre=?1"),
	@NamedQuery(name=cliente_dimension.LISTAR_CLIENTE_DIMENSION,query="select cli from cliente_dimension cli")
})
public class cliente_dimension implements Serializable{
	
	public static final String LISTAR_CLIENTE_DIMENSION = "ListarClienteDimension";
	
	public static final String BUSCAR_NOMBRE_CLIENTE = "BuscarNombreCliente";

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="genero")
	private String genero;
	
	public cliente_dimension() {
		// TODO Auto-generated constructor stub
	}

	public cliente_dimension(int codigo, String nombre, String genero) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.genero = genero;
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
		cliente_dimension other = (cliente_dimension) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
}
