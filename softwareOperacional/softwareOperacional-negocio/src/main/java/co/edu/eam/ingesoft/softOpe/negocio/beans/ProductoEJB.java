package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.TipoProducto;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB para la auditoria hecho
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@LocalBean
@Stateless
public class ProductoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * Metodo encargado de crear los productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */

	public void crearProducto(Producto pro) {
		em.persist(pro);
	}

	/**
	 * 
	 * Metodo encargado de editar los productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void editarProducto(Producto pro) {
		em.merge(pro);
	}

	/**
	 * 
	 * Metodo encargado de buscar los productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public Producto buscarProduto(int codigo) {
		return em.find(Producto.class, codigo);
	}

	/**
	 * 
	 * Metodo encargado de buscar el tipo de productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public TipoProducto buscarTipoProducto(int codigo) {
		return em.find(TipoProducto.class, codigo);
	}

	/**
	 * 
	 * Metodo encargado de eliminar los productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void eliminarProducto(int codigo) {
		em.remove(em.find(Producto.class, codigo));
	}

	/**
	 * 
	 * Metodo encargado de listar los productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Producto> listarProductos() {
		return em.createNamedQuery(Producto.LISTAR_PRODUCTOS).getResultList();

	}

	/**
	 * 
	 * Metodo encargado de listar los tipo de productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<TipoProducto> listarTipoProducto() {
		return em.createNamedQuery(TipoProducto.LISTAR_TIPO_PRODUCTOS).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los productos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Producto> listarProductosDif(List<Producto> productosVenta) {
		List<Producto> productos = new ArrayList<Producto>();
		List<Producto> productosIn = listarProductos();
		for (int i = 0; i < productosIn.size(); i++) {
			for (int j = 0; j < productosVenta.size(); j++) {
				if (productosIn.get(i).getCodigo() != productosVenta.get(j).getCodigo()) {
					productos.add(productosIn.get(i));
				}
			}

		}
		return productos;
	}

}
