package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Venta;

@LocalBean
@Stateless
public class VentaEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * @param venta
	 */
	public void crearVenta(Venta venta) {
		em.persist(venta);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Venta buscarVenta(int codigo) {
		return em.find(Venta.class, codigo);
	}

	/**
	 * 
	 * @param venta
	 */
	public void editarVenta(Venta venta) {
		em.merge(venta);
	}

	/**
	 * 
	 * @param codigo
	 */
	public void eliminarVenta(Integer codigo) {
		em.remove(em.find(Venta.class, codigo));
	}

	/**
	 * 
	 * @return
	 */
	public List<Venta> listarVentas() {
		return em.createNamedQuery(Venta.LISTAR_VENTAS).getResultList();
	}

}
