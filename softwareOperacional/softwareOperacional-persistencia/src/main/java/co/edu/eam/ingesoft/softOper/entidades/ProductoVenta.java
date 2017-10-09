package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ProductoVenta")
@IdClass(ProductoVentaPK.class)
public class ProductoVenta implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="producto")
	private Producto producto_codigo;
	
	@Id
	@ManyToOne
	@JoinColumn(name="venta")
	private Venta venta_codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha",nullable = false)
	private Date fecha;
	
	@Column(name="total")
	private int total;

	public ProductoVenta(){
	}

	/**
	 * @return the producto_codigo
	 */
	public Producto getProducto_codigo() {
		return producto_codigo;
	}

	/**
	 * @param producto_codigo the producto_codigo to set
	 */
	public void setProducto_codigo(Producto producto_codigo) {
		this.producto_codigo = producto_codigo;
	}

	/**
	 * @return the venta_codigo
	 */
	public Venta getVenta_codigo() {
		return venta_codigo;
	}

	/**
	 * @param venta_codigo the venta_codigo to set
	 */
	public void setVenta_codigo(Venta venta_codigo) {
		this.venta_codigo = venta_codigo;
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
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producto_codigo == null) ? 0 : producto_codigo.hashCode());
		result = prime * result + ((venta_codigo == null) ? 0 : venta_codigo.hashCode());
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
		ProductoVenta other = (ProductoVenta) obj;
		if (producto_codigo == null) {
			if (other.producto_codigo != null)
				return false;
		} else if (!producto_codigo.equals(other.producto_codigo))
			return false;
		if (venta_codigo == null) {
			if (other.venta_codigo != null)
				return false;
		} else if (!venta_codigo.equals(other.venta_codigo))
			return false;
		return true;
	}

	/**
	 * @return the producto
	 */

	
	
	

}


