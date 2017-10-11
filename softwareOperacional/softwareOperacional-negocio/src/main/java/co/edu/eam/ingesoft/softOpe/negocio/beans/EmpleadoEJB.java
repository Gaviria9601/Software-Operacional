package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Empleado;

@LocalBean
@Stateless
public class EmpleadoEJB {

	@PersistenceContext
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
	
}
