package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto_dimension")
public class producto_dimension implements Serializable {

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="precio")
	private int precio;
	
	@Column(name="fechaingreso")
	private Date fechaingreso;
	
	@Column(name="cantidad")
	private int cantidad;
	
	public producto_dimension() {
		// TODO Auto-generated constructor stub
	}

	public producto_dimension(int codigo, String nombre, int precio, Date fechaingreso, int cantidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaingreso = fechaingreso;
		this.cantidad = cantidad;
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		producto_dimension other = (producto_dimension) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
}
