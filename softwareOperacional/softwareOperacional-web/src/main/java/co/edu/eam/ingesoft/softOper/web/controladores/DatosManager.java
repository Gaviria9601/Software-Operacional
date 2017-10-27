package co.edu.eam.ingesoft.softOper.web.controladores;

public class DatosManager {
	
	private static int codigoVenta;
	
	private static int codigoTipoUsuario;
	
	private static int codigoProducto;
	
	private static int codigoCliente;
	
	private static int codigoArea;
	

	public static int getCodigoProducto() {
		return codigoProducto;
	}

	public static void setCodigoProducto(int codigoProducto) {
		DatosManager.codigoProducto = codigoProducto;
	}

	public static int getCodigoVenta() {
		return codigoVenta;
	}

	public static void setCodigoVenta(int codigoVenta) {
		DatosManager.codigoVenta = codigoVenta;
	}

	/**
	 * @return the codigoTipoUsuario
	 */
	public static int getCodigoTipoUsuario() {
		return codigoTipoUsuario;
	}

	/**
	 * @param codigoTipoUsuario the codigoTipoUsuario to set
	 */
	public static void setCodigoTipoUsuario(int codigoTipoUsuario) {
		DatosManager.codigoTipoUsuario = codigoTipoUsuario;
	}

	public static int getCodigoCliente() {
		return codigoCliente;
	}

	public static void setCodigoCliente(int codigoCliente) {
		DatosManager.codigoCliente = codigoCliente;
	}

	public static int getCodigoArea() {
		return codigoArea;
	}

	public static void setCodigoArea(int codigoArea) {
		DatosManager.codigoArea = codigoArea;
	}
	
	

}
