package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB de la conexion al
 * proceso ETL.
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@LocalBean
@Stateless
public class ConexionETL {

	protected String datasources = "java:jboss/datasources/ETLDS";
	protected String user = "postgres"; // usuario de la base de datos
	protected String password = "admin"; // password de la base de datos
	protected Connection conexionDB = null; // variable que permite la conexion
	protected Statement sentenciaSQL; // permite la ejecucion de sentencias SQL
	protected ResultSet resultadoDB;// almacena el resultado de una consulta

	/**
	 * 
	 * Metodo encargado de conectar a la base de datos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void conectar() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:jboss/datasources/ETLDS");
			conexionDB = ds.getConnection();
			sentenciaSQL = conexionDB.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} catch (NamingException ex) {
			System.out.println(ex.getMessage());

		}
	}

	/**
	 * 
	 * Metodo encargado de desconectar a la base de datos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void desconectar() {
		try {
			// sentenciaSQL.close();//cierra la consulta
			conexionDB.close();// cierra conexion
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo encargado de Ejecuta una sentencia sql actualizando la base de datos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public boolean ejecutar(String sentencia) {
		try {
			conectar();
			sentenciaSQL.executeUpdate(sentencia);
			desconectar();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Metodo encargado de Ejecuta una sentencia sql sin actualizar , pero si
	 * guardando informacion una sentencia sql actualizando la base de datos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */

	public ResultSet ejecutarRetorno(String sentencia) {
		try {
			conectar();
			resultadoDB = sentenciaSQL.executeQuery(sentencia);
		} catch (Exception e) {

		}
		return resultadoDB;
	}

}
