package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Cliente;


@LocalBean
@Stateless
public class ClienteEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;
	
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Cliente buscarCliente(int codigo){
		return em.find(Cliente.class, codigo);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Cliente> listarClientes() {
		return em.createNamedQuery(Cliente.LISTAR_CLIENTES).getResultList();
		
	}
	
}
