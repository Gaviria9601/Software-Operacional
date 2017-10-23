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


import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Area;

@Stateless
@LocalBean
public class AreaEJB {

	@PersistenceContext(unitName = Conexion.OPCION )
	private EntityManager em;

	/**
	 * 
	 * @param a
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(Area a) {
		
			em.persist(a);
		
	}

	

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Area buscarArea(String nombre) {
		Area pa = em.find(Area.class, nombre);
		return pa;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Area buscarA(String nombre){
		return em.find(Area.class, nombre);
	}
	/**
	 * 
	 * @param audi
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarArea(Area audi){
		em.merge(audi);
	}
 /**
  * 
  * @param codigo
  */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarArea(String nombre){
		em.remove(nombre);
	}
	

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Area> listarArea() {
		List<Area> lista;
		lista = em.createNamedQuery(Area.LISTAR_AREA).getResultList();
		return lista;
	}
}
