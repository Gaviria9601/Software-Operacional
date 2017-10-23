package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Producto;

@LocalBean
@Stateless
public class InventarioEJB {
	
	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	
	/**
	 * 
	 * @param pro
	 */
	public void crearProducto(Producto pro){
		em.persist(pro);
	}
	
	
}
