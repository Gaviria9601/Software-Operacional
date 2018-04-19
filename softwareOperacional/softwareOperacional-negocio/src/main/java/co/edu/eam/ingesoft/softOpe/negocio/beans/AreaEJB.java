package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Area;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB del area de.
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@Stateless
@LocalBean
public class AreaEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;
	
	

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * 
	 * Metodo encargado de crear una nueva area
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Area a) {
		em.persist(a);
	}

	/**
	 * 
	 * Metodo encargado de buscar el area por nombre
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Area buscarArea(String nombre) {
		Area pa = em.find(Area.class, nombre);
		return pa;
	}

	/**
	 * 
	 * Metodo encargado de buscar el area por identificador
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */

	public Area buscarArea(int id) {
		return em.find(Area.class, id);
	}

	/**
	 * 
	 * Metodo encargado de editar el area
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarArea(Area ar) {
		em.merge(ar);
	}

	/**
	 * 
	 * Metodo encargado de eliminar el area
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id) {
		em.remove(em.find(Area.class, id));
	}

	/**
	 * 
	 * Metodo encargado de listar todas las areas de la base de datos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Area> listarArea() {
		List<Area> lista;
		lista = em.createNamedQuery(Area.LISTAR_AREA).getResultList();
		return lista;
	}
}
