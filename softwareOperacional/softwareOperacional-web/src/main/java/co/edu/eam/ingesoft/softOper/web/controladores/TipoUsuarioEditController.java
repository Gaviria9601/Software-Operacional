package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.TipoUsuarioEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;

@Named("edittipoUsuariocontrolador")
@ViewScoped
public class TipoUsuarioEditController implements Serializable {

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 15")
	private String nombre;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 10, max = 2000, message = "longitud entre 10 y 2000")
	private String des;
	
	TipoUsuario tipoUsuario;
	
	@EJB
	private TipoUsuarioEJB tipousuejb;
	
	@EJB
	private AuditoriaEJB audEJB;
	
	@Inject
	private SessionController sesion;
	
	@PostConstruct
	public void inicializar() {
		tipoUsuario = tipousuejb.buscarTipoUsuario(DatosManager.getCodigoTipoUsuario());
		nombre = tipoUsuario.getNombre();
		des = tipoUsuario.getDescripcion();
	}
	
	public String cancelar() {
		limpiar();
		return "/paginas/privado/verTipoUsuario.xhtml?faces-redirect=true";
	}
	
	public void limpiar(){
		nombre = null;
		des = null;
	}
	
	
		public String editar() {
			if (nombre.isEmpty() || des.isEmpty()) {
				Messages.addFlashGlobalWarn("Digite los campos Obligatorios");
			} else {
				try {
					tipoUsuario.setNombre(nombre);
					tipoUsuario.setDescripcion(des);
					tipousuejb.editar(tipoUsuario);
					Messages.addFlashGlobalInfo("tipo de usuario Editado Correctamente");
					registrarAuditoria("Editar");
					limpiar();
					return "/paginas/privado/verTipoUsuario.xhtml?faces-redirect=true";
				} catch (Exception e) {
					Messages.addFlashGlobalError(e.getMessage());
				}

			}
			return null;
		}

	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("TipoUsuario");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}

	/**
	 * @return the tipoUsuario
	 */
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return the sesion
	 */
	public SessionController getSesion() {
		return sesion;
	}

	/**
	 * @param sesion the sesion to set
	 */
	public void setSesion(SessionController sesion) {
		this.sesion = sesion;
	}
}
