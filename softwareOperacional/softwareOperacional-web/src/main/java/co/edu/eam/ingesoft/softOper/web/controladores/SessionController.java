package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

@Named("sessionControl")
@SessionScoped
public class SessionController implements Serializable {

	private String user;
	private String pass;

	private Usuario usuario;
	private String tipoUsuario = "Administrador";
	
	private Empleado empleado;

	@EJB
	private SeguridadEJB segEJB;
	
	@EJB
	private EmpleadoEJB empEJB;

	/**
	 * Logea un usuario al sistema
	 */
	public String login() {
		try {
			if (user.isEmpty() || pass.isEmpty()) {
				Messages.addFlashGlobalWarn("Digite los datos para ingresar");
			} else {
				Usuario userTemp = segEJB.buscarUsuario(user);
				tipoUsuario = userTemp.getTipoUsuario().getNombre();
				if (userTemp != null) {
					if (userTemp.getContrasenia().equals(pass)) {
						usuario = userTemp;
						empleado = buscarEmpleado(usuario.getId());
						setTipoUsuario(usuario.getTipoUsuario().getNombre());
						Faces.setSessionAttribute("user", usuario);
						return "/paginas/privado/inicio.xhtml?faces-redirect=true";
					} else {
						Messages.addFlashGlobalError("Contraseña Incorrecta");
					}
				} else {
					Messages.addFlashGlobalError("Usuario no Registrado");
				}
			}
			return null;
		} catch (Exception e) {
			Messages.addFlashGlobalError("Usuario no Registrado");
			e.printStackTrace();
			return "/templates/templateInicioSesion.xhtml?faces-redirect=true";
		}
	}

	/**
	 * 
	 * @return
	 */
	public String cerrarSesion() {
		usuario = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/templates/templateInicioSesion.xhtml?faces-redirect=true";
	}

	public boolean isSesion() {
		return usuario != null;
	}

	public boolean isSesionAdmin() {
		return tipoUsuario.equals("Administrador") && usuario != null;
	}

	
	public String getUser() {
		return user;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	public Empleado buscarEmpleado(int usuario){
		return empEJB.buscarEmpleado(usuario);
	}

}
