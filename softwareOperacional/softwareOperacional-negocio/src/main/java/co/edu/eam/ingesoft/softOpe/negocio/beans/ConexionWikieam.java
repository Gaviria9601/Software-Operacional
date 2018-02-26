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

/**
 * Clase encargada de la conexion con la bd wikieam.
 *
 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
 * @date 25/02/2018
 * @version 1.0
 *
 */
public class ConexionWikieam {

	protected String driver = "com.mysql.jdbc.Driver"; // nombre del driver
	protected String connectString = "jdbc:mysql://localhost:3306/wikieam"; // ubicacion de la base de datos, para
																			// postgres esta es por defecto
	protected String user = "root"; // usuario de la base de datos
	protected String password = "admin"; // password de la base de datos
	protected Connection conexionDB; // variable que permite la conexion
	protected Statement sentenciaSQL; // permite la ejecucion de sentencias SQL
	protected ResultSet resultadoDB;// almacena el resultado de una consulta

	/**
	 * Permite la conexion de la base de datos
	 */
	public void conectar() {
		try {
			Class.forName(driver); // se carga el driver en memoria
			conexionDB = DriverManager.getConnection(connectString, user, password);// conexion a la base de datos
			sentenciaSQL = conexionDB.createStatement();// variable que permite ejecutar las sentencias SQL
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Desconecta la conexion de la base de datos
	 */
	public void desconectar() {
		try {
			// sentenciaSQL.close();//cierra la consulta
			conexionDB.close();// cierra conexion
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

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

	public ResultSet ejecutarRetorno(String sentencia) {
		try {
			conectar();
			resultadoDB = sentenciaSQL.executeQuery(sentencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultadoDB;
	}

}
