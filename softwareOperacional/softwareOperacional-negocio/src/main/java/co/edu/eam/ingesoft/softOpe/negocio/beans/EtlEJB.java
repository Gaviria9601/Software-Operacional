package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

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

	/**
	 * 
	 * @param origen
	 */
	public void insertarOrigen(origen_dimension origen) {
		String consulta = "insert into origen_dimension (codigo,dispositivo)" + "values('" + origen.getCodigo() + "','"
				+ origen.getDispositivo() + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param navegador
	 */
	public void insertarNavegador(navegador_dimension navegador) {
		String consulta = "insert into navegador_dimension(codigo,navegador)" + "values('" + navegador.getCodigo()
				+ "','" + navegador.getNavegador() + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param pro
	 */

	public void insertarProducto(producto_dimension pro) {
		String consulta = "insert into producto_dimension(codigo,nombre,precio,fechaingreso,cantidad)" + "values('"
				+ pro.getCodigo() + "','" + pro.getNombre() + "','" + pro.getPrecio() + "','" + pro.getFechaingreso()
				+ "','" + pro.getCantidad() + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param ven
	 */
	public void insertarVentaD(venta_dimension ven) {
		String consulta = "insert into venta_dimension(codigo,fecha,total,nombrevendedor,nombrecliente)" + "values('"
				+ ven.getCodigo() + "','" + ven.getFecha() + "','" + ven.getTotal() + "','" + ven.getNombrevendedor()
				+ "','" + ven.getNombrecliente() + "')";
		System.out.println(consulta);
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param cli
	 */
	public void insertarCliente(cliente_dimension cli) {
		String consulta = "insert into cliente_dimension(codigo,nombre,genero)" + "values('" + cli.getCodigo() + "','"
				+ cli.getNombre() + "','" + cli.getGenero() + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param emp
	 */
	public void insertarEmpleado(empleado_dimension emp) {
		String consulta = "insert into empleado_dimension(codigo,nombre,genero,nombrecargo)" + "values('"
				+ emp.getCodigo() + "','" + emp.getNombre() + "','" + emp.getGenero() + "','" + emp.getNombrecargo()
				+ "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param audi
	 */

	public void insertarAuditoria(auditoria_hecho audi) {
		String consulta = "insert into auditoria_hecho(codigo,accion,fechaauditoria,tablaaccion,navegador,origen,tiempo)"
				+ "values('" + audi.getCodigo() + "','" + audi.getAccion() + "','" + audi.getFechaauditoria() + "','"
				+ audi.getTablaaccion() + audi.getNavegador() + "','" + audi.getTiempo() + "')";
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param ve
	 */

	public void insertarVentaH(venta_hecho ve) {
		String consulta = "insert into venta_hecho(codigo,totaldetalle,cantidad,fechaventa,venta,tiempo,producto,cliente,empleado) "
				+ "values('" + ve.getCodigo() + "','" + ve.getTotaldetalle() + "','" + ve.getCantidad() + "','"
				+ ve.getFechaventa() + "'," + ve.getVenta().getCodigo() + "," + ve.getTiempo().getCodigo() + ","
				+ ve.getProducto().getCodigo() + "," + ve.getCliente().getCodigo() + "," + ve.getEmpleado().getCodigo()
				+ ")";
		System.out.println(consulta);
		super.ejecutar(consulta);
	}

	/**
	 * 
	 * @param tiem
	 */
	public void insertarTiempo(tiempo_dimension tiem) {
		String consulta = "insert into tiempo_dimension(codigo,trimestre,mes)" + "values('" + tiem.getCodigo() + "','"
				+ tiem.getTrimestre() + "','" + tiem.getMes() + "')";
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
