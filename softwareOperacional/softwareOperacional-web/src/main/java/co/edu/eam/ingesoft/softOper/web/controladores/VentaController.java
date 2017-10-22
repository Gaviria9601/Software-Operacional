package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DualListModel;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Producto;

@Named("ventaController")
@ViewScoped
public class VentaController implements Serializable {

	private Cliente cliente;

	private List<Cliente> clientes;

	private DualListModel<Producto> productos;

	private Date fecha;

	private int cantidadProducto;

	@EJB
	private ClienteEJB cliEJB;

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private ProductoEJB proEJB;

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
