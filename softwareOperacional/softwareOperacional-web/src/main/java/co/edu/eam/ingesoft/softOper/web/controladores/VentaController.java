package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DualListModel;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.VentaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.Venta;

@Named("ventaController")
@ViewScoped
public class VentaController implements Serializable {
	
	private Venta selectedVenta;

	private Cliente cliente;

	private List<Cliente> clientes;

	private DualListModel<Producto> productos;

	private Date fecha;

	private int cantidadProducto;
	
	private List<Venta> ventas;
	
	private ArrayList<Venta> filtroVenta = new ArrayList<Venta>();

	@EJB
	private ClienteEJB cliEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private ProductoEJB proEJB;
	
	@EJB
	private VentaEJB venEJB;
	
	
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

	@PostConstruct
	public void inicializar() {
		clientes = cliEJB.listarClientes();
		ventas = venEJB.listarVentas();
		List<Producto> productosSource = proEJB.listarProductos();
		List<Producto> productosTarget = new ArrayList<Producto>();
		productos = new DualListModel<Producto>(productosSource, productosTarget);
		fecha = generarFechaActual();
	}

	public void crear() {

	}

	public void limpiar() {

	}

	/**
	 * 
	 * @return
	 */
	public Date generarFechaActual() {
		return audEJB.generarFechaActual();
	}
	
	/**
	 * 
	 * @return
	 */
	public String procederEditar(){
		return "/paginas/privado/editarVenta.xhtml?faces-redirect=true";
	}

	/**
	 * 
	 * @param query
	 * @return
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
	
}
