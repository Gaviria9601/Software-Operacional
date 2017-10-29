package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

@LocalBean
@Stateless
public class SeguridadEJB{

	@PersistenceContext(unitName = Conexion.OPCION )
	private EntityManager em;

	/**
	 * Busca un Usuario para saber si esta registrado
	 * 
	 * @param user
	 * @return
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
	 * Registra un usuario
	 * 
	 * @param u,
	 *            usuario a registrar
	 */
	public void registrarUsuario(Usuario u) {
		if (buscarUsuario(u.getNombre()) == null) {
			em.persist(u);
		} else {
			throw new ExcepcionNegocio("El Usuario ya se encuentra registrado");
		}
	}

}
