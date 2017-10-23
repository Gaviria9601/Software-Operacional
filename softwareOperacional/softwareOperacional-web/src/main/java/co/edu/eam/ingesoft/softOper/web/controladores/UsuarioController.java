package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.TipoUsuarioEJB;
import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Cargo;
import co.edu.eam.ingesoft.softOper.entidades.Departamento;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;

@Named("usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

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

	private TipoUsuario tipoUsuario;

	@Length(max = 20, message = "maximo 20 digitos")
	private String nickname;

	private String contrasenia;

	private Cargo cargo;

	private int usuario;

	private Municipio municipio;

	private String departamento;

	private List<Empleado> empleados;

	private List<Municipio> municipios;

	private List<Cargo> cargos;

	private List<Departamento> departamentos;

	private List<TipoUsuario> tipos;

	private ArrayList<Empleado> filtroEmpleados = new ArrayList<Empleado>();

	private boolean busco = false;

	private Empleado empleado;

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
	 * @return the empleados
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
	private TipoUsuarioEJB tiposejb;

	@EJB
	private SeguridadEJB seguridadejb;


	@PostConstruct
	public void inicializador() {

		cargos = empleadoejb.listarCargos();
		tipos = tiposejb.listarTipoUsuario();
		departamentos = empleadoejb.listardepartamentos();
	}

	public void crear() {
		try {
			Usuario usucreado = crearUsuario();
			if (usucreado != null) {
				Usuario usu = seguridadejb.buscarUsuario(nickname);
				Empleado empleado = new Empleado(nombre, apellido, fechaNacimiento, fechaIngreso, cedula, genero,
						municipio, cargo, usucreado);
				empleadoejb.crear(empleado);

				Messages.addFlashGlobalInfo("Usuario creado correctamente");

			} else {
				Messages.addGlobalError("algo salio mal con la creacion de usuario");
			}

		} catch (ExcepcionNegocio e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	public Usuario crearUsuario() {
		try {
			Usuario usuariop = new Usuario(nickname, contrasenia, tipoUsuario);
			empleadoejb.crearUsuario(usuariop);
			return usuariop;
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
			return null;
		}
	}

	public void onDepartamentoChange() {
		if (departamento != null && !departamento.equals(""))
			municipios = empleadoejb.listarMuniporDepto(departamento);
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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the tipoUsuario
	 */
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario
	 *            the tipoUsuario to set
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the municipio
	 */
	public Municipio getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
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
	 * @return the cargo
	 */
	public Cargo getCargo() {
		return cargo;
	}

}
