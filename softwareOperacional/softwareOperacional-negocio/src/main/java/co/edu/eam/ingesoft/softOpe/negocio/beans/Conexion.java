package co.edu.eam.ingesoft.softOpe.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class Conexion {

	public static final String OPCION = "primary"; 

	/**
	 * 
	 * @return
	 */
	public String getConexion() {
		if (OPCION.equals("primary")) {
			return "Oracle";
		} else {
			return "MySQL";
		}

	}

}
