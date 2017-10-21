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

	public void crear(Area a) {
		em.persist(a);

	}

	/**
	 * public void crearArea(Area a) {
	 * 
	 * Area p =buscarArea(a.getNombre()); if(p==null){ em.persist(a); }else {
	 * throw new ExcepcionNegocio("El area ya se encuentra registrada"); } }
	 **/

	/**
	 * 
	 * @param nombre
	 * @return
	 */

	public Area buscarArea(int id) {
		Area pa = em.find(Area.class, id);
		return pa;
	}

	/**
	 * 
	 * @param a
	 */

	/**
	 * @TransactionAttribute(TransactionAttributeType.REQUIRED) public void
	 *                                                          modificarArea(Area
	 *                                                          a){ Area p
	 *                                                          =buscarArea(a.getNombre());
	 *                                                          if(p!=null){
	 *                                                          em.merge(a);
	 *                                                          }else { throw
	 *                                                          new
	 *                                                          ExcepcionNegocio("Ya
	 *                                                          esta esta cedula
	 *                                                          de usuario
	 *                                                          registrado"); }
	 * 
	 *                                                          }
	 **/

	/**
	 * 
	 * @param pa
	 */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarArea(Area pa) {
		em.remove(buscarArea(pa.getId()));
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Area> listarArea() {
		List<Area> lista;
		lista = em.createNamedQuery(Area.LISTAR_AREA).getResultList();
		return lista;
	}
}
