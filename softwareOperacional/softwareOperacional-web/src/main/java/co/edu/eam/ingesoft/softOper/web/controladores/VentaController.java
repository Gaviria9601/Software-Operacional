package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoVentaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.VentaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.Venta;

/**
 * 
 * Clase encargada de la logica del controlador para la venta
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@Named("ventaController")
@ViewScoped
public class VentaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Venta selectedVenta;

	private Venta ventaGeneral;

	private Cliente cliente;

	private List<Cliente> clientes;

	private DualListModel<Producto> productos;

	private Date fecha;

	private int cantidadProducto;

	private String infoGeneral;

	private int cantSobrantes;

	private int valorProducto;

	private int cantProductos;

	private boolean empezar;

	private int totalVenta;

	private List<Venta> ventas;

	private int ventasInfo;

	private ArrayList<Venta> filtroVenta = new ArrayList<Venta>();

	@EJB
	private ClienteEJB cliEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private ProductoEJB proEJB;

	@EJB
	private VentaEJB venEJB;

	@EJB
	private ProductoVentaEJB proVenEJB;

	@Inject
	private SessionController sesion;

	@Inject
	private VentaEditController ventEdi;

	public int getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(int totalVenta) {
		this.totalVenta = totalVenta;
	}

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
	}

	public int getVentasInfo() {
		return ventasInfo;
	}

	public void setVentasInfo(int ventasInfo) {
		this.ventasInfo = ventasInfo;
	}

	public String getInfoGeneral() {
		return infoGeneral;
	}

	public void setInfoGeneral(String infoGeneral) {
		this.infoGeneral = infoGeneral;
	}

	public int getCantSobrantes() {
		return cantSobrantes;
	}

	public void setCantSobrantes(int cantSobrantes) {
		this.cantSobrantes = cantSobrantes;
	}

	public int getValorProducto() {
		return valorProducto;
	}

	public void setValorProducto(int valorProducto) {
		this.valorProducto = valorProducto;
	}

	public Venta getVentaGeneral() {
		return ventaGeneral;
	}

	public void setVentaGeneral(Venta ventaGeneral) {
		this.ventaGeneral = ventaGeneral;
	}

	public ArrayList<Venta> getFiltroVenta() {
		return filtroVenta;
	}

	public void setFiltroVenta(ArrayList<Venta> filtroVenta) {
		this.filtroVenta = filtroVenta;
	}

	public Venta getSelectedVenta() {
		return selectedVenta;
	}

	public void setSelectedVenta(Venta selectedVenta) {
		this.selectedVenta = selectedVenta;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DualListModel<Producto> getProductos() {
		return productos;
	}

	public void setProductos(DualListModel<Producto> productos) {
		this.productos = productos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isEmpezar() {
		return empezar;
	}

	public void setEmpezar(boolean empezar) {
		this.empezar = empezar;
	}

	@PostConstruct
	public void inicializar() {
		clientes = cliEJB.listarClientes();
		ventas = venEJB.listarVentas();
		ventasInfo = venEJB.listarVentas().size();
		List<Producto> productosSource = proEJB.listarProductos();
		List<Producto> productosTarget = new ArrayList<Producto>();
		productos = new DualListModel<Producto>(productosSource, productosTarget);
		fecha = generarFechaActual();
	}

	/**
	 * 
	 * Metodo encargado de terminar la venta
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void terminar() {
		List<Producto> produc = productos.getTarget();
		for (int i = 0; i < produc.size(); i++) {
			produc.remove(i);
		}
		ventaGeneral.setTotalVenta(totalVenta);
		venEJB.editarVenta(ventaGeneral);
		infoGeneral = "";
		cantSobrantes = 0;
		valorProducto = 0;
		totalVenta = 0;
		cantProductos = 0;
		cliente = null;
		empezar = false;
		Messages.addFlashGlobalInfo("Venta Terminada con Exito");
		registrarAuditoria("Crear");
	}

	/**
	 * 
	 * Metodo encargado deempezar la venta
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void empezarVenta() {
		if (cliente != null) {
			Venta venta = new Venta();
			venta.setFecha(audEJB.generarFechaActual());
			venta.setVendedor(sesion.buscarEmpleado(sesion.getUsuario().getId()));
			venta.setCliente(cliente);
			venta.setTotalVenta(totalVenta);
			venEJB.crearVenta(venta);
			ventaGeneral = venEJB.listarVentas().get(venEJB.listarVentas().size() - 1);
			empezar = true;
			Messages.addFlashGlobalInfo("Se ha iniciado la Venta");
		} else {
			Messages.addFlashGlobalWarn("Busque el Cliente para empezar la Venta");
		}
	}

	/**
	 * 
	 * Metodo encargado de generar la fecha actual
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public Date generarFechaActual() {
		return audEJB.generarFechaActual();
	}

	/**
	 * 
	 * Metodo encargado de direccionar la pagina a editar
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	/**
	 * 
	 * @return
	 */
	public String procederEditar(Venta ven) {
		DatosManager.setCodigoVenta(ven.getCodigo());
		return "/paginas/privado/editarVenta.xhtml?faces-redirect=true";
	}

	/**
	 * 
	 * Metodo encargado de listar los clientes
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public List<Cliente> completeTheme(String query) {
		List<Cliente> allThemes = cliEJB.listarClientes();
		List<Cliente> filteredThemes = new ArrayList<Cliente>();

		for (int i = 0; i < allThemes.size(); i++) {
			Cliente skin = allThemes.get(i);
			if (skin.getNombre().toLowerCase().contains(query) || skin.getApellido().toLowerCase().contains(query)) {
				filteredThemes.add(skin);
			}
		}

		return filteredThemes;
	}

	/**
	 * 
	 * @param event
	 */
	public void onSelect(SelectEvent event) {
		Producto pro = (Producto) event.getObject();
		infoGeneral = "Descripción: " + pro.getDescripcion() + " Peso: " + pro.getPeso() + " Dimensiones: "
				+ pro.getDimensiones();
		cantSobrantes = pro.getCantidad();
		valorProducto = pro.getValor();
	}

	/**
	 * 
	 * @param event
	 */
	public void onUnselect(UnselectEvent event) {
		infoGeneral = null;
		cantSobrantes = 0;
		valorProducto = 0;
	}

	/**
	 * 
	 * @param event
	 */
	public void onTransfer(TransferEvent event) {
		if (event.isAdd()) {
			try {
				if (cantidadProducto > 0) {
					for (Object item : event.getItems()) {
						Producto pro = (Producto) item;
						proVenEJB.agregarProductoVenta(pro, ventaGeneral, cantidadProducto);
						int total = pro.getValor() * cantidadProducto;
						totalVenta += total;
						cantProductos = productos.getTarget().size();
						Messages.addFlashGlobalInfo("Producto Agregado");
					}
				} else {
					event.isRemove();
					Messages.addFlashGlobalError("La Cantidad de productos a agregar debe ser mayor que 0");
				}
			} catch (Exception e) {
				Messages.addFlashGlobalError(e.getMessage());
			}
		} else if (event.isRemove()) {
			int totalVen;
			for (Object item : event.getItems()) {
				Producto pro = (Producto) item;
				totalVen = proVenEJB.eliminarProductoVenta(pro.getCodigo(), ventaGeneral.getCodigo());
				cantProductos = productos.getTarget().size();
				totalVenta -= totalVen;
				Messages.addFlashGlobalInfo("Producto Eliminado");

			}
		}
		cantidadProducto = 0;
		infoGeneral = null;
		cantSobrantes = 0;
		valorProducto = 0;
	}

	/**
	 * 
	 * Metodo encargado de eliminar la venta
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void eliminar(Venta venta) {
		try {
			venEJB.eliminarVenta(venta.getCodigo());
			ventas = venEJB.listarVentas();
			resetearFitrosTabla();
			registrarAuditoria("Eliminar");
			Messages.addFlashGlobalInfo("Se ha eliminado la venta correctamente");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Error al eliminar la venta");
		}
	}

	/**
	 * 
	 * Metodo encargado de registrar la accion de buscar en la auditoria
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void buscar() {
		registrarAuditoria("Buscar");
	}

	/**
	 * 
	 * Metodo encargado de resetear el filtro de la tabla
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void resetearFitrosTabla() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('ventaTable').clearFilters()");
	}

	/**
	 * 
	 * Metodo encargado de registrar e la auditoria
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("Venta");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
