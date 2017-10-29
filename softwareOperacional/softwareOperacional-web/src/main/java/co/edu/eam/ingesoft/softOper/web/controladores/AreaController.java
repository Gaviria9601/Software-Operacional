package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
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
import co.edu.eam.ingesoft.softOper.entidades.Producto;
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

	@Inject
	private SessionController sesion;

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
		if (nombre.isEmpty()) {
			Messages.addFlashGlobalWarn("Digite los campos Obligatorios");
		} else {
			try {
				Area ar = new Area();
				ar.getDescripcion();
				ar.getNombre();
				arEJB.crear(ar);
				Messages.addFlashGlobalInfo("El area a sido ingresanda Correctamente");
				registrarAuditoria("Crear");
				limpiar();
			} catch (Exception e) {
				Messages.addFlashGlobalError(e.getMessage());
			}
		}
		}
	
	/**
	 * Metodo para filtar en la tabla
	 * @param id
	 */
	
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
	
	public void buscar() {
		registrarAuditoria("Buscar");
	}
	
	public String procederEditar(Area audi) {
		DatosManager.setIdArea(audi.getId());
		return "/paginas/privado/editarArea.xhtml?faces-redirect=true";
	}
	
    

	public void resetearFitrosTabla() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('audiTable').clearFilters()");
	}
	
	public void eliminarA(Area venta) {
		
		try {
			arEJB.eliminar(venta.getId());
			areas = arEJB.listarArea();
			Messages.addFlashGlobalInfo("Se ha eliminado el area correctamente");
			resetearFitrosTabla();
			registrarAuditoria("Eliminar");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Error al eliminar la venta");
		}
	}
	
	/**
	 * Metodo para limpiar los campos
	 */
	public void limpiar(){
		nombre = "";
		des = "";
	}
	
	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("Area");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi,browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
