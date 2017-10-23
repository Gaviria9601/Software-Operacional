package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoVentaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.VentaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Venta;

@Named("ventaEditController")
@ViewScoped
public class VentaEditController implements Serializable {

	private Venta venta;

	private Cliente cliente;

	private int cantProductos;

	private int totalVenta;

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
	
	

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	
	@EJB
	private ProductoVentaEJB proVenEJB;
	
	@EJB
	private VentaEJB venEJB;

	
	/**
	 * 
	 */
	@PostConstruct
	public void inicializar() {
		venta = venEJB.buscarVenta(DatosManager.getCodigoVenta());
		cliente = venta.getCliente();
		cantProductos = proVenEJB.listarProductosVenta(venta.getCodigo()).size();
		totalVenta = venta.getTotalVenta();
		
	}

	
	
}
