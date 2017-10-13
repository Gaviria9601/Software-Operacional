package co.edu.eam.ingesoft.softOpe.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Area;

@Stateless
@LocalBean
public class AreaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 
	 * @param a
	 */
	
	
	
	public void crear(Area a) {
	     em.persist(a);
		
	}
	
	
	public void crearArea(Area a) {
		
	Area p =buscarArea(a.getNombre());
	if(p==null){
			em.persist(a);
	}else {
		throw new ExcepcionNegocio("El area ya se encuentra registrada");
	}
	}
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */

	public Area buscarArea(String nombre) {
		Area pa = em.find(Area.class, nombre);
		return pa;
	}
	
	/**
	 * 
	 * @param a
	 */
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarArea(Area a){
		Area p =buscarArea(a.getNombre());
		if(p!=null){
				em.merge(a);
		}else {
			throw new ExcepcionNegocio("Ya esta esta cedula de usuario registrado");
		}
		
	}
	
	/**
	 * 
	 * @param pa
	 */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarArea(Area pa){
		em.remove(buscarArea(pa.getNombre()));
	}
}
