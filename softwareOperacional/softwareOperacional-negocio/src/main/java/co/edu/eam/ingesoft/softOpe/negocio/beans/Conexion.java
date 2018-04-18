package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.HashMap;
import java.util.Map;

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

	private static final Map<String, String> conexiones = new HashMap<String,String>();

	/**
	 * Metodo encargado obtener la conexion
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public String getConexion() {
		conexiones.put("primary", "Oracle");
		conexiones.put("secundary", "MySQL");
		return conexiones.get("primary");
	}

}
