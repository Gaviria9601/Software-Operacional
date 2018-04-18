package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

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
 * @author <Paula Casta�o Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@LocalBean
@Stateless
public class ConexionETL {

	private Logger logger = Logger.getLogger(ConexionETL.class.getName());

	Contrasenia contrasenia;
	protected String datasources = "java:jboss/datasources/ETLDS";
	protected String user = "postgres"; // usuario de la base de datos
	protected String password = contrasenia.getPassword("C:\\Users\\alejo\\git\\"
			+ "Software-Operacional\\softwareOperacional\\bd_credenciales_postgres.txt"); // password de la base de datos
  //protected String password = getPassword();
	protected Connection conexionDB = null; // variable que permite la conexion
	protected Statement sentenciaSQL; // permite la ejecucion de sentencias SQL
	protected ResultSet resultadoDB;// almacena el resultado de una consulta

	public ConexionETL() {
		// TODO Auto-generated constructor stub
		contrasenia = new Contrasenia();
	}

	/**
	 * 
	 * Metodo encargado de conectar a la base de datos
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void conectar() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:jboss/datasources/ETLDS");
			conexionDB = ds.getConnection();
			sentenciaSQL = conexionDB.createStatement();
		} catch (SQLException e) {
			logger.info(e.getMessage());

		} catch (NamingException ex) {
			logger.info(ex.getMessage());

		}
	}

	/**
	 * 
	 * Metodo encargado de desconectar a la base de datos
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void desconectar() {
		try {
			// sentenciaSQL.close();//cierra la consulta
			conexionDB.close();// cierra conexion
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo encargado de Ejecuta una sentencia sql actualizando la base de datos
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
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
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */

	public ResultSet ejecutarRetorno(String sentencia) {
		try {
			conectar();
			resultadoDB = sentenciaSQL.executeQuery(sentencia);
		} catch (Exception e) {

		}
		return resultadoDB;
	}
	
	public String getPassword() {
		return "admin";
	}

}
