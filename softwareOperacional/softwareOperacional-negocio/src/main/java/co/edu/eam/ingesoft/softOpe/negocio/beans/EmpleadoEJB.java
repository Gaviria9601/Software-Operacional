package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Cargo;
import co.edu.eam.ingesoft.softOper.entidades.Departamento;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;
import co.edu.eam.ingesoft.softOper.entidades.Venta;

@LocalBean
@Stateless
public class EmpleadoEJB {

	@PersistenceContext(unitName = Conexion.OPCION )
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
	public void crear(Empleado e) {
	     em.persist(e);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearUsuario(Usuario usu){
		em.persist(usu);
	}
	

	public Empleado buscarEmpleado2(int id) {
		Empleado pa = em.find(Empleado.class, id);
		return pa;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarEmpleado(Empleado pa){
		em.remove(buscarEmpleado2(pa.getCodigo()));
	}

	/**
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Departamento> listardepartamentos(){
		return em.createNamedQuery(Departamento.LISTAR_DEPTOS).getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Municipio> listarMuniporDepto(String depto){
		return em.createNamedQuery(Municipio.LISTAR_MUNIPorDepto).setParameter(1, depto).getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Cargo> listarCargos(){
		return em.createNamedQuery(Cargo.LISTAR_CARGOS).getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Empleado> listarEmpleados(){
		return em.createNamedQuery(Empleado.LISTAR_EMPLEADOS).getResultList();
	}
	
	public Municipio buscarMunicipio(int id){
		return em.find(Municipio.class, id);
	}
	
	public Cargo buscarCargo(int id){
		return em.find(Cargo.class, id);
	}
	
}
