package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Venta;

@LocalBean
@Stateless
public class VentaEJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;
	
	/**
	 * 
	 * @return
	 */
	public List<Venta> listarVentas(){
		return em.createNamedQuery(Venta.LISTAR_VENTAS).getResultList();
	}
	
}
