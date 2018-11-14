package co.edu.eam.ingesoft.softOper.web.servicios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta4 {

	private double venta;

	private Date fecha;
	
	public Consulta4() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param venta
	 * @param fecha
	 */
	public Consulta4(double venta, Date fecha) {
		super();
		this.venta = venta;
		this.fecha = fecha;
	}

	/**
	 * @return the venta
	 */
	public double getVenta() {
		return venta;
	}

	/**
	 * @param venta the venta to set
	 */
	public void setVenta(double venta) {
		this.venta = venta;
	}

	/**
	 * @return the fecha
	 * @throws ParseException 
	 */
	public String getFecha() throws ParseException {	
		DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return outputFormat.format(fecha);
	}


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
