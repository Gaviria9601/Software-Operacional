package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import co.edu.eam.ingesoft.softOper.entidades.Auditoria;

@LocalBean
@Stateless
public class AuditoriaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 
	 * @param auditoria
	 */
	public void registrarAuditoria(Auditoria auditoria){
		auditoria.setFechaHora(generarFechaActual());
		em.persist(auditoria);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriasIdentificacionUsuarios(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA_ID_USUARIOS).setParameter(1, "Ingreso").setParameter(2, "Identificacion Usuario")
				.getResultList();
	}
	
	/**
	 * Genera la fecha actual del sistema
	 * @return
	 */
	public Date generarFechaActual() {
		Calendar fechaHora = Calendar.getInstance();
		Date fecha = fechaHora.getTime();
		return fecha;
	}

}
