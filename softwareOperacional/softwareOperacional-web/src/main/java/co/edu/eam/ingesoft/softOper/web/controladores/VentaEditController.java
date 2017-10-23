package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoVentaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.VentaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.ProductoVenta;
import co.edu.eam.ingesoft.softOper.entidades.Venta;

@Named("ventaEditController")
@ViewScoped
public class VentaEditController implements Serializable {

	private int cantidadProducto;

	private String infoGeneral;

	private int cantSobrantes;

	private int valorProducto;

	private DualListModel<Producto> productos;

	private Venta venta;

	private Cliente cliente;

	private int cantProductos;

	private int totalVenta;

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
	}

	public int getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(int totalVenta) {
		this.totalVenta = totalVenta;
	}

	public DualListModel<Producto> getProductos() {
		return productos;
	}

	public void setProductos(DualListModel<Producto> productos) {
		this.productos = productos;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@EJB
	private AuditoriaEJB audEJB;

	@EJB
	private ProductoVentaEJB proVenEJB;

	@EJB
	private ProductoEJB proEJB;

	@EJB
	private VentaEJB venEJB;

	@Inject
	private SessionController sesion;

	/**
	 * 
	 */
	@PostConstruct
	public void inicializar() {
		venta = venEJB.buscarVenta(DatosManager.getCodigoVenta());
		cliente = venta.getCliente();
		cantProductos = proVenEJB.listarProductosVenta(venta.getCodigo()).size();
		totalVenta = venta.getTotalVenta();
		List<ProductoVenta> productosVenta = proVenEJB.listarProductosVenta(venta.getCodigo());
		List<Producto> productosTarget = new ArrayList<Producto>();
		List<Producto> productosSource = new ArrayList<Producto>();
		for (int i = 0; i < productosVenta.size(); i++) {
			productosTarget.add(productosVenta.get(i).getProducto_codigo());
		}
		if (productosTarget.size() == 0) {
			productosSource = proEJB.listarProductos();
		} else if (productosTarget.size() != proEJB.listarProductos().size()) {
			productosSource = proEJB.listarProductosDif(productosTarget);
		} else if (productosTarget.size() == proEJB.listarProductos().size()) {

		}
		productos = new DualListModel<Producto>(productosSource, productosTarget);

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
						proVenEJB.agregarProductoVenta(pro, venta, cantidadProducto);
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
				totalVen = proVenEJB.eliminarProductoVenta(pro.getCodigo(), venta.getCodigo());
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
	 */
	public String editar() {
		List<Producto> produc = productos.getTarget();
		for (int i = 0; i < produc.size(); i++) {
			produc.remove(i);
		}
		venta.setTotalVenta(totalVenta);
		venEJB.editarVenta(venta);
		infoGeneral = "";
		cantSobrantes = 0;
		valorProducto = 0;
		totalVenta = 0;
		cantProductos = 0;
		cliente = null;
		Messages.addFlashGlobalInfo("Venta Editada con Exito");
		registrarAuditoria("Editar");
		return "/paginas/privado/verVenta.xhtml?faces-redirect=true";
	}

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
