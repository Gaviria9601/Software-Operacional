package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Venta")
public class Venta implements Serializable {

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha",nullable = false)
	private Date fecha;
	
	@Column(name="totalVenta")
	private int totalVenta;

	@ManyToOne
	@JoinColumn(name = "vendedor", nullable=false)
	private Empleado vendedor;
	
	@ManyToOne
	@JoinColumn(name = "cliente", nullable=false)
	private Cliente cliente;
	
	public Venta(){
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the totalVenta
	 */
	public int getTotalVenta() {
		return totalVenta;
	}

	/**
	 * @param totalVenta the totalVenta to set
	 */
	public void setTotalVenta(int totalVenta) {
		this.totalVenta = totalVenta;
	}

	/**
	 * @return the empleado
	 */
	

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Venta other = (Venta) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	/**
	 * @return the vendedor
	 */
	public Empleado getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Empleado vendedor) {
		this.vendedor = vendedor;
	}
	
	
	
}
