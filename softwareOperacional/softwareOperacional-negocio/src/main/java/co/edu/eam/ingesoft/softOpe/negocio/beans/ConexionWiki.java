package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionWiki {
	
	 protected String datasources = "java:jboss/datasources/wikieamDS";
	    protected String user = "root"; //usuario de la base de datos
	    protected String password = "root"; //password de la base de datos
	    protected Connection conexionDB=null; // variable que permite la conexion
	    protected Statement sentenciaSQL; //permite la ejecucion de sentencias SQL
	    protected ResultSet resultadoDB;//almacena el resultado de una consulta
	
	    
	    /**
	     * Permite la conexion de la base de datos
	    */
	    public void conectar() {
	        try {
	        InitialContext ic = new InitialContext(); 
	   		 DataSource ds = (DataSource)ic.lookup("java:jboss/datasources/wikieamDS");
	   		 conexionDB = ds.getConnection();
	   		 sentenciaSQL = conexionDB.createStatement();                               
	      } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            
	        } catch (NamingException ex){
	        	System.out.println(ex.getMessage());
	        	
	        }
	    }
	    


	    /**
	     * Desconecta la conexion de la base de datos
	     */
	    public void desconectar() {
	        try {
	            //sentenciaSQL.close();//cierra la consulta
	            conexionDB.close();//cierra conexion
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * Ejecuta una sentencia sql actualizando la bd
	     * @param sentencia, sentencia
	     * @return true si es verdadera la accion de lo contrario false
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
	      * Ejecuta una sentencia sql sin actualizar , pero si guardando informacion
	      * @param sentencia , sentencia 
	      */
	    public void ejecutarRetorno(String sentencia) {
	        try {
	            conectar();
	            resultadoDB = sentenciaSQL.executeQuery(sentencia);
	            desconectar();
	        } catch (Exception e) {

	        }
	    }



}
