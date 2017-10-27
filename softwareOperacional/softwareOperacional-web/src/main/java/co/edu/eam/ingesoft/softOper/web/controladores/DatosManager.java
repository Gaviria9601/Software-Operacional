package co.edu.eam.ingesoft.softOper.web.controladores;

public class DatosManager {
	
	private static int codigoVenta;
	
	private static int codigoTipoUsuario;
	
	private static int codigoProducto;
	

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
	
	

}
