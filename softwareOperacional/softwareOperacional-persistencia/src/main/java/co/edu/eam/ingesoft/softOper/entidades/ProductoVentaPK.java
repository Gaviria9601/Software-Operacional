package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

public class ProductoVentaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int producto_codigo;

	private int venta_codigo;

	public ProductoVentaPK() {
		super();
	}

	public ProductoVentaPK(int producto_codigo, int venta_codigo) {
		super();
		this.producto_codigo = producto_codigo;
		this.venta_codigo = venta_codigo;
	}

	/**
	 * @return the producto_codigo
	 */
	public int getProducto_codigo() {
		return producto_codigo;
	}

	/**
	 * @param producto_codigo
	 *            the producto_codigo to set
	 */
	public void setProducto_codigo(int producto_codigo) {
		this.producto_codigo = producto_codigo;
	}

	/**
	 * @return the venta_codigo
	 */
	public int getVenta_codigo() {
		return venta_codigo;
	}

	/**
	 * @param venta_codigo
	 *            the venta_codigo to set
	 */
	public void setVenta_codigo(int venta_codigo) {
		this.venta_codigo = venta_codigo;
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
		result = prime * result + producto_codigo;
		result = prime * result + venta_codigo;
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
		ProductoVentaPK other = (ProductoVentaPK) obj;
		if (producto_codigo != other.producto_codigo)
			return false;
		if (venta_codigo != other.venta_codigo)
			return false;
		return true;
	}

	/**
	 * @return the producto_codigo
	 */

}