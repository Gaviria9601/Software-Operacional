package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;

@Stateless
@LocalBean
public class TipoUsuarioEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 
	 * @param a
	 */
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(TipoUsuario t) {
	     em.persist(t);
		
	}
	

	public TipoUsuario buscarTipoUsuario(int id) {
		TipoUsuario pa = em.find(TipoUsuario.class, id);
		return pa;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarTipoUsuario(TipoUsuario pa){
		em.remove(buscarTipoUsuario(pa.getId()));
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoUsuario> listarTipoUsuario(){
		List<TipoUsuario> lista;
	       lista = em.createNamedQuery(TipoUsuario.listar_tipos).getResultList();
	       return lista;	
	}

}
