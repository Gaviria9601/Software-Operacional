package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
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
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

/**
 * 
 * Clase encargada de la logica del  controlador para el area
 * 
 * @author <Paula Casta�o Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@Named("areaControlador")
@ViewScoped
public class AreaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 6, max = 30, message = "longitud entre 6 y 30")
	private String nombre;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 10, max = 2000, message = "longitud entre 10 y 2000")
	private String des;

	private int id;

	private List<Area> areas;

	private ArrayList<Area> filtroArea = new ArrayList<Area>();

	private boolean busco = false;

	private Area ar;

	// auditoria

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
	public void inicializador() {
		areas = arEJB.listarArea();
	}

	/**
	 * 
	 * Metodo encargado de crear el area
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */

	public void crear() {
		if (nombre.isEmpty()) {
			Messages.addFlashGlobalWarn("Digite los campos Obligatorios");
		} else {
			try {
				Area ar = new Area();
				ar.setNombre(nombre);
				ar.setDescripcion(des);

				arEJB.crear(ar);
				Messages.addFlashGlobalInfo("El area a sido ingresada correctamente");
				registrarAuditoria("Crear");
				limpiar();
			} catch (Exception e) {
				Messages.addFlashGlobalError(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * Metodo encargado de modificar el area
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void modificar(Area audi) {
		ar = audi;
		nombre = audi.getNombre();
		des = audi.getDescripcion();
		busco = true;
	}

	/**
	 * 
	 * Metodo encargado de buscar el area
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void buscar() {
		registrarAuditoria("Buscar");
	}

	/**
	 * 
	 * Metodo encargado de editar el area
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */

	public String procederEditar(Area audi) {
		DatosManager.setIdArea(audi.getId());
		return "/paginas/privado/editarArea.xhtml?faces-redirect=true";
	}

	/**
	 * 
	 * Metodo encargado de filtar en la tabla de area
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void resetearFitrosTabla() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('audiTable').clearFilters()");
	}

	/**
	 * 
	 * Metodo encargado de eliminar el area
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void eliminarA(Area venta) {

		try {
			arEJB.eliminar(venta.getId());
			areas = arEJB.listarArea();
			Messages.addFlashGlobalInfo("Se ha eliminado el area correctamente");
			resetearFitrosTabla();
			registrarAuditoria("Eliminar");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Error al eliminar el area");
		}
	}

	/**
	 * 
	 * Metodo encargado de limpiar los campos
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void limpiar() {
		nombre = "";
		des = "";
	}

	/**
	 * 
	 * Metodo encargado de registrar las auditorias en el area
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("Area");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
