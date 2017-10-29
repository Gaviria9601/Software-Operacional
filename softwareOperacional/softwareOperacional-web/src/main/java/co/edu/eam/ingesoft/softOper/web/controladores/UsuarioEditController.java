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

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.TipoUsuarioEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cargo;
import co.edu.eam.ingesoft.softOper.entidades.Departamento;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

@Named("usuarioeditController")
@ViewScoped
public class UsuarioEditController implements Serializable {

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

	private int tipoUsuario;

	@Length(max = 20, message = "maximo 20 digitos")
	private String nickname;

	private String contrasenia;

	private int cargo;

	private int usuario;

	private int municipio;

	private String departamento;

	private List<Municipio> municipios;

	private List<Cargo> cargos;

	private List<Departamento> departamentos;

	private List<TipoUsuario> tipos;

	private Empleado empleado;

	private Usuario usu;

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private EmpleadoEJB empleadoejb;

	@EJB
	private SeguridadEJB seguridadejb;

	@EJB
	private TipoUsuarioEJB tiposejb;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializar() {
		empleado = empleadoejb.buscarEmpleado2(DatosManager.getCodigoEmpleado());
		// cargando datos del empleado a editar
		nombre = empleado.getNombre();
		apellido = empleado.getApellido();
		cargo = empleado.getCargo().getId();
		cedula = empleado.getCedula();
		fechaIngreso = empleado.getFechaIngresoEmpresa();
		fechaNacimiento = empleado.getFechaNacimiento();
		genero = empleado.getGenero();
		municipio = empleado.getMunicipio().getId();

		usu = empleado.getUsuario();
		tipoUsuario = usu.getTipoUsuario().getId();
		contrasenia = usu.getContrasenia();

		cargos = empleadoejb.listarCargos();
		tipos = tiposejb.listarTipoUsuario();
		departamentos = empleadoejb.listardepartamentos();
	}

	public String cancelar() {
		limpiar();
		return "/paginas/privado/verEmpleadoUsuario.xhtml?faces-redirect=true";
	}

	public void limpiar() {
		nombre = null;
		apellido = null;
		cedula = null;
		fechaIngreso = null;
		fechaNacimiento = null;
		municipio = -1;
		tipoUsuario = -1;
		cargo = -1;
		contrasenia= null;
	}

	public String editar() {
		try {
			empleado.setNombre(nombre);

			empleado.setApellido(apellido);
			Cargo c = empleadoejb.buscarCargo(cargo);
			empleado.setCargo(c);
			empleado.setCedula(cedula);
			empleado.setFechaIngresoEmpresa(fechaIngreso);
			empleado.setFechaNacimiento(fechaNacimiento);
			empleado.setGenero(genero);
			Municipio m = empleadoejb.buscarMunicipio(municipio);
			empleado.setMunicipio(m);
			TipoUsuario tipo = tiposejb.buscarTipoUsuario(tipoUsuario);

			usu.setContrasenia(contrasenia);
			usu.setTipoUsuario(tipo);

			empleadoejb.editarEmpleado(empleado);
			empleadoejb.editarUsuario(usu);
			Messages.addFlashGlobalInfo("usuario Editado Correctamente");
			registrarAuditoriaEmpleado("Editar");
			registrarAuditoriaUsuario("Editar");
			limpiar();
			return "/paginas/privado/verEmpleadoUsuario.xhtml?faces-redirect=true";
		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());
		}
		return null;
	}

	public void onDepartamentoChange() {
		if (departamento != null && !departamento.equals(""))
			municipios = empleadoejb.listarMuniporDepto(departamento);
	}

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

	public void registrarAuditoriaUsuario(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("Usuario");
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
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
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
	 * @return the cedula
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
	 * @return the fechaNacimiento
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
	 * @return the fechaIngreso
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
	 * @return the genero
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
	 * @return the tipoUsuario
	 */
	public int getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario
	 *            the tipoUsuario to set
	 */
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia
	 *            the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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
	 * @return the usuario
	 */
	public int getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(int usuario) {
		this.usuario = usuario;
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

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the municipios
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
	 * @return the cargos
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
	 * @return the departamentos
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

	/**
	 * @return the tipos
	 */
	public List<TipoUsuario> getTipos() {
		return tipos;
	}

	/**
	 * @param tipos
	 *            the tipos to set
	 */
	public void setTipos(List<TipoUsuario> tipos) {
		this.tipos = tipos;
	}

	/**
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

	/**
	 * @return the usu
	 */
	public Usuario getUsu() {
		return usu;
	}

	/**
	 * @param usu
	 *            the usu to set
	 */
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

}
