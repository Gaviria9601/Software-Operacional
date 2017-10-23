package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Empleado;

@LocalBean
@Stateless
public class inventarioEJB {
	
	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	
	
}
