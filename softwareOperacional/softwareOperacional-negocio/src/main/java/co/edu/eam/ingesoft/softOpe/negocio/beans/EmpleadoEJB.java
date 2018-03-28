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

@LocalBean
@Stateless
public class EmpleadoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Empleado buscarEmpleado(int usuario) {
		List<Empleado> empleado = em.createNamedQuery(Empleado.BUSQUEDA__POR_USUARIO).setParameter(1, usuario)
				.getResultList();
		if (!empleado.isEmpty()) {
			return empleado.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param e
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Empleado e, String nickname) {
		List<Usuario> usuario = em.createNamedQuery(Usuario.LISTA_BUSQUEDA_USUARIO).setParameter(1, nickname)
				.getResultList();
		if (usuario.get(0) != null) {
			e.setUsuario(usuario.get(0));
			System.out.println(usuario.get(0).getNombre() + "************************************");
			em.persist(e);
		} else {
			System.out.println("error");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearEmpleadosinUsuario(Empleado e) {
		em.persist(e);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarEmpleado(Empleado e) {
		em.merge(e);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarUsuario(Usuario t) {
		em.merge(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearUsuario(Usuario usu) {
		em.persist(usu);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Empleado buscarEmpleado2(int id) {
		System.out.println(id + "*****************************");
		List<Empleado> emp = em.createNamedQuery(Empleado.BUSQUEDA_USUARIO).setParameter(1, id).getResultList();
		Empleado pa = emp.get(0);
		System.out.println(pa.getApellido() + "************" + pa.getNombre());
		return pa;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuario(int id) {
		Usuario pa = em.find(Usuario.class, id);
		return pa;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuarioNombre(String nombre) {
		List<Usuario> usuarios = em.createNamedQuery(Usuario.LISTA_BUSQUEDA_USUARIO).setParameter(1, nombre)
				.getResultList();
		return (Usuario) usuarios.get(0);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarEmpleado(Empleado pa) {
		em.remove(buscarEmpleado2(pa.getCodigo()));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarUsuario(Usuario a) {
		em.remove(buscarUsuario(a.getId()));
	}

	/**
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Departamento> listardepartamentos() {
		return em.createNamedQuery(Departamento.LISTAR_DEPTOS).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Municipio> listarMuniporDepto(String depto) {
		return em.createNamedQuery(Municipio.LISTAR_MUNIPorDepto).setParameter(1, depto).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Cargo> listarCargos() {
		return em.createNamedQuery(Cargo.LISTAR_CARGOS).getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<Empleado> listarEmpleados() {
		return em.createNamedQuery(Empleado.LISTAR_EMPLEADOS).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Municipio buscarMunicipio(int id) {
		return em.find(Municipio.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cargo buscarCargo(int id) {
		return em.find(Cargo.class, id);
	}

}
