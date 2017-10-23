package co.edu.eam.ingesoft.softOpe.negocio.beans;

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

@LocalBean
@Stateless
public class ProductoVentaEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	@EJB
	private AuditoriaEJB audiEJB;

	/**
	 * 
	 * @param proVen
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
	 * @param producto
	 * @param venta
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

}
