package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.Date;

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
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.TipoProducto;

/**
 * 
 * Clase encargada de la logica del controlador para editar el inventario
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@Named("inventarioEditController")
@ViewScoped
public class InventarioEditController implements Serializable {

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

	@Length(min = 0, max = 20, message = "longitud entre 0 y 20")
	private String peso = "0";

	private String pesoOpcion;

	@Length(min = 0, max = 30, message = "Longitud entre 0 y 30")
	private String dimensiones;

	private Empleado empleado;

	@Pattern(regexp = "[0-9]*", message = "Solo numeros")
	@Length(min = 0, max = 10, message = "longitud entre 0 y 10")
	private String valor = "0";

	private Producto pro;

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

	public String getPesoOpcion() {
		return pesoOpcion;
	}

	public void setPesoOpcion(String pesoOpcion) {
		this.pesoOpcion = pesoOpcion;
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

	public Producto getPro() {
		return pro;
	}

	public void setPro(Producto pro) {
		this.pro = pro;
	}

	@EJB
	private EmpleadoEJB empEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private ProductoEJB proEJB;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializar() {
		pro = proEJB.buscarProduto(DatosManager.getCodigoProducto());
		nombre = pro.getNombre();
		tipoProducto = pro.getTipoProducto();
		fechaIngreso = pro.getFechaIngreso();
		descripcion = pro.getDescripcion();
		cantidadProducto = pro.getCantidad();
		codigoLote = pro.getCodigoLote();
		String[] datos = pro.getPeso().split(" ");
		peso = datos[0];
		pesoOpcion = datos[1];
		dimensiones = pro.getDimensiones();
		empleado = pro.getEmpleado();
		valor = pro.getValor() + "";

	}

	/**
	 * 
	 * Metodo encargado de registrar la accion de buscar en la auditoria
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void buscar() {
		registrarAuditoria("Buscar");
	}

	/**
	 * 
	 * Metodo encargado de realizar la edicion en el inventario
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String editar() {
		if (nombre.isEmpty() || fechaIngreso == null || cantidadProducto == 0 || codigoLote.isEmpty()
				|| empleado == null || valor.isEmpty()) {
			Messages.addFlashGlobalWarn("Digite los campos Obligatorios");
		} else {
			try {
				pro.setNombre(nombre);
				pro.setFechaIngreso(fechaIngreso);
				pro.setDescripcion(descripcion);
				pro.setCantidad(cantidadProducto);
				pro.setCodigoLote(codigoLote);
				pro.setPeso(peso + " " + pesoOpcion);
				pro.setDimensiones(dimensiones);
				pro.setValor(Integer.parseInt(valor));
				pro.setTipoProducto(tipoProducto);
				pro.setEmpleado(empleado);
				proEJB.editarProducto(pro);
				Messages.addFlashGlobalInfo("Producto Editado Correctamente");
				registrarAuditoria("Editar");
				limpiar();
				return "/paginas/privado/verInventario.xhtml?faces-redirect=true";
			} catch (Exception e) {
				Messages.addFlashGlobalError(e.getMessage());
			}

		}
		return null;
	}

	/**
	 * 
	 * Metodo encargado de limpiar la edicion en el inventario
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
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
		pesoOpcion = "";
	}

	/**
	 * 
	 * Metodo encargado de cancelar la edicion en el inventario
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public String cancelar() {
		limpiar();
		return "/paginas/privado/verInventario.xhtml?faces-redirect=true";
	}

	/**
	 * 
	 * Metodo encargado de registrar la auditoria
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
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

}
