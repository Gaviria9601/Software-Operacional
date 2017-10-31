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
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Departamento;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

@Named("clienteEditController")
@ViewScoped
public class ClienteEditController implements Serializable {
	
	@Pattern(regexp = "[A-Za-z ]*", message = "Solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 15")
	private String nombre;

	@Pattern(regexp = "[A-Za-z ]*", message = "Solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 2000")
	private String apellido;

	private Date fechanaci;

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

    private Usuario usuario;

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

	public Cliente getClie() {
		return clie;
	}

	public void setClie(Cliente clie) {
		this.clie = clie;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

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

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public ArrayList<Cliente> getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(ArrayList<Cliente> filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public List<Municipio> getMuni() {
		return muni;
	}

	public void setMuni(List<Municipio> muni) {
		this.muni = muni;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@EJB
	ClienteEJB cliEJB;

	@EJB
	AreaEJB arEJB;

	@EJB
	private EmpleadoEJB empleadoejb;

	@EJB
	private EmpleadoEJB empEJB;

	@EJB
	private AuditoriaEJB audEJB;
	
	
	@Inject
	private SessionController sesion;
    
	@PostConstruct
	public void inicializar() {
		clie = cliEJB.buscarCliente(DatosManager.getCodigoCliente());
	
		nombre = clie.getNombre();
		apellido = clie.getApellido();
	    fechanaci = clie.getFechaNacimiento();
		cedula = clie.getCedula();
	    genero = clie.getGenero();
		departamentos = empleadoejb.listardepartamentos();
	    idMuni = clie.getMunicipioId().getId();
	}
	public void onDepartamentoChange() {
		if (departamento != null && !departamento.equals(""))
			muni = empleadoejb.listarMuniporDepto(departamento);
	}
	
	public String editar() {
		
		if (nombre.isEmpty() || cedula.isEmpty() || apellido.isEmpty()) {
			Messages.addFlashGlobalWarn("Digite los campos Obligatorios");
		} else {
			try {
				
				clie.setApellido(apellido);
				clie.setNombre(nombre);
				clie.setCedula(cedula);
		        clie.setFechaNacimiento(fechanaci);
		        clie.setGenero(genero);
		        clie.setMunicipioId(municipio);
				cliEJB.editar(clie);
				Messages.addFlashGlobalInfo("El cliente ha sido Editado Correctamente");
				registrarAuditoria("Editar");
				limpiar();
				return "/paginas/privado/verCliente.xhtml?faces-redirect=true";
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
			audi.setRegistroRealizoAccion("Cliente");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void limpiar() {
		nombre = "";
		apellido = "";
		cedula = "";
		genero = null;
		fechanaci = null;
		municipio = null;
	}

	public String cancelar() {
		limpiar();
		return "/paginas/privado/verCliente.xhtml?faces-redirect=true";
	}
	
    public void resetearFitrosTabla() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('audiTable').clearFilters()");
	}
	
	

	

}
