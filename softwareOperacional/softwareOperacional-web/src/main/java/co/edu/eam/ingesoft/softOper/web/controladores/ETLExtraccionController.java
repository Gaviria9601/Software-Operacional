package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Auditoria_hechoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoVentaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Ventas_hechoEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.ProductoVenta;
import co.edu.eam.ingesoft.softOper.entidades.auditoria_hecho;
import co.edu.eam.ingesoft.softOper.entidades.cliente_dimension;
import co.edu.eam.ingesoft.softOper.entidades.empleado_dimension;
import co.edu.eam.ingesoft.softOper.entidades.navegador_dimension;
import co.edu.eam.ingesoft.softOper.entidades.origen_dimension;
import co.edu.eam.ingesoft.softOper.entidades.producto_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

/**
 * 
 * Clase encargada de la logica del controlador para la extraccion del proceso
 * de ETL
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@Named("ETLExtraccionController")
@ViewScoped
public class ETLExtraccionController implements Serializable {

	private Logger logger = Logger.getLogger(ETLExtraccionController.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<auditoria_hecho> auditorias;

	private List<venta_hecho> ventas;

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
	private ProductoVentaEJB proVenEJB;

	@EJB
	private Auditoria_hechoEJB audHecEJB;

	@EJB
	private Ventas_hechoEJB venHecEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializador() {
		auditorias = audHecEJB.listarAuditoriasHecho();
		ventas = venHecEJB.listarVentasHecho();

	}

	/**
	 * 
	 * Metodo encargado de extraer la auditoria
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void extraerAuditoria() {
		try {
			extraerTablaAuditoriaHecho();
			registrarAuditoria("Extracción de Auditoria Hecho");
			Messages.addFlashGlobalInfo("Se ha extraido correctamente los Datos hasta la fecha");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Ya se han extraido el total de los Datos");
		}
	}

	/**
	 * 
	 * Metodo encargado de extraer la venta
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void extraerVenta() {
		try {
			extraerTablaVentaHecho();
			registrarAuditoria("Extracción de Venta Hecho");
			Messages.addFlashGlobalInfo("Se ha extraido correctamente los Datos hasta la fecha");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Ya se han extraido el total de los Datos");
		}
	}

	/**
	 * 
	 * Metodo encargado de extraer la auditoria hecho
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void extraerTablaAuditoriaHecho() {

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
			if (verificaringresoNavegadorDimension(auditoria.getNavegador()) == 0) {
				navegador_dimension navDim = new navegador_dimension();
				navDim.setNavegador(auditoria.getNavegador());
				audHecEJB.ingresarNavegadorDimension(navDim);
				navegador_dimension navIng = audHecEJB.listarNavegadorDimension()
						.get(audHecEJB.listarNavegadorDimension().size() - 1);
				audiHecho.setNavegador(navIng);
			} else {
				audiHecho.setNavegador(audHecEJB.buscarNavegadorDimension(auditoria.getNavegador()));
			}

			if (verificaringresoOrigenDimension(auditoria.getOrigen()) == 0) {
				origen_dimension oriDim = new origen_dimension();
				oriDim.setDispositivo(auditoria.getOrigen());
				audHecEJB.ingresarOrigenDimension(oriDim);
				origen_dimension oriIng = audHecEJB.listarOrigenDimension()
						.get(audHecEJB.listarOrigenDimension().size() - 1);
				audiHecho.setOrigen(oriIng);
			} else {
				audiHecho.setOrigen(audHecEJB.buscarOrigenDimension(auditoria.getOrigen()));
			}

			if (verificarIngresoAuditoria(audiHecho)) {
				audHecEJB.ingresarAuditoriaHecho(audiHecho);
			}

		}
		this.auditorias = audHecEJB.listarAuditoriasHecho();

	}

	/**
	 * 
	 * Metodo encargado de extraer la venta hecho
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void extraerTablaVentaHecho() {
		List<ProductoVenta> productoVentas = proVenEJB.listarProductoVenta();

		for (ProductoVenta productoVenta : productoVentas) {
			venta_hecho venHec = new venta_hecho();
			venHec.setTotaldetalle(productoVenta.getTotal());
			venHec.setCantidad(productoVenta.getCantidad());
			venHec.setFechaventa(productoVenta.getFecha());

			if (verificaringresoVentaDimension(productoVenta.getVenta_codigo().getCodigo()) == 0) {
				venta_dimension venDim = new venta_dimension();
				venDim.setCodigo(productoVenta.getVenta_codigo().getCodigo());
				venDim.setFecha(productoVenta.getVenta_codigo().getFecha());
				venDim.setTotal(productoVenta.getVenta_codigo().getTotalVenta());
				venDim.setNombrevendedor(productoVenta.getVenta_codigo().getVendedor().getNombre());
				venDim.setNombrecliente(productoVenta.getVenta_codigo().getCliente().getNombre());

				venHecEJB.ingresarventaDimension(venDim);
				venta_dimension venDimIng = venHecEJB.listarVentas().get(venHecEJB.listarVentas().size() - 1);
				venHec.setVenta(venDimIng);
			} else {
				venHec.setVenta(venHecEJB.buscarVentaDimension(productoVenta.getVenta_codigo().getCodigo()));
			}

			if (verificaringresoProductoDimension(productoVenta.getProducto_codigo().getCodigo()) == 0) {
				producto_dimension proDim = new producto_dimension();
				proDim.setCodigo(productoVenta.getProducto_codigo().getCodigo());
				proDim.setNombre(productoVenta.getProducto_codigo().getNombre());
				proDim.setNombre(productoVenta.getProducto_codigo().getNombre());
				proDim.setPrecio(productoVenta.getProducto_codigo().getValor());
				proDim.setFechaingreso(productoVenta.getProducto_codigo().getFechaIngreso());
				proDim.setCantidad(productoVenta.getProducto_codigo().getCantidad());

				venHecEJB.ingresarProductoDimension(proDim);
				producto_dimension proDimIng = venHecEJB.listarProductos().get(venHecEJB.listarProductos().size() - 1);
				venHec.setProducto(proDimIng);
			} else {
				venHec.setProducto(venHecEJB.buscarProductoDimension(productoVenta.getProducto_codigo().getCodigo()));
			}

			if (verificaringresoEmpleadoDimension(productoVenta.getVenta_codigo().getVendedor().getNombre()) == 0) {
				empleado_dimension empDim = new empleado_dimension();
				empDim.setNombre(productoVenta.getVenta_codigo().getVendedor().getNombre());
				empDim.setGenero(productoVenta.getVenta_codigo().getVendedor().getGenero());
				empDim.setNombrecargo(productoVenta.getVenta_codigo().getVendedor().getCargo().getCargo());
				venHecEJB.ingresarempleadoDimension(empDim);
				empleado_dimension empDimIng = venHecEJB.listarEmpleados().get(venHecEJB.listarEmpleados().size() - 1);
				venHec.setEmpleado(empDimIng);
			} else {
				venHec.setEmpleado(
						venHecEJB.buscarEmpleadoDimension(productoVenta.getVenta_codigo().getVendedor().getNombre()));
			}

			if (verificaringresoClienteDimension(productoVenta.getVenta_codigo().getCliente().getNombre()) == 0) {
				cliente_dimension cliDim = new cliente_dimension();
				cliDim.setNombre(productoVenta.getVenta_codigo().getCliente().getNombre());
				cliDim.setGenero(productoVenta.getVenta_codigo().getCliente().getGenero());
				venHecEJB.ingresarclienteDimension(cliDim);
				cliente_dimension cliDimIng = venHecEJB.listarClientes().get(venHecEJB.listarClientes().size() - 1);
				venHec.setCliente(cliDimIng);
			} else {
				venHec.setCliente(
						venHecEJB.buscarClienteDimension(productoVenta.getVenta_codigo().getCliente().getNombre()));
			}

			if (verificarIngresoVenta(venHec)) {
				venHecEJB.ingresarVentaHecho(venHec);
			}

		}
		this.ventas = venHecEJB.listarVentasHecho();

	}

	/**
	 * 
	 * Metodo encargado de verificar el ingreso de la venta
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public boolean verificarIngresoVenta(venta_hecho venHe) {
		List<venta_hecho> ven = venHecEJB.listarVentasHecho();
		for (int i = 0; i < ven.size(); i++) {
			if (ven.get(i).getProducto().equals(venHe.getProducto())
					&& ven.get(i).getVenta().equals(venHe.getVenta())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * Metodo encargado de verificar el ingreso de la auditoria *
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public boolean verificarIngresoAuditoria(auditoria_hecho audHe) {
		List<auditoria_hecho> ven = audHecEJB.listarAuditoriasHecho();
		for (int i = 0; i < ven.size(); i++) {
			if (ven.get(i).getCodigo() == audHe.getCodigo()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * Metodo encargado de verificar el ingreso del navegador dimension
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public int verificaringresoNavegadorDimension(String navegador) {
		List<navegador_dimension> navDimension = audHecEJB.listarNavegadorDimension();
		int co = 0;
		for (int i = 0; i < navDimension.size(); i++) {
			if (navDimension.get(i).getNavegador().equalsIgnoreCase(navegador)) {
				co++;
			}
		}
		return co;
	}

	/**
	 * 
	 * Metodo encargado de verificar el origen dimension
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public int verificaringresoOrigenDimension(String dispositivo) {
		List<origen_dimension> navDimension = audHecEJB.listarOrigenDimension();
		int co = 0;
		for (int i = 0; i < navDimension.size(); i++) {
			if (navDimension.get(i).getDispositivo().equalsIgnoreCase(dispositivo)) {
				co++;
			}
		}
		return co;
	}

	/**
	 * 
	 * Metodo encargado de verificar el ingreso del empleado dimension
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public int verificaringresoEmpleadoDimension(String nombre) {
		List<empleado_dimension> empDimension = venHecEJB.listarEmpleados();
		int co = 0;
		for (int i = 0; i < empDimension.size(); i++) {
			if (empDimension.get(i).getNombre().equalsIgnoreCase(nombre)) {
				co++;
			}
		}
		return co;
	}

	/**
	 * 
	 * Metodo encargado de verificar el ingreso del cliente dimension
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public int verificaringresoClienteDimension(String nombre) {
		List<cliente_dimension> cliDimension = venHecEJB.listarClientes();
		int co = 0;
		for (int i = 0; i < cliDimension.size(); i++) {
			if (cliDimension.get(i).getNombre().equalsIgnoreCase(nombre)) {
				co++;
			}
		}
		return co;
	}

	/**
	 * 
	 * Metodo encargado de verificar el ingreso del producto dimension
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public int verificaringresoProductoDimension(int proDim) {
		List<producto_dimension> proDimensiones = venHecEJB.listarProductos();
		int co = 0;
		for (int i = 0; i < proDimensiones.size(); i++) {
			if (proDimensiones.get(i).getCodigo() == proDim) {
				co++;
			}
		}
		return co;
	}

	/**
	 * 
	 * Metodo encargado de verificar el ingreso de la venta dimension
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public int verificaringresoVentaDimension(int venDim) {
		List<venta_dimension> venDimensiones = venHecEJB.listarVentas();
		int co = 0;
		for (venta_dimension ven_Dime : venDimensiones) {
			if (ven_Dime.getCodigo() == venDim) {
				co++;
			}
		}
		return co;
	}

	/**
	 * 
	 * Metodo encargado de conectar a la base de datos
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void conectar() {
		Connection con = null;
		try {
			InitialContext ic = new InitialContext();
			// en esta parte es donde // ponemos el Nombre // // de
			// JNDI para que traiga el datasource
			DataSource ds = (DataSource) ic.lookup("java:jboss/datasources/ETLDS");
			con = ds.getConnection();
			Statement st = con.createStatement();
			Messages.addFlashGlobalInfo("Se ha realizado con exito la conexión a Postgres");
			// el // encargado de traer los datos de la consulta
			ResultSet rs = st.executeQuery("select * from cliente_dimension");
			while (rs.next()) {
				logger.info(" " + rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ETLExtraccionController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NamingException ex) {
			Logger.getLogger(ETLExtraccionController.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				con.close();
				logger.info("Conexion Cerrada con Exito...");
			} catch (SQLException ex) {
				Logger.getLogger(ETLExtraccionController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * 
	 * Metodo encargado de registrar las auditorias
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("ETL");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
