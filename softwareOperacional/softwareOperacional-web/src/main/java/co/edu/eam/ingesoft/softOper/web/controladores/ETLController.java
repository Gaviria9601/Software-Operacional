package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;


@Named("etlController")
@ViewScoped
public class ETLController implements Serializable {
	

	/**
	 * 
	 */
	public void conectar() {
		Connection con = null;
		try {
			InitialContext ic = new InitialContext();
			// en esta parte es donde ponemos el Nombre
			// de JNDI para que traiga el datasource
			DataSource ds = (DataSource) ic.lookup("java:jboss/datasources/ETLDS");
			con = ds.getConnection();
			Statement st = con.createStatement();
			Messages.addFlashGlobalInfo("Se ha realizado con exito la conexión a Postgres");
			// el resultSet es el encargado de traer los datos de la consulta
			ResultSet rs = st.executeQuery("select * from Usuario");
			while (rs.next()) {
				System.out.println(" " + rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ETLController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NamingException ex) {
			Logger.getLogger(ETLController.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				con.close();
				System.out.println("Conexion Cerrada con Exito...");
			} catch (SQLException ex) {
				Logger.getLogger(ETLController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
