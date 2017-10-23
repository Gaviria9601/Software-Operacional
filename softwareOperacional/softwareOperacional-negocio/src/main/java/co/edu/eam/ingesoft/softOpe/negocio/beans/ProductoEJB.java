package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public Producto buscarProduto(int codigo) {
		return em.find(Producto.class, codigo);
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public TipoProducto buscarTipoProducto(int codigo){
		return em.find(TipoProducto.class, codigo);
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
	 * @param productosVenta
	 * @return
	 */
	public List<Producto> listarProductosDif(List<Producto> productosVenta) {
		List<Producto> productos = new ArrayList<Producto>();
		List<Producto> productosIn = listarProductos();
		for (int i = 0; i < productosIn.size(); i++) {
			for (int j = 0; j < productosVenta.size(); j++) {
				if (productosIn.get(i).getCodigo()!=productosVenta.get(j).getCodigo()) {
					productos.add(productosIn.get(i));
				}
			}

		}
		return productos;
	}

}
