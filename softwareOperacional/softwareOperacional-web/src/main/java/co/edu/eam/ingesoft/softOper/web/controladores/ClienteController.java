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
import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Departamento;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

/**
 * 
 * Clase encargada de la logica del controlador para el cliente
 * 
 * @author <Paula Casta�o Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@Named("clienteControlador")
@ViewScoped
public class ClienteController implements Serializable {

	@Pattern(regexp = "[A-Za-z ]*", message = "Solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 15")
	private String nombre;

	@Pattern(regexp = "[A-Za-z ]*", message = "Solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 2000")
	private String apellido;

	private Date fechanaci;

	@Pattern(regexp = "[0-9 ]*", message = "Solo n�meros")
	@Length(min = 6, max = 10, message = "longitud entre 6 y 10")
	private String cedula;

	private String genero;

	private Cliente clie;

	private Municipio municipio;

	private int idMuni;

	private String departamento;

	private List<Cliente> cliente;

	private List<Area> areas;

	private ArrayList<Cliente> filtroCliente = new ArrayList<Cliente>();

	private List<Municipio> muni;

	private List<Departamento> departamentos;

	// auditoria

	private Usuario usuario;

	public int getIdMuni() {
		return idMuni;
	}

	public void setIdMuni(int idMuni) {
		this.idMuni = idMuni;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public AreaEJB getArEJB() {
		return arEJB;
	}

	public void setArEJB(AreaEJB arEJB) {
		this.arEJB = arEJB;
	}

	public Cliente getClie() {
		return clie;
	}

	public void setClie(Cliente clie) {
		this.clie = clie;
	}

	public ArrayList<Cliente> getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(ArrayList<Cliente> filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	private Empleado empleado;

	private String ingreso;

	private String browserDetails;

	private String userAgent;

	private String user2;

	private String browser;

	private String os;

	private int clientesInfo;

	public int getClientesInfo() {
		return clientesInfo;
	}

	public void setClientesInfo(int clientesInfo) {
		this.clientesInfo = clientesInfo;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

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

	@EJB
	AreaEJB arEJB;

	@EJB
	private EmpleadoEJB empleadoejb;

	@PostConstruct
	public void inicializador() {
		clientesInfo = cliEJB.listarClientes().size();
		cliente = cliEJB.listarClientes();
		cliente = cliEJB.listarClientes();
		departamentos = empleadoejb.listardepartamentos();

	}

	/**
	 * 
	 * Metodo encargado de listar los municipios
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Municipio> getMuni() {
		return muni;
	}

	public void setMuni(List<Municipio> muni) {
		this.muni = muni;
	}

	public SessionController getSesion() {
		return sesion;
	}

	public void setSesion(SessionController sesion) {
		this.sesion = sesion;
	}

	/**
	 * 
	 * Metodo encargado de crear el cliente
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void crear() {

		if (nombre.isEmpty() || cedula.isEmpty() || apellido.isEmpty()) {
			Messages.addFlashGlobalWarn("Digite los campos Obligatorios");
		} else {
			try {
				Municipio m = cliEJB.buscarMunicipio(idMuni);
				Cliente c = new Cliente();
				c.setNombre(nombre);
				c.setFechaNacimiento(fechanaci);
				c.setApellido(apellido);
				c.setCedula(cedula);
				c.setGenero(genero);
				c.setMunicipioId(m);
				cliEJB.crearCliente(c);
				Messages.addFlashGlobalInfo("Cliente ingresando Correctamente");
				registrarAuditoria("Crear");
				limpiar();
			} catch (Exception e) {
				Messages.addFlashGlobalError(e.getMessage());
			}
		}

	}

	/**
	 * 
	 * Metodo encargado de registrar la accion de buscar en la auditoria
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void buscar() {
		registrarAuditoria("Buscar");
	}

	/**
	 * 
	 * Metodo encargado de realizar la edici�n al cliente
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String procederEditar(Cliente audi) {
		DatosManager.setCodigoCliente(audi.getCodigo());
		return "/paginas/privado/editarCliente.xhtml?faces-redirect=true";
	}

	/**
	 * 
	 * Metodo encargado de resear en la tabla del cliente
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void resetearFitrosTabla() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('audiTable').clearFilters()");
	}

	/**
	 * 
	 * Metodo encargado de limpiar en el cliente
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void limpiar() {
		nombre = "";
		apellido = "";
		fechanaci = null;
		cedula = "";
		genero = null;
		muni = null;
		departamento = null;

	}

	/**
	 * 
	 * Metodo encargado de cambiar el municipio dependiendo del departamento
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void onDepartamentoChange() {
		if (departamento != null && !departamento.equals(""))
			muni = empleadoejb.listarMuniporDepto(departamento);
	}

	/**
	 * 
	 * Metodo encargado de eliminar el cliente
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void eliminar(Cliente venta) {

		try {
			cliEJB.eliminar(venta.getCodigo());
			cliente = cliEJB.listarClientes();
			Messages.addFlashGlobalInfo("Se ha eliminado el cliente correctamente");
			resetearFitrosTabla();
			registrarAuditoria("Eliminar");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Error al eliminar el cliente");
		}
	}

	/**
	 * 
	 * Metodo encargado de registrar la auditoria en el cliente
	 * 
	 * @author <Paula casta�o aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("Cliente");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
