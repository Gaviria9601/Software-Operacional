package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Cargo;
import co.edu.eam.ingesoft.softOper.entidades.Departamento;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB para los empleados
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@LocalBean
@Stateless
public class EmpleadoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * Metodo encargado de buscar los empleados
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Empleado buscarEmpleado(int usuario) {
		List<Empleado> empleado = em.createNamedQuery(Empleado.BUSQUEDA_POR_USUARIO).setParameter(1, usuario)
				.getResultList();
		if (!empleado.isEmpty()) {
			return empleado.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * Metodo encargado de crear el empleados
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Empleado e, String nickname) {
		List<Usuario> usuario = em.createNamedQuery(Usuario.LISTA_BUSQUEDA_USUARIO).setParameter(1, nickname)
				.getResultList();
		if (usuario.get(0) != null) {
			e.setUsuario(usuario.get(0));
			em.persist(e);
		}
	}

	/**
	 * 
	 * Metodo encargado de crear empleados con usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearEmpleadosinUsuario(Empleado e) {
		em.persist(e);
	}

	/**
	 * 
	 * Metodo encargado de editar empleados
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarEmpleado(Empleado e) {
		em.merge(e);
	}

	/**
	 * 
	 * Metodo encargado de editar los usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	/**
	 * 
	 * Metodo encargado de editar los usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarUsuario(Usuario t) {
		em.merge(t);
	}

	/**
	 * 
	 * Metodo encargado de crear los usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearUsuario(Usuario usu) {
		em.persist(usu);
	}

	/**
	 * 
	 * Metodo encargado de buscar los empleados por identificador
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Empleado buscarEmpleado2(int id) {
		List<Empleado> emp = em.createNamedQuery(Empleado.BUSQUEDA_USUARIO).setParameter(1, id).getResultList();
		Empleado pa = emp.get(0);
		return pa;
	}

	/**
	 * 
	 * Metodo encargado de buscar los usuarios por identificador
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuario(int id) {
		Usuario pa = em.find(Usuario.class, id);
		return pa;
	}

	/**
	 * 
	 * Metodo encargado de buscar el usuario por nombre
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuarioNombre(String nombre) {
		List<Usuario> usuarios = em.createNamedQuery(Usuario.LISTA_BUSQUEDA_USUARIO).setParameter(1, nombre)
				.getResultList();
		return (Usuario) usuarios.get(0);
	}

	/**
	 * 
	 * Metodo encargado de eliminar los empleados
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarEmpleado(Empleado pa) {
		em.remove(buscarEmpleado2(pa.getCodigo()));
	}

	/**
	 * 
	 * Metodo encargado de eliminar los usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarUsuario(Usuario a) {
		em.remove(buscarUsuario(a.getId()));
	}

	/**
	 * 
	 * Metodo encargado de listar los departamento
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Departamento> listardepartamentos() {
		return em.createNamedQuery(Departamento.LISTAR_DEPTOS).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los municipios por departamento
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Municipio> listarMuniporDepto(String depto) {
		return em.createNamedQuery(Municipio.LISTAR_MUNI_POR_DEPTO).setParameter(1, depto).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los cargos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Cargo> listarCargos() {
		return em.createNamedQuery(Cargo.LISTAR_CARGOS).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar los empleados
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<Empleado> listarEmpleados() {
		return em.createNamedQuery(Empleado.LISTAR_EMPLEADOS).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de buscar los municipios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Municipio buscarMunicipio(int id) {
		return em.find(Municipio.class, id);
	}

	/**
	 * 
	 * Metodo encargado de buscar los cargos
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cargo buscarCargo(int id) {
		return em.find(Cargo.class, id);
	}

}
