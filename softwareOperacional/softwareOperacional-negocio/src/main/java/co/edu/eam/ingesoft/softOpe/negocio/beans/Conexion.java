package co.edu.eam.ingesoft.softOpe.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Clase encargada de la logica del negocio para el EJB de la conexion a la base
 * de datos
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@Stateless
@LocalBean
public class Conexion {

	public static final String OPCION = "primary";

	/**
	 * Metodo encargado obtener la conexion
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public String getConexion() {
		if (OPCION.equals(OPCION)) {
			return "Oracle";
		} else {
			return "MySQL";
		}

	}

}
