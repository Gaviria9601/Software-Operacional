package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.auditoria_hecho;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

@LocalBean
@Stateless
public class Ventas_hechoEJB {
	
	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;
	
	/**
	 * 
	 * @return
	 */
	public List<venta_hecho> listarVentasHecho() {
		return em.createNamedQuery(venta_hecho.LISTAR_VENTA_HECHO).getResultList();
	}

}
