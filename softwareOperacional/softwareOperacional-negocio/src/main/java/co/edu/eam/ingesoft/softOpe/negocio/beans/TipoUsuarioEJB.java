package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB del tipo de usuarios
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@Stateless
@LocalBean
public class TipoUsuarioEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * 
	 * Metodo encargado de crear los tipo de usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(TipoUsuario t) {
		em.persist(t);

	}

	/**
	 * 
	 * Metodo encargado de editr los tipos de usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(TipoUsuario t) {
		em.merge(t);
	}

	/**
	 * 
	 * Metodo encargado de buscar los tipos de usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public TipoUsuario buscarTipoUsuario(int id) {
		TipoUsuario pa = em.find(TipoUsuario.class, id);
		return pa;
	}

	/**
	 * 
	 * Metodo encargado de eliminar los tipos de usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarTipoUsuario(Integer codigo) {
		em.remove(em.find(TipoUsuario.class, codigo));
	}

	/**
	 * 
	 * Metodo encargado de listar los tipos de usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoUsuario> listarTipoUsuario() {
		List<TipoUsuario> lista;
		lista = em.createNamedQuery(TipoUsuario.LISTAR_TIPOS).getResultList();
		return lista;
	}

}
