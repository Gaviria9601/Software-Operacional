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
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.InventarioEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.TipoProducto;

@Named("inventarioController")
@ViewScoped
public class InventarioController implements Serializable {

	@Pattern(regexp = "[A-Za-z ]*", message = "Solo Letras")
	@Length(min = 0, max = 30, message = "Longitud entre 0 y 30")
	private String nombre;

	private TipoProducto tipoProducto;

	private Date fechaIngreso;

	@Length(min = 0, max = 2000, message = "Longitud entre 0 y 2000")
	private String descripcion;

	private int cantidadProducto;

	@Length(min = 0, max = 20, message = "Longitud entre 0 y 20")
	private String codigoLote;

	@Pattern(regexp = "[0-9]*", message = "Solo numeros")
	@Length(min = 0, max = 10, message = "longitud entre 0 y 10")
	private String peso="0";

	@Length(min = 0, max = 30, message = "Longitud entre 0 y 30")
	private String dimensiones;

	private Empleado empleado;

	@Pattern(regexp = "[0-9]*", message = "Solo numeros")
	@Length(min = 0, max = 10, message = "longitud entre 0 y 10")
	private String valor="0";

	private List<TipoProducto> tipoproductos;

	private List<Producto> productos;

	private ArrayList<Producto> filtroProducto = new ArrayList<Producto>();

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<TipoProducto> getTipoproductos() {
		return tipoproductos;
	}

	public void setTipoproductos(List<TipoProducto> tipoproductos) {
		this.tipoproductos = tipoproductos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public String getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@EJB
	private EmpleadoEJB empEJB;

	@EJB
	private InventarioEJB invEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private ProductoEJB proEJB;

	@Inject
	private SessionController sesion;

	/**
	 * 
	 * @param query
	 * @return
	 */
	public List<Empleado> completeTheme(String query) {
		List<Empleado> allThemes = empEJB.listarEmpleados();
		List<Empleado> filteredThemes = new ArrayList<Empleado>();

		for (int i = 0; i < allThemes.size(); i++) {
			Empleado skin = allThemes.get(i);
			if (skin.getNombre().toLowerCase().contains(query) || skin.getApellido().toLowerCase().contains(query)) {
				filteredThemes.add(skin);
			}
		}

		return filteredThemes;
	}

	@PostConstruct
	public void inicializar() {
		tipoproductos = proEJB.listarTipoProducto();
		productos = proEJB.listarProductos();
	}

	/**
	 * 
	 */
	public void crear() {
		if (nombre.isEmpty() || fechaIngreso == null || cantidadProducto == 0 || codigoLote.isEmpty()
				|| empleado == null || valor.isEmpty()) {
			Messages.addFlashGlobalWarn("Digite los campos Obligatorios");
		} else {
			try {
				Producto pro = new Producto();
				pro.setNombre(nombre);
				pro.setFechaIngreso(fechaIngreso);
				pro.setDescripcion(descripcion);
				pro.setCantidad(cantidadProducto);
				pro.setCodigoLote(codigoLote);
				pro.setPeso(Integer.parseInt(peso));
				pro.setDimensiones(dimensiones);
				pro.setValor(Integer.parseInt(valor));
				pro.setTipoProducto(tipoProducto);
				pro.setEmpleado(empleado);
				invEJB.crearProducto(pro);
				Messages.addFlashGlobalInfo("Producto ingresando Correctamente");
				registrarAuditoria("Crear");
				limpiar();
			} catch (Exception e) {
				Messages.addFlashGlobalError(e.getMessage());
			}
		}
	}

	/**
	 * 
	 */
	public void limpiar() {
		nombre = null;
		fechaIngreso = null;
		descripcion = null;
		cantidadProducto = 0;
		codigoLote = null;
		peso = "0";
		dimensiones = null;
		valor = "0";
		tipoProducto = null;
		empleado = null;
	}

	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("Inventario");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void buscar() {
		registrarAuditoria("Buscar");
	}

	/**
	 * 
	 * @return
	 */
	public String procederEditar(Producto ven) {
		DatosManager.setCodigoVenta(ven.getCodigo());
		return "/paginas/privado/editarInventario.xhtml?faces-redirect=true";
	}

}
