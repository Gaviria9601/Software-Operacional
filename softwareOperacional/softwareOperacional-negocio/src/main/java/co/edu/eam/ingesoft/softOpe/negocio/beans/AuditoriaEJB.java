package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import co.edu.eam.ingesoft.softOper.entidades.Auditoria;

@LocalBean
@Stateless
public class AuditoriaEJB {

		
	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;
	
	private String browserDetails;

	private String userAgent;

	private String user2;

	private String browser;

	private String os;

	/**
	 * 
	 * @param auditoria
	 */
	public void registrarAuditoria(Auditoria auditoria,String browserDeta) {
		this.browserDetails = browserDeta;
		userAgent = browserDetails;
		user2 = userAgent.toLowerCase();
		identificarNavegadorPeticion();
		auditoria.setOrigen(os);
		auditoria.setNavegador(browser);
		auditoria.setFechaHora(generarFechaActual());
		em.persist(auditoria);
	}
	
	/**
	 * 
	 */
	public void identificarNavegadorPeticion() {

		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		// ===============Browser===========================
		if (user2.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user2.contains("safari") && user2.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user2.contains("opr") || user2.contains("opera")) {
			if (user2.contains("opera"))
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user2.contains("opr"))
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user2.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user2.indexOf("mozilla/7.0") > -1) || (user2.indexOf("netscape6") != -1)
				|| (user2.indexOf("mozilla/4.7") != -1) || (user2.indexOf("mozilla/4.78") != -1)
				|| (user2.indexOf("mozilla/4.08") != -1) || (user2.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user2.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user2.contains("rv")) {
			browser = "IE-" + user2.substring(user2.indexOf("rv") + 3, user2.indexOf(")"));
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}

	}

	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriasIdentificacionUsuarios() {
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA_ID_USUARIOS).setParameter(1, "Ingreso")
				.setParameter(2, "Identificacion Usuario").getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriasIdeUsuarios() {
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "Identificacion Usuario")
				.getResultList();
	}

	/**
	 * Genera la fecha actual del sistema
	 * 
	 * @return
	 */
	public Date generarFechaActual() {
		Calendar fechaHora = Calendar.getInstance();
		Date fecha = fechaHora.getTime();
		return fecha;
	}

	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriasArea() {
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "Area").getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriaVentas(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "Venta").getResultList();
	}

	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriaClientes(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "Cliente").getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriaEmpleados(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "Empleado").getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriaInventarios(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "Inventario").getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriaTiposUsuarios(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "TipoUsuario").getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriaUsuarios(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "Usuario").getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Auditoria> listarAuditoriaProcesosETL(){
		return em.createNamedQuery(Auditoria.LISTAR_AUDITORIA).setParameter(1, "ETL").getResultList();
	}
	
}
