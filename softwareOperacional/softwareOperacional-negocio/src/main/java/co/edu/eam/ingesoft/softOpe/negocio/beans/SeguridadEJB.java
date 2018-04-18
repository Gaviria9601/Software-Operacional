package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

/**
 * 
 * Clase encargada de la logica del negocio para el EJB la seguridad
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@LocalBean
@Stateless
public class SeguridadEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	/**
	 * 
	 * Metodo encargado de buscar los usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public Usuario buscarUsuario(String user) {
		List<Usuario> usuario = em.createNamedQuery(Usuario.LISTA_BUSQUEDA_USUARIO).setParameter(1, user)
				.getResultList();
		if (!usuario.isEmpty()) {
			return usuario.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * Metodo encargado de registrar los usuarios
	 * 
	 * @author <Paula castaño aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void registrarUsuario(Usuario u) {
		if (buscarUsuario(u.getNombre()) == null) {
			em.persist(u);
		} else {
			throw new ExcepcionNegocio("El Usuario ya se encuentra registrado");
		}
	}

}
