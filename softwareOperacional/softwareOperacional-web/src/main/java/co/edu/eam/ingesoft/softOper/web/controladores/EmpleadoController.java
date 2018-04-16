package co.edu.eam.ingesoft.softOper.web.controladores;

/**
 * 
 * Clase encargada de la logica del controlador del empleado
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
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

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cargo;
import co.edu.eam.ingesoft.softOper.entidades.Departamento;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;

@Named("empleadoController")
@ViewScoped
public class EmpleadoController implements Serializable {

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 3, max = 30, message = "longitud entre 3 y 15")
	private String nombre;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 3, max = 30, message = "longitud entre 3 y 15")
	private String apellido;

	@Pattern(regexp = "[0-9]*", message = "solo numeros")
	@Length(min = 4, max = 15, message = "longitud entre 4 y 15")
	private String cedula;

	private Date fechaNacimiento;

	private Date fechaIngreso;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 1, max = 1, message = "longitud 1")
	private String genero;

	private int cargo;

	private int municipio;

	private String departamento;

	private List<Empleado> empleados;

	private List<Municipio> municipios;

	private List<Cargo> cargos;

	private List<Departamento> departamentos;

	private ArrayList<Empleado> filtroEmpleados = new ArrayList<Empleado>();

	private boolean busco = false;

	private Empleado empleado;

	private int empleadosInfo;

	public int getEmpleadosInfo() {
		return empleadosInfo;
	}

	public void setEmpleadosInfo(int empleadosInfo) {
		this.empleadosInfo = empleadosInfo;
	}

	/**
	 * 
	 * Metodo encargado de obtener el nombre del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * Metodo encargado de obtener el apellido del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * 
	 * Metodo encargado de obtener la cedula del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * 
	 * Metodo encargado de obtener la fecha de nacimiento del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * 
	 * Metodo encargado de obtener la fecha de ingreso del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso
	 *            the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * 
	 * Metodo encargado de obtener el genero del empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero
	 *            the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * 
	 * Metodo encargado de listar los empleados
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	/**
	 * @param empleados
	 *            the empleados to set
	 */
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	/**
	 * @return the filtroEmpleados
	 */
	public ArrayList<Empleado> getFiltroEmpleados() {
		return filtroEmpleados;
	}

	/**
	 * @param filtroEmpleados
	 *            the filtroEmpleados to set
	 */
	public void setFiltroEmpleados(ArrayList<Empleado> filtroEmpleados) {
		this.filtroEmpleados = filtroEmpleados;
	}

	/**
	 * @return the busco
	 */
	public boolean isBusco() {
		return busco;
	}

	/**
	 * @param busco
	 *            the busco to set
	 */
	public void setBusco(boolean busco) {
		this.busco = busco;
	}

	/**
	 * 
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado
	 *            the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@EJB
	private EmpleadoEJB empleadoejb;

	@EJB
	private SeguridadEJB seguridadejb;

	@EJB
	private AuditoriaEJB audEJB;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializador() {
		empleadosInfo = empleadoejb.listarEmpleados().size();
		cargos = empleadoejb.listarCargos();
		departamentos = empleadoejb.listardepartamentos();
		empleados = empleadoejb.listarEmpleados();
	}

	/**
	 * 
	 * Metodo encargado de crear los empleados
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void crear() {
		try {
			Cargo c = empleadoejb.buscarCargo(cargo);
			Municipio m = empleadoejb.buscarMunicipio(municipio);
			Empleado empleado = new Empleado();
			empleado.setApellido(apellido);
			empleado.setCargo(c);
			empleado.setCedula(cedula);
			empleado.setFechaIngresoEmpresa(fechaIngreso);
			empleado.setFechaNacimiento(fechaNacimiento);
			empleado.setGenero(genero);
			empleado.setMunicipio(m);
			empleado.setNombre(nombre);
			empleado.setUsuario(empleadoejb.buscarUsuarioNombre("Empleado"));
			registrarAuditoriaEmpleado("Guardar");
			empleadoejb.crearEmpleadosinUsuario(empleado);
			limpiar();
			Messages.addFlashGlobalInfo("Usuario creado correctamente");
		} catch (ExcepcionNegocio e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo encargado de registar la accion de buscar en la auditoria
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void buscar() {
		registrarAuditoriaEmpleado("Buscar");
	}

	/**
	 * 
	 * Metodo encargado de cambiar de municipio dependiendo del departamento
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void onDepartamentoChange() {
		if (departamento != null && !departamento.equals(""))
			municipios = empleadoejb.listarMuniporDepto(departamento);
	}

	/**
	 * 
	 * Metodo encargado de editar los empleados
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String procederEditar(Empleado audi) {
		DatosManager.setCodigoEmpleado2(audi.getCodigo());
		return "/paginas/privado/editarEmpleado.xhtml?faces-redirect=true";
	}

	/**
	 * 
	 * Metodo encargado de realizar los filtros en la tabla de empleado
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void resetearFitrosTabla() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('audiTable').clearFilters()");
	}

	/**
	 * 
	 * Metodo encargado de registrar la auditoria en empleadoss
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void registrarAuditoriaEmpleado(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("Empleado");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Metodo encargado de limpiar los empleados
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void limpiar() {
		nombre = null;
		apellido = null;
		cedula = null;
		fechaIngreso = null;
		fechaNacimiento = null;
		municipio = -1;
		cargo = -1;
	}

	/**
	 * 
	 * Metodo encargado de listar los municipios
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Municipio> getMunicipios() {
		return municipios;
	}

	/**
	 * @param municipios
	 *            the municipios to set
	 */
	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	/**
	 * 
	 * Metodo encargado de listar los departamentos
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	/**
	 * @param departamentos
	 *            the departamentos to set
	 */
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * 
	 * Metodo encargado de listar los cargos
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public List<Cargo> getCargos() {
		return cargos;
	}

	/**
	 * @param cargos
	 *            the cargos to set
	 */
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	/**
	 * @return the cargo
	 */
	public int getCargo() {
		return cargo;
	}

	/**
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the municipio
	 */
	public int getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}
}
