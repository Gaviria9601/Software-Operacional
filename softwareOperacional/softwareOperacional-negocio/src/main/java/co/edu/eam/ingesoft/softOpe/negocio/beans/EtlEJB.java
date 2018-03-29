package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.auditoria_hecho;
import co.edu.eam.ingesoft.softOper.entidades.cliente_dimension;
import co.edu.eam.ingesoft.softOper.entidades.empleado_dimension;
import co.edu.eam.ingesoft.softOper.entidades.navegador_dimension;
import co.edu.eam.ingesoft.softOper.entidades.origen_dimension;
import co.edu.eam.ingesoft.softOper.entidades.producto_dimension;
import co.edu.eam.ingesoft.softOper.entidades.tiempo_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

@LocalBean
@Stateless
public class EtlEJB extends ConexionETL {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	@EJB
	private MediaWikiEJB mediWikiEJB;

	@EJB
	private Auditoria_hechoEJB audiEJB;

	public void procesoCarga(List<tiempo_dimension> ti, List<cliente_dimension> cli, List<navegador_dimension> na,
			List<producto_dimension> po, List<venta_dimension> ve, List<empleado_dimension> em,
			List<origen_dimension> ori, List<auditoria_hecho> audi, List<venta_hecho> vh) {

		// TIEMPOS
		for (tiempo_dimension tiem : ti) {
			insertaTiempo(tiem.getCodigo(), tiem.getTrimestre(), tiem.getMes());
		}

		// CLIENTE
		for (cliente_dimension c : cli) {
			insertaCliente(c.getCodigo(), c.getNombre(), c.getGenero());
		}

		// NAVEGADOR
		for (navegador_dimension d : na) {
			insertaNavegador(d.getCodigo(), d.getNavegador());
		}

		// PRODUCTO
		for (producto_dimension p : po) {
			insertaProducto(p.getCodigo(), p.getNombre(), p.getPrecio(), p.getFechaingreso(), p.getCantidad());
		}

		// VENTA DIMENSION
		for (venta_dimension v : ve) {
			insertaVentaD(v.getCodigo(), v.getFecha(), v.getTotal(), v.getNombrevendedor(), v.getNombrecliente());
		}

		// EMPLEADO
		for (empleado_dimension e : em) {
			insertaEmpleado(e.getCodigo(), e.getNombre(), e.getGenero(), e.getNombrecargo());
		}

		// ORIGEN
		for (origen_dimension og : ori) {
			insertarOrigen(og.getCodigo(), og.getDispositivo());
		}

		// AUDITORIA
		for (auditoria_hecho a : audi) {
			insertaAditoria(a.getCodigo(), a.getFechaauditoria(), a.getTablaaccion(), a.getAccion(), a.getNavegador(),
					a.getOrigen(), a.getTiempo());
		}

		// VENTA HECHO
		for (venta_hecho g : vh) {
			insertaVentaH(g.getCodigo(), g.getTotaldetalle(), g.getCantidad(), g.getFechaventa(),
					g.getVenta().getCodigo(), g.getTiempo(), g.getProducto().getCodigo(), g.getCliente().getCodigo(),
					g.getEmpleado().getCodigo());

		}
	}

	/**
	 * Metodo encargado de eliminar los registro del Datawehouse
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	public void eliminarRegistros() {

		String sentenciaAudi = "delete from auditoria_hecho";
		super.ejecutar(sentenciaAudi);

		String sentenciaVenHech = "delete from venta_hecho";
		super.ejecutar(sentenciaVenHech);

		String sentenciaClien = "delete from cliente_dimension";
		super.ejecutar(sentenciaClien);

		String sentenciaEmpl = "delete from empleado_dimension";
		super.ejecutar(sentenciaEmpl);

		String sentenciaNave = "delete from navegador_dimension";
		super.ejecutar(sentenciaNave);

		String sentenciaOri = "delete from origen_dimension";
		super.ejecutar(sentenciaOri);

		String sentenciaTiem = "delete from tiempo_dimension";
		super.ejecutar(sentenciaTiem);

		String sentenciaVenDim = "delete from venta_dimension";
		super.ejecutar(sentenciaVenDim);

		String sentenciaPro = "delete from producto_dimension";
		super.ejecutar(sentenciaPro);

	}

	/**
	 * 
	 * @param codigo
	 * @param navegador
	 */
	public void insertarOrigen(int codigo, String dispositivo) {
		String consulta = "insert into origen_dimension(codigo,dispositivo)" + "values(" + codigo + ",'" + dispositivo
				+ "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param codigo
	 * @param navegador
	 */
	public void insertaNavegador(int codigo, String navegador) {
		String consulta = "insert into navegador_dimension(codigo,navegador)" + "values(" + codigo + ",'" + navegador
				+ "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param precio
	 * @param fechaingreso
	 * @param cantidad
	 */
	public void insertaProducto(int codigo, String nombre, int precio, Date fechaingreso, int cantidad) {
		String consulta = "insert into producto_dimension(codigo,nombre,precio,fechaingreso,cantidad)" + "values("
				+ codigo + ",'" + nombre + "'," + precio + ",'" + fechaingreso + "'," + cantidad + ")";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param genero
	 */
	public void insertaCliente(int codigo, String nombre, String genero) {
		String consulta = "insert into cliente_dimension(codigo,nombre,genero)" + "values(" + codigo + ",'" + nombre
				+ "','" + genero + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param genero
	 * @param nombrecargo
	 */
	public void insertaEmpleado(int codigo, String nombre, String genero, String nombrecargo) {
		String consulta = "insert into empleado_dimension(codigo,nombre,genero,nombrecargo)" + "values(" + codigo + ",'"
				+ nombre + "','" + genero + "','" + nombrecargo + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param codigo
	 * @param fecha
	 * @param total
	 * @param nombrevendedor
	 * @param nombrecliente
	 */

	public void insertaVentaD(int codigo, Date fecha, int total, String nombrevendedor, String nombrecliente) {
		String consulta = "insert into venta_dimension(codigo,fecha,total,nombrevendedor,nombrecliente)" + "values("
				+ codigo + ",'" + fecha + "'," + total + ",'" + nombrevendedor + "','" + nombrecliente + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param codigo
	 * @param fechaauditoria
	 * @param tablaaccion
	 * @param accion
	 * @param navegador
	 * @param origen
	 * @param tiempo
	 */

	public void insertaAditoria(int codigo, Date fechaauditoria, String tablaaccion, String accion,
			navegador_dimension navegador, origen_dimension origen, tiempo_dimension tiempo) {
		String consulta = "insert into auditoria_hecho(codigo,accion,fechaauditoria,tablaaccion,navegador,origen,tiempo) "
				+ "values(" + codigo + ",'" + accion + "','" + fechaauditoria + "','" + tablaaccion + "',"
				+ navegador.getCodigo() + "," + origen.getCodigo() + "," + (tiempo != null ? tiempo.getCodigo() : null)
				+ ")";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param codigo
	 * @param totaldetalle
	 * @param cantidad
	 * @param fechaventa
	 * @param venta
	 * @param tiempo
	 * @param producto
	 * @param cliente
	 * @param empleado
	 */
	public void insertaVentaH(int codigo, int totalDetalle, int cantidad, Date fecha, int venta,
			tiempo_dimension tiempo, int producto, int cliente, int empleado) {
		String consulta = "insert into venta_hecho(codigo,totaldetalle,cantidad,fechaventa,venta,tiempo,producto,cliente,empleado) "
				+ "values(" + codigo + "," + totalDetalle + "," + cantidad + ",'" + fecha + "'," + venta + ","
				+ (tiempo != null ? tiempo.getCodigo() : null) + "," + producto + "," + cliente + "," + empleado + ")";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public List<auditoria_hecho> listarAuditoriasDataWarehouse() {
		List<auditoria_hecho> lista = new ArrayList<auditoria_hecho>();
		try {
			String consulta = "select codigo,fechaauditoria,tablaaccion,accion,navegador,origen,tiempo from auditoria_hecho";
			ResultSet resultado = super.ejecutarRetorno(consulta);
			while (resultado.next()) {
				lista.add(new auditoria_hecho(resultado.getInt(1), resultado.getDate(2), resultado.getString(3),
						resultado.getString(4), new navegador_dimension(resultado.getInt(5)),
						new origen_dimension(resultado.getInt(6)), new tiempo_dimension(resultado.getInt(7))));
			}
		} catch (SQLException e) {
		}
		return lista;
	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public List<venta_hecho> listarVentaDataWarehouse() {
		List<venta_hecho> lista = new ArrayList<venta_hecho>();
		try {
			String consulta = "select codigo,totaldetalle,cantidad,fechaventa,venta,tiempo,producto,cliente,empleado from venta_hecho";
			ResultSet resultado = super.ejecutarRetorno(consulta);
			while (resultado.next()) {
				lista.add(new venta_hecho(resultado.getInt(1), resultado.getInt(2), resultado.getInt(3),
						resultado.getDate(4), new venta_dimension(resultado.getInt(5)),
						new tiempo_dimension(resultado.getInt(6)), new producto_dimension(resultado.getInt(7)),
						new cliente_dimension(resultado.getInt(8)), new empleado_dimension(resultado.getInt(9))));
			}
		} catch (SQLException e) {
		}
		return lista;
	}

	/**
	 * 
	 * @param codigo
	 * @param trimestre
	 * @param mes
	 */
	public void insertaTiempo(int codigo, String trimestre, String mes) {
		String consulta = "insert into tiempo_dimension(codigo,trimestre,mes)" + "values(" + codigo + "," + trimestre
				+ "," + mes + ")";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @return
	 */

	public List<tiempo_dimension> listarTiempo() {
		List<tiempo_dimension> lista;
		lista = em.createNamedQuery(tiempo_dimension.LISTAR_TIEMPO).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */

	public List<origen_dimension> listarOrigen() {
		List<origen_dimension> lista;
		lista = em.createNamedQuery(origen_dimension.LISTAR_ORIGEN_DIMENSION).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */
	public List<auditoria_hecho> listarAuditoria() {
		List<auditoria_hecho> lista;
		lista = em.createNamedQuery(auditoria_hecho.LISTAR_AUDITORIA_HECHO).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */
	public List<cliente_dimension> listarCliente() {
		List<cliente_dimension> lista;
		lista = em.createNamedQuery(cliente_dimension.LISTAR_CLIENTE_DIMENSION).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */
	public List<empleado_dimension> listarEmpleado() {
		List<empleado_dimension> lista;
		lista = em.createNamedQuery(empleado_dimension.LISTAR_EMPLEADO_DIMENSION).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */
	public List<navegador_dimension> listarNavegador() {
		List<navegador_dimension> lista;
		lista = em.createNamedQuery(navegador_dimension.LISTAR_NAVEGADOR_DIMENSION).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */
	public List<producto_dimension> listarProducto() {
		List<producto_dimension> lista;
		lista = em.createNamedQuery(producto_dimension.LISTAR_PRODUCTO_DIMENSION).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */
	public List<venta_dimension> listarVentaD() {
		List<venta_dimension> lista;
		lista = em.createNamedQuery(venta_dimension.LISTAR_VENTA_DIMENSION).getResultList();
		return lista;
	}

	/**
	 * 
	 * @return
	 */
	public List<venta_hecho> listarVentaH() {
		List<venta_hecho> lista;
		lista = em.createNamedQuery(venta_hecho.LISTAR_VENTA_HECHO).getResultList();
		return lista;
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public tiempo_dimension buscarPTiempoDimension(int codigo) {
		return em.find(tiempo_dimension.class, codigo);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public cliente_dimension buscarClienteDimension(int codigo) {
		return em.find(cliente_dimension.class, codigo);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public empleado_dimension buscarEmpleadoDimension(int codigo) {
		return em.find(empleado_dimension.class, codigo);
	}

}
