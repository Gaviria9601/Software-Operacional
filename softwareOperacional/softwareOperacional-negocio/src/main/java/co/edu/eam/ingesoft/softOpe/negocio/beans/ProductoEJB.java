package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.ProductoVenta;
import co.edu.eam.ingesoft.softOper.entidades.TipoProducto;

@LocalBean
@Stateless
public class ProductoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/*
	 * 
	 * @param codigo
	 * 
	 * @return
	 */

	public void crearProducto(Producto pro) {
		em.persist(pro);
	}

	/**
	 * 
	 * @param pro
	 */
	public void editarProducto(Producto pro) {
		em.merge(pro);
	}

	public Producto buscarProduto(int codigo) {
		return em.find(Producto.class, codigo);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public TipoProducto buscarTipoProducto(int codigo) {
		return em.find(TipoProducto.class, codigo);
	}

	/**
	 * 
	 * @param codigo
	 */
	public void eliminarProducto(int codigo) {
		em.remove(em.find(Producto.class, codigo));
	}

	/**
	 * 
	 * @return
	 */
	public List<Producto> listarProductos() {
		return em.createNamedQuery(Producto.LISTAR_PRODUCTOS).getResultList();

	}

	/**
	 * 
	 * @return
	 */
	public List<TipoProducto> listarTipoProducto() {
		return em.createNamedQuery(TipoProducto.LISTAR_TIPO_PRODUCTOS).getResultList();
	}

	/**
	 * 
	 * @param productosVenta
	 * @return
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
