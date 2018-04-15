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

/**
 * 
 * Clase encargada de la logica del negocio para el EJB para la auditoria hecho
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@LocalBean
@Stateless
public class Auditoria_hechoEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * Metodo encargado de ingresar la auditoria hecho
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void ingresarAuditoriaHecho(auditoria_hecho aud) {
		em.persist(aud);
	}

	/**
	 * 
	 * Metodo encargado de ingresar origen dimencion
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void ingresarOrigenDimension(origen_dimension orDim) {
		em.persist(orDim);
	}

	/**
	 * 
	 * Metodo encargado de buscar el origen(Desde donde estan ingresando) dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public origen_dimension buscarOrigenDimension(String dispositivo) {
		return (origen_dimension) em.createNamedQuery(origen_dimension.BUSCAR_NOMBRE).setParameter(1, dispositivo)
				.getSingleResult();
	}

	/**
	 * 
	 * Metodo encargado de buscar el origen (desde donde estan ingresando)
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public origen_dimension buscarOrigen(int codigo) {
		return em.find(origen_dimension.class, codigo);
	}

	/**
	 * 
	 * Metodo encargado de ingresar el navegador dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void ingresarNavegadorDimension(navegador_dimension navDim) {
		em.persist(navDim);
	}

	/**
	 * 
	 * Metodo encargado de buscar el navegador
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public navegador_dimension buscarNave(int codigo) {
		return em.find(navegador_dimension.class, codigo);
	}

	/**
	 * 
	 * Metodo encargado de buscar el navegador dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public navegador_dimension buscarNavegadorDimension(String navegador) {
		return (navegador_dimension) em.createNamedQuery(navegador_dimension.BUSCAR_NOMBRE).setParameter(1, navegador)
				.getSingleResult();
	}

	/**
	 * 
	 * Metodo encargado de listar la auditoria hecho
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<auditoria_hecho> listarAuditoriasHecho() {
		return em.createNamedQuery(auditoria_hecho.LISTAR_AUDITORIA_HECHO).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar el navegador dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<navegador_dimension> listarNavegadorDimension() {
		return em.createNamedQuery(navegador_dimension.LISTAR_NAVEGADOR_DIMENSION).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de listar el origen dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<origen_dimension> listarOrigenDimension() {
		return em.createNamedQuery(origen_dimension.LISTAR_ORIGEN_DIMENSION).getResultList();
	}

	/**
	 * 
	 * Metodo encargado de buscar el tiempo dimension
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public tiempo_dimension buscarTiempoDimension(Integer codigo) {
		return codigo != null ? em.find(tiempo_dimension.class, codigo) : null;

	}

}
