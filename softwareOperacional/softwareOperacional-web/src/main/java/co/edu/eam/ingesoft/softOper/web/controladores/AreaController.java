package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;


import co.edu.eam.ingesoft.softOpe.negocio.beans.AreaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

@Named("areaControlador")
@ViewScoped
public class AreaController implements Serializable {
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 3 y 15")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 10 y 2000")
	private String des;
	
	private int id;
	
	private List<Area> areas;
	
	private ArrayList<Area> filtroArea = new ArrayList<Area>();
	
	private boolean busco = false;
	
	private Area ar;
	
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

	public Area getAr() {
		return ar;
	}

	public void setAr(Area ar) {
		this.ar = ar;
	}

	public boolean isBusco() {
		return busco;
	}

	public void setBusco(boolean busco) {
		this.busco = busco;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public ArrayList<Area> getFiltroArea() {
		return filtroArea;
	}

	public void setFiltroArea(ArrayList<Area> filtroArea) {
		this.filtroArea = filtroArea;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AreaEJB getArEJB() {
		return arEJB;
	}

	public void setArEJB(AreaEJB arEJB) {
		this.arEJB = arEJB;
	}


	@EJB
	private AreaEJB arEJB;
	
	@PostConstruct
	public void inicializador(){
		areas = arEJB.listarArea();
	}
	
	/**
	 * Metodo para crear el area de la empresa
	 */
	
	
	
	
	public void crear(){
		try{
			
			Area a = new Area(nombre, des);
			arEJB.crear(a);
			 
		    Auditoria audi = new Auditoria();
			browserDetails = Faces.getRequest().getHeader("User-Agent");
			userAgent = browserDetails;
			user2 = userAgent.toLowerCase();
			identificarNavegador();
			audi.setIngreso(ingreso);
			audi.setOrigen(os);
			audi.setNavegador(browser);
			audi.setAccion("Crear");
			audi.setRegistroRealizoAccion("Area");
			audi.setUsuario(usuario);
			audEJB.registrarAuditoria(audi);
			limpiar();
			Messages.addFlashGlobalInfo("Area ingresada Correctamente");
				
		} catch (ExcepcionNegocio e) {
		       Messages.addGlobalError(e.getMessage());
			   }
		}
	
	/**
	 * Metodo para filtar en la tabla
	 * @param id
	 */
	public void resetearFitrosTabla(String id) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('vtWidget').clearFilters()");
	}
	/**
	 * Metodo para modificar
	 * @param audi
	 */
	public void modificar (Area audi) {
		ar = audi;
		nombre = audi.getNombre();
		des = audi.getDescripcion();
		busco = true;
	}
	
	public String procederEditar(){
		return "/paginas/privado/editarArea.xhtml?faces-redirect=true";
	}
    /**
     * Metodo para editar
     */
	public void editar() {
		ar.setNombre(nombre);
		ar.setDescripcion(des);
		limpiar();
		Auditoria audi = new Auditoria();
		browserDetails = Faces.getRequest().getHeader("User-Agent");
		userAgent = browserDetails;
		user2 = userAgent.toLowerCase();
		identificarNavegador();
		audi.setIngreso(ingreso);
		audi.setOrigen(os);
		audi.setNavegador(browser);
		audi.setAccion("Editar");
		audi.setRegistroRealizoAccion("Area");
		audi.setUsuario(usuario);
		audEJB.registrarAuditoria(audi);
		Messages.addGlobalInfo("El area fue modificada con exito");
	
	}

	public void eliminar(Area audi) {
		try {
			arEJB.eliminarArea(audi.getNombre());
			limpiar();
			
			Auditoria audir = new Auditoria();
			browserDetails = Faces.getRequest().getHeader("User-Agent");
			userAgent = browserDetails;
			user2 = userAgent.toLowerCase();
			identificarNavegador();
			audir.setIngreso(ingreso);
			audir.setOrigen(os);
			audir.setNavegador(browser);
			audir.setAccion("Eliminar");
			audir.setRegistroRealizoAccion("Area");
			audir.setUsuario(usuario);
			audEJB.registrarAuditoria(audir);
			
			Messages.addFlashGlobalInfo("Se ha eliminado la area");			
			areas = arEJB.listarArea();
			resetearFitrosTabla("tablaIdUsuarios");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Metodo para identificar el navegador
	 */
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
	/**
	 * Metodo para limpiar los campos
	 */
	public void limpiar(){
		nombre = "";
		des = "";
	}


}
