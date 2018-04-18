/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Clase encargada de la conexion con la bd wikieam.
 *
 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
 * @date 25/02/2018
 * @version 1.0
 *
 */
public class ConexionWikieam {

	private Logger logger = Logger.getLogger(ConexionWikieam.class.getName());

	protected String driver = "com.mysql.jdbc.Driver"; // nombre del driver
	protected String connectString = "jdbc:mysql://localhost:3306/wikieam"; // ubicacion de la base de datos, para
																			// postgres esta es por defecto
	protected String user = "root"; // usuario de la base de datos
	protected String password = getPassword(); // password de la base de datos
	protected Connection conexionDB; // variable que permite la conexion
	protected Statement sentenciaSQL; // permite la ejecucion de sentencias SQL
	protected ResultSet resultadoDB;// almacena el resultado de una consulta

	/**
	 * 
	 * Metodo encargado de conectar a la base de datos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void conectar() {
		try {
			Class.forName(driver); // se carga el driver en memoria
			conexionDB = DriverManager.getConnection(connectString, user, password);// conexion a la base de datos
			sentenciaSQL = conexionDB.createStatement();// variable que permite ejecutar las sentencias SQL
		} catch (ClassNotFoundException e) {
			logger.info(e.getMessage());
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo encargado de desconectar a la base de datos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
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
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
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
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public ResultSet ejecutarRetorno(String sentencia) {
		try {
			conectar();
			resultadoDB = sentenciaSQL.executeQuery(sentencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultadoDB;
	}
	
	public String getPassword() {
		return "admin";
	}


}
