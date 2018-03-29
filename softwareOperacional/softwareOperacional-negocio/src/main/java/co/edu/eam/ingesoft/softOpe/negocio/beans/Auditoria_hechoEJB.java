package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.auditoria_hecho;
import co.edu.eam.ingesoft.softOper.entidades.navegador_dimension;
import co.edu.eam.ingesoft.softOper.entidades.origen_dimension;
import co.edu.eam.ingesoft.softOper.entidades.tiempo_dimension;

@LocalBean
@Stateless
public class Auditoria_hechoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * @param aud
	 */
	public void ingresarAuditoriaHecho(auditoria_hecho aud) {
		em.persist(aud);
	}

	/**
	 * 
	 * @param orDim
	 */
	public void ingresarOrigenDimension(origen_dimension orDim) {
		em.persist(orDim);
	}

	/**
	 * 
	 * @param dispositivo
	 */
	public origen_dimension buscarOrigenDimension(String dispositivo) {
		return (origen_dimension) em.createNamedQuery(origen_dimension.BUSCAR_NOMBRE).setParameter(1, dispositivo)
				.getSingleResult();
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public origen_dimension buscarOrigen(int codigo) {
		return em.find(origen_dimension.class, codigo);
	}

	/**
	 * 
	 * @param navDim
	 */
	public void ingresarNavegadorDimension(navegador_dimension navDim) {
		em.persist(navDim);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public navegador_dimension buscarNave(int codigo) {
		return em.find(navegador_dimension.class, codigo);
	}

	/**
	 * 
	 * @param dispositivo
	 */
	public navegador_dimension buscarNavegadorDimension(String navegador) {
		return (navegador_dimension) em.createNamedQuery(navegador_dimension.BUSCAR_NOMBRE).setParameter(1, navegador)
				.getSingleResult();
	}

	/**
	 * 
	 * @return
	 */
	public List<auditoria_hecho> listarAuditoriasHecho() {
		return em.createNamedQuery(auditoria_hecho.LISTAR_AUDITORIA_HECHO).getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<navegador_dimension> listarNavegadorDimension() {
		return em.createNamedQuery(navegador_dimension.LISTAR_NAVEGADOR_DIMENSION).getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<origen_dimension> listarOrigenDimension() {
		return em.createNamedQuery(origen_dimension.LISTAR_ORIGEN_DIMENSION).getResultList();
	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public tiempo_dimension buscarTiempoDimension(Integer codigo) {
		return codigo != null ? em.find(tiempo_dimension.class, codigo) : null;

	}

}
