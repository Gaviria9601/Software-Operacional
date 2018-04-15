package co.edu.eam.ingesoft.softOpe.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB de la conexion a la base
 * de datos
 * 
 * @author <Paula Casta�o Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@Stateless
@LocalBean
public class Conexion {

	public static final String OPCION = "primary";

	/**
	 * 
	 * Metodo encargado obtener la conexion
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String getConexion() {
		if (OPCION.equals("primary")) {
			return "Oracle";
		} else {
			return "MySQL";
		}

	}

}
