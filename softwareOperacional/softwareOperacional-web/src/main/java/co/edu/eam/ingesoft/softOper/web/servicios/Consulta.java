package co.edu.eam.ingesoft.softOper.web.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta {

	private String producto;
	
	private double venta;
	
	private int cantidad;
	
	private Date fecha;
	
	private String cliente;
	
	private String empleado;
	
	public Consulta() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the empleado
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * @return the venta
	 */
	public double getventa() {
		return venta;
	}

	/**
	 * @param venta the venta to set
	 */
	public void setventa(double venta) {
		this.venta = venta;
	}
	
	
	
}
