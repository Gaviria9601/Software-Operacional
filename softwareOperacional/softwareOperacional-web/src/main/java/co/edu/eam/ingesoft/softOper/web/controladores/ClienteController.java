package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;


import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

@Named("clienteControlador")
@ViewScoped
public class ClienteController implements Serializable{

	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 3 y 15")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 10 y 2000")
	private String apellido;
	
	
	private Date fechanaci;
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 10 y 2000")
	private String cedula;
	
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 3 y 15")
	private String genero;
	
	
	private Municipio municipio;
	
	//auditoria
	
		private Usuario usuario;

		private Empleado empleado;

		private String ingreso;

		private String browserDetails;

		private String userAgent;

		private String user2;

		private String browser;

		private String os;

		

		@EJB
		private SeguridadEJB segEJB;

		@EJB
		private EmpleadoEJB empEJB;

		@EJB
		private AuditoriaEJB audEJB;
		
		


	


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


		public String getBrowserDetails() {
			return browserDetails;
		}


		public void setBrowserDetails(String browserDetails) {
			this.browserDetails = browserDetails;
		}


		public String getUserAgent() {
			return userAgent;
		}


		public void setUserAgent(String userAgent) {
			this.userAgent = userAgent;
		}


		public String getUser2() {
			return user2;
		}


		public void setUser2(String user2) {
			this.user2 = user2;
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


		public SeguridadEJB getSegEJB() {
			return segEJB;
		}


		public void setSegEJB(SeguridadEJB segEJB) {
			this.segEJB = segEJB;
		}


		public EmpleadoEJB getEmpEJB() {
			return empEJB;
		}


		public void setEmpEJB(EmpleadoEJB empEJB) {
			this.empEJB = empEJB;
		}


		public AuditoriaEJB getAudEJB() {
			return audEJB;
		}


		public void setAudEJB(AuditoriaEJB audEJB) {
			this.audEJB = audEJB;
		}


		public ClienteEJB getCliEJB() {
			return cliEJB;
		}


		public void setCliEJB(ClienteEJB cliEJB) {
			this.cliEJB = cliEJB;
		}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Date getFechanaci() {
		return fechanaci;
	}


	public void setFechanaci(Date fechanaci) {
		this.fechanaci = fechanaci;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	

	@EJB
	ClienteEJB cliEJB;
	
	@PostConstruct
	public void inicializador(){
		
	}
	
	public void crear(){
		try{
			Cliente c = new Cliente();
			c.setNombre(nombre);
			c.setApellido(apellido);
			c.setFechaNacimiento(fechanaci);
			c.setCedula(cedula);
			c.setGenero(genero);
			c.setMunicipioId(municipio);
			
			cliEJB.crearCliente(c);
		
			 
		    Auditoria audi = new Auditoria();
			browserDetails = Faces.getRequest().getHeader("User-Agent");
			userAgent = browserDetails;
			user2 = userAgent.toLowerCase();
			identificarNavegador();
			audi.setIngreso(ingreso);
			audi.setOrigen(os);
			audi.setNavegador(browser);
			audi.setAccion("Crear");
			audi.setRegistroRealizoAccion("Cliente");
			audi.setUsuario(usuario);
			audEJB.registrarAuditoria(audi);
			//limpiar();
			Messages.addFlashGlobalInfo("Cliente ingresado Correctamente");
				
		} catch (ExcepcionNegocio e) {
		       Messages.addGlobalError(e.getMessage());
			   }
		}
	
	public void identificarNavegador() {

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
