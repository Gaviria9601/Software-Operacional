package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Auditoria_hechoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Ventas_hechoEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.auditoria_hecho;
import co.edu.eam.ingesoft.softOper.entidades.navegador_dimension;
import co.edu.eam.ingesoft.softOper.entidades.origen_dimension;
import co.edu.eam.ingesoft.softOper.entidades.tiempo_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

@Named("ETLExtraccionController")
@ViewScoped
public class ETLExtraccionController implements Serializable {

	List<auditoria_hecho> auditorias;

	List<venta_hecho> ventas;

	public List<venta_hecho> getVentas() {
		return ventas;
	}

	public void setVentas(List<venta_hecho> ventas) {
		this.ventas = ventas;
	}

	public List<auditoria_hecho> getAuditorias() {
		return auditorias;
	}

	public void setAuditorias(List<auditoria_hecho> auditorias) {
		this.auditorias = auditorias;
	}

	@EJB
	private Auditoria_hechoEJB audHecEJB;

	@EJB
	private Ventas_hechoEJB venHecEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@PostConstruct
	public void inicializador() {
		auditorias = audHecEJB.listarAuditoriasHecho();
		ventas = venHecEJB.listarVentasHecho();

	}

	/**
	 * 
	 */
	public void extraer() {
		try{
		extrarTablaAuditoriaHecho();
		Messages.addFlashGlobalInfo("Se ha extraido correctamente los Datos");
		}catch (Exception e) {
			Messages.addFlashGlobalError("Ya se han extraido el total de los Datos");
		}
	}

	/**
	 * 
	 */
	public void extrarTablaAuditoriaHecho() {
		
		List<Auditoria> auditorias = new ArrayList<Auditoria>();
		
		List<Auditoria> clientes = audEJB.listarAuditoriaClientes();
		List<Auditoria> empleados = audEJB.listarAuditoriaEmpleados();
		List<Auditoria> inventarios = audEJB.listarAuditoriaInventarios();
		List<Auditoria> areas = audEJB.listarAuditoriasArea();
		List<Auditoria> identificacionUsuarios = audEJB.listarAuditoriasIdeUsuarios();
		List<Auditoria> tipoUsuarios = audEJB.listarAuditoriaTiposUsuarios();
		List<Auditoria> usuarios = audEJB.listarAuditoriaUsuarios();
		List<Auditoria> ventas = audEJB.listarAuditoriaVentas();
		
		for (Auditoria cliente : clientes) {
			auditorias.add(cliente);
		}
		
		for (Auditoria empleado : empleados) {
			auditorias.add(empleado);
		}
		
		for (Auditoria inventario : inventarios) {
			auditorias.add(inventario);
		}
		
		for (Auditoria area : areas) {
			auditorias.add(area);
		}
		
		for (Auditoria idUsuario : identificacionUsuarios) {
			auditorias.add(idUsuario);
		}
		
		for (Auditoria tipoUsuario : tipoUsuarios) {
			auditorias.add(tipoUsuario);
		}
		
		for (Auditoria usuario : usuarios) {
			auditorias.add(usuario);
		}
		
		for (Auditoria venta : ventas) {
			auditorias.add(venta);
		}
		
		for (Auditoria auditoria : auditorias) {
			auditoria_hecho audiHecho = new auditoria_hecho();
			audiHecho.setCodigo(auditoria.getCodigo());
			audiHecho.setAccion(auditoria.getAccion());
			audiHecho.setFechaauditoria(auditoria.getFechaHora());
			audiHecho.setTablaaccion(auditoria.getRegistroRealizoAccion());
			navegador_dimension navDim = new navegador_dimension();
			navDim.setNavegador(auditoria.getNavegador());
			audHecEJB.ingresarNavegadorDimension(navDim);
			navegador_dimension navIng = audHecEJB.listarNavegadorDimension().get(audHecEJB.listarNavegadorDimension().size()-1);
			audiHecho.setNavegador(navIng);
			origen_dimension oriDim = new origen_dimension();
			oriDim.setDispositivo(auditoria.getOrigen());
			audHecEJB.ingresarOrigenDimension(oriDim);
			origen_dimension oriIng = audHecEJB.listarOrigenDimension().get(audHecEJB.listarOrigenDimension().size()-1);
			audiHecho.setOrigen(oriIng);
			audHecEJB.ingresarAuditoriaHecho(audiHecho);
		}
		this.auditorias = audHecEJB.listarAuditoriasHecho();
		

	}

	/**
	 * public void conectar() { Connection con = null; try { InitialContext ic =
	 * new InitialContext(); // en esta parte es donde ponemos el Nombre // de
	 * JNDI para que traiga el datasource DataSource ds = (DataSource)
	 * ic.lookup("java:jboss/datasources/ETLDS"); con = ds.getConnection();
	 * Statement st = con.createStatement(); Messages.addFlashGlobalInfo("Se ha
	 * realizado con exito la conexión a Postgres"); // el resultSet es el
	 * encargado de traer los datos de la consulta ResultSet rs =
	 * st.executeQuery("select * from cliente_dimension"); while (rs.next()) {
	 * System.out.println(" " + rs.getString(1) + " " + rs.getString(2)); } }
	 * catch (SQLException ex) {
	 * Logger.getLogger(ETLExtraccionController.class.getName()).log(Level.SEVERE,
	 * null, ex); } catch (NamingException ex) {
	 * Logger.getLogger(ETLExtraccionController.class.getName()).log(Level.SEVERE,
	 * null, ex); } finally { try { con.close(); System.out.println("Conexion
	 * Cerrada con Exito..."); } catch (SQLException ex) {
	 * Logger.getLogger(ETLExtraccionController.class.getName()).log(Level.SEVERE,
	 * null, ex); } } }
	 */

}
