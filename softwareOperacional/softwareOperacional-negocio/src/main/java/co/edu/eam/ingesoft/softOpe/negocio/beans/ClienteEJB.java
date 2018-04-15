package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB del cliente
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@LocalBean
@Stateless
public class ClienteEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * Metodo encargado de  buscar el cliente por el codigo
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public Cliente buscarCliente(int codigo) {
		return em.find(Cliente.class, codigo);
	}
	/**
	 * 
	 * Metodo encargado de buscar el municipio
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public Municipio buscarMunicipio(int id) {
		return em.find(Municipio.class, id);
	}

	/**
	 * 
	 * Metodo encargado de crear el cliente
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCliente(Cliente c) {
		em.persist(c);

	}
	/**
	 * 
	 * Metodo encargado de buscar el cliente por el codigo
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cliente buscar(int codigo) {
		Cliente pa = em.find(Cliente.class, codigo);
		return pa;
	}

	/**
	 * 
	 * Metodo encargado de editar el cliente
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(Cliente clie) {
		em.merge(clie);
	}
	/**
	 * 
	 * Metodo encargado de eliminar el cliente por su codigo
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Integer codigo) {
		em.remove(em.find(Cliente.class, codigo));
	}

	/**
	 * 
	 * Metodo encargado de listar los clientes
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Cliente> listarClientes() {
		return em.createNamedQuery(Cliente.LISTAR_CLIENTES).getResultList();

	}
	/**
	 * 
	 * Metodo encargado de listar los clientes
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Cliente> listarCli() {
		List<Cliente> lista;
		lista = em.createNamedQuery(Cliente.LISTAR_CLIENTES).getResultList();
		return lista;
	}
	/**
	 * 
	 * Metodo encargado de listar los municipios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Municipio> listarMuni() {
		return em.createNamedQuery(Municipio.LISTAR_MUNI).getResultList();

	}
}
