package co.edu.eam.ingesoft.softOper.web.controladores;

/**
 * 
 * Clase encargada de la logica de los datos manager
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
public class DatosManager {

	private static int codigoVenta;

	private static int codigoTipoUsuario;

	private static int codigoProducto;

	private static int codigoCliente;

	private static int IdArea;

	private static int codigoEmpleado;

	private static int codigoEmpleado2;

	/**
	 * 
	 * Metodo encargado de obtener el codigo del producto
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public static int getCodigoProducto() {
		return codigoProducto;
	}

	public static void setCodigoProducto(int codigoProducto) {
		DatosManager.codigoProducto = codigoProducto;
	}

	/**
	 * 
	 * Metodo encargado de obtener el codigo del producto
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public static int getCodigoVenta() {
		return codigoVenta;
	}

	public static void setCodigoVenta(int codigoVenta) {
		DatosManager.codigoVenta = codigoVenta;
	}

	/**
	 * 
	 * Metodo encargado de obtener el codigo del tipo de usuario
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public static int getCodigoTipoUsuario() {
		return codigoTipoUsuario;
	}

	public static void setCodigoTipoUsuario(int codigoTipoUsuario) {
		DatosManager.codigoTipoUsuario = codigoTipoUsuario;
	}

	/**
	 * 
	 * Metodo encargado de obtener el codigo del cliente
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public static int getCodigoCliente() {
		return codigoCliente;
	}

	public static void setCodigoCliente(int codigoCliente) {
		DatosManager.codigoCliente = codigoCliente;
	}

	/**
	 * 
	 * Metodo encargado de obtener el identificador del area
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public static int getIdArea() {
		return IdArea;
	}

	public static void setIdArea(int idArea) {
		IdArea = idArea;
	}

	/**
	 * 
	 * Metodo encargado de obtener el codigo del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public static int getCodigoEmpleado() {
		return codigoEmpleado;
	}

	/**
	 * @param codigoEmpleado
	 *            the codigoEmpleado to set
	 */
	public static void setCodigoEmpleado(int codigoEmpleado) {
		DatosManager.codigoEmpleado = codigoEmpleado;
	}

	/**
	 * 
	 * Metodo encargado de obtener el codigo del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public static int getCodigoEmpleado2() {
		return codigoEmpleado2;
	}

	/**
	 * @param codigoEmpleado2
	 *            the codigoEmpleado2 to set
	 */
	public static void setCodigoEmpleado2(int codigoEmpleado2) {
		DatosManager.codigoEmpleado2 = codigoEmpleado2;
	}

}
