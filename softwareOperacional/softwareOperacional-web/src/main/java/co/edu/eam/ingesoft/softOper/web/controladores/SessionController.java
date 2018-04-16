package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Conexion;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;
/**
 * 
 * Clase encargada de la logica del controlar la sesion
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@Named("sessionControl")
@SessionScoped
public class SessionController implements Serializable {

	private String user;

	private String pass;

	private Usuario usuario;

	private String tipoUsuario = "Administrador";

	private Empleado empleado;

	private String ingreso;

	private String browserDetails;

	private String userAgent;

	private String user2;

	private String browser;

	private String os;

	private int numVisitas;

	private String conexion;

	@EJB
	private SeguridadEJB segEJB;

	@EJB
	private EmpleadoEJB empEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private Conexion conEJB;

	/**
	 * 
	 * Metodo encargado realizar el login
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
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
						ingreso = "Ingreso";
						registrarAuditoria();
						numVisitas = audEJB.listarAuditoriasIdentificacionUsuarios().size();
						conexion = conEJB.getConexion();
						return "/paginas/privado/inicio.xhtml?faces-redirect=true";
					} else {
						Messages.addFlashGlobalError("Contraseña Incorrecta");
						ingreso = "No ingreso - Contraseña mal Ingresada";
						registrarAuditoria();
					}
				} else {
					Messages.addFlashGlobalError("Usuario no Registrado");
					ingreso = "No ingreso - El Usuario no existe";
					registrarAuditoria();
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
	 * Metodo encargado de cerrar sesion
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
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

	public boolean isSesionUsuario() {
		return !tipoUsuario.equals("Administrador") && usuario != null;
	}

	public boolean isSesionVendedora() {
		return tipoUsuario.equals("Vendedor") && usuario != null;
	}

	public boolean isSesionContadora() {
		return tipoUsuario.equals("Contador") && usuario != null;
	}

	public boolean isSesionWindows() {
		return os.equals("Windows");
	}

	public String getUser() {
		return user;
	}

	public String getConexion() {
		return conexion;
	}

	public void setConexion(String conexion) {
		this.conexion = conexion;
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

	public String getIngreso() {
		return ingreso;
	}

	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getNumVisitas() {
		return numVisitas;
	}

	public void setNumVisitas(int numVisitas) {
		this.numVisitas = numVisitas;
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Empleado buscarEmpleado(int usuario) {
		return empEJB.buscarEmpleado(usuario);
	}

	/**
	 * 
	 * Metodo encargado de registrar la audiroia 
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void registrarAuditoria() {
		try {
			Auditoria audi = new Auditoria();
			audi.setIngreso(ingreso);
			browserDetails = Faces.getRequest().getHeader("User-Agent");
			userAgent = browserDetails;
			user2 = userAgent.toLowerCase();
			identificarNavegadorPeticion();
			audi.setAccion("Ingresando al Sistema");
			audi.setRegistroRealizoAccion("Identificacion Usuario");
			audi.setUsuario(usuario);
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Metodo encargado de identificar el navegador del usuario
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
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

}
