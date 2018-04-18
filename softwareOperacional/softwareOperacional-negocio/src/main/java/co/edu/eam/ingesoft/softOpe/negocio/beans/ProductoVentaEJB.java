package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.ProductoVenta;
import co.edu.eam.ingesoft.softOper.entidades.ProductoVentaPK;
import co.edu.eam.ingesoft.softOper.entidades.Venta;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB de las ventas de las
 * productos
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@LocalBean
@Stateless
public class ProductoVentaEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	@EJB
	private AuditoriaEJB audiEJB;

	/**
	 * 
	 * Metodo encargado de agregar productos a la venta
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void agregarProductoVenta(Producto producto, Venta venta, int cantidad) {
		Producto pro = em.find(Producto.class, producto.getCodigo());
		Venta ve = em.find(Venta.class, venta.getCodigo());
		if (pro.getCantidad() >= cantidad) {
			ProductoVenta proVen = new ProductoVenta();
			proVen.setVenta_codigo(ve);
			proVen.setProducto_codigo(pro);
			proVen.setCantidad(cantidad);
			proVen.setFecha(audiEJB.generarFechaActual());
			int total = pro.getValor() * cantidad;
			proVen.setTotal(total);
			pro.setCantidad(pro.getCantidad() - cantidad);
			em.merge(pro);
			em.persist(proVen);
		} else {
			throw new ExcepcionNegocio("No Existe la cantidad de inventario del Producto a agregar");
		}
	}

	/**
	 * 
	 * Metodo encargado de eliminar los productos de la video
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public int eliminarProductoVenta(int producto, int venta) {
		int totalElim;
		ProductoVentaPK proVenPK = new ProductoVentaPK(producto, venta);
		ProductoVenta proVen = em.find(ProductoVenta.class, proVenPK);
		Producto pro = em.find(Producto.class, producto);
		pro.setCantidad(pro.getCantidad() + proVen.getCantidad());
		em.remove(proVen);
		totalElim = proVen.getTotal();
		return totalElim;
	}

	/**
	 * 
	 * Metodo encargado de listar los productos de la venta
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public List<ProductoVenta> listarProductosVenta(int codigo) {
		return em.createNamedQuery(ProductoVenta.LISTAR_PRODUCTOS_VENTA).setParameter(1, codigo).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los productos de la venta
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public List<ProductoVenta> listarProductoVenta() {
		return em.createNamedQuery(ProductoVenta.LISTAR_PRODUCTOS_VENTA_FULL).getResultList();
	}

}
