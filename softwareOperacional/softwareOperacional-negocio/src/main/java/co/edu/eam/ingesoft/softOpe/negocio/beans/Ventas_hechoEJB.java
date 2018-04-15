package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.cliente_dimension;
import co.edu.eam.ingesoft.softOper.entidades.empleado_dimension;
import co.edu.eam.ingesoft.softOper.entidades.producto_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB la venta de hecho
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@LocalBean
@Stateless
public class Ventas_hechoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * Metodo encargado de ingresar la venta hecho
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void ingresarVentaHecho(venta_hecho venHecho) {
		em.persist(venHecho);
	}

	/**
	 * 
	 * Metodo encargado de ingresar el producto dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void ingresarProductoDimension(producto_dimension proDim) {
		em.persist(proDim);
	}

	/**
	 * 
	 * Metodo encargado de buscar los productos dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public producto_dimension buscarProductoDimension(int codigo) {
		return em.find(producto_dimension.class, codigo);
	}

	/**
	 * 
	 * @param proDim
	 */
	public void ingresarventaDimension(venta_dimension venDim) {
		em.persist(venDim);
	}

	/**
	 * 
	 * Metodo encargado de buscar venta dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public venta_dimension buscarVentaDimension(int codigo) {
		return em.find(venta_dimension.class, codigo);
	}

	/**
	 * 
	 * Metodo encargado de ingresar empleado dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void ingresarempleadoDimension(empleado_dimension empDim) {
		em.persist(empDim);
	}

	/**
	 * 
	 * Metodo encargado de buscar el empleado dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public empleado_dimension buscarEmpleadoDimension(String nombre) {
		List<empleado_dimension> empDimen = em.createNamedQuery(empleado_dimension.BUSCAR_NOMBRE_EMPLEADO)
				.setParameter(1, nombre).getResultList();
		return empDimen.get(0);
	}

	/**
	 * 
	 * Metodo encargado de ingresar el cliente dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void ingresarclienteDimension(cliente_dimension cliDim) {
		em.persist(cliDim);
	}

	/**
	 * 
	 * Metodo encargado de buscar el cliente dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public cliente_dimension buscarClienteDimension(String nombre) {
		List<cliente_dimension> cliDimen = em.createNamedQuery(cliente_dimension.BUSCAR_NOMBRE_CLIENTE)
				.setParameter(1, nombre).getResultList();
		return cliDimen.get(0);
	}

	/**
	 * 
	 * Metodo encargado de listar las ventas de hecho
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<venta_hecho> listarVentasHecho() {
		return em.createNamedQuery(venta_hecho.LISTAR_VENTA_HECHO).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<producto_dimension> listarProductos() {
		return em.createNamedQuery(producto_dimension.LISTAR_PRODUCTO_DIMENSION).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listas las ventas
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<venta_dimension> listarVentas() {
		return em.createNamedQuery(venta_dimension.LISTAR_VENTA_DIMENSION).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los empleados
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<empleado_dimension> listarEmpleados() {
		return em.createNamedQuery(empleado_dimension.LISTAR_EMPLEADO_DIMENSION).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los clientes
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<cliente_dimension> listarClientes() {
		return em.createNamedQuery(cliente_dimension.LISTAR_CLIENTE_DIMENSION).getResultList();
	}

	/**
	 * 
	 * Metodo encargado realizar la tranformación para borras valores menores a 0
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public boolean borrarMenores0() {
		try {
			em.createQuery("delete from producto_dimension p where p.precio<0").executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * 
	 * Metodo encargado realizar la tranformación para borras valores menores a 20
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public boolean borrarMenores20() {
		try {
			em.createQuery("delete from producto_dimension p where p.precio<20000").executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
