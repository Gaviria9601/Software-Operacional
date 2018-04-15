package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Venta;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB de venta
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@LocalBean
@Stateless
public class VentaEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * Metodo encargado de crear las ventas
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void crearVenta(Venta venta) {
		em.persist(venta);
	}

	/**
	 * 
	 * Metodo encargado de buscar las ventas
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public Venta buscarVenta(int codigo) {
		return em.find(Venta.class, codigo);
	}

	/**
	 * 
	 * Metodo encargado de editar las ventas
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void editarVenta(Venta venta) {
		em.merge(venta);
	}

	/**
	 * 
	 * Metodo encargado de eliminar las ventas
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void eliminarVenta(Integer codigo) {
		em.remove(em.find(Venta.class, codigo));
	}

	/**
	 * 
	 * Metodo encargado de listar las ventas
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Venta> listarVentas() {
		return em.createNamedQuery(Venta.LISTAR_VENTAS).getResultList();
	}

}
