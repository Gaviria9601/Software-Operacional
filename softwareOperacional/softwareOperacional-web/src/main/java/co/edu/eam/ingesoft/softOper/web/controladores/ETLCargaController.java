package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Auditoria_hechoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EtlEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Ventas_hechoEJB;
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.Municipio;
import co.edu.eam.ingesoft.softOper.entidades.auditoria_hecho;
import co.edu.eam.ingesoft.softOper.entidades.cliente_dimension;
import co.edu.eam.ingesoft.softOper.entidades.empleado_dimension;
import co.edu.eam.ingesoft.softOper.entidades.navegador_dimension;
import co.edu.eam.ingesoft.softOper.entidades.origen_dimension;
import co.edu.eam.ingesoft.softOper.entidades.producto_dimension;
import co.edu.eam.ingesoft.softOper.entidades.tiempo_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_dimension;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;


@Named("ETLCargaController")
@ViewScoped
public class ETLCargaController implements Serializable {
	
	private List<origen_dimension> origenes;
	
	private List <navegador_dimension> navegadores;
	
	private List <producto_dimension> productos;
	
	private List <auditoria_hecho> auditorias;
	

	private List <cliente_dimension> clientes;
	
	private List <empleado_dimension> empleados;
	
	//private List <tiempo_dimension> tiempo;
	List<tiempo_dimension> tiempos = new ArrayList<tiempo_dimension>();
	
	private List <venta_dimension> ventasD;
	
	private List <venta_hecho> ventasH;
	
	public List<origen_dimension> getOrigenes() {
		return origenes;
	}
	public void setOrigenes(List<origen_dimension> origenes) {
		this.origenes = origenes;
	}
	public List<navegador_dimension> getNavegadores() {
		return navegadores;
	}
	public void setNavegadores(List<navegador_dimension> navegadores) {
		this.navegadores = navegadores;
	}
	public List<producto_dimension> getProductos() {
		return productos;
	}
	public void setProductos(List<producto_dimension> productos) {
		this.productos = productos;
	}
	public List<auditoria_hecho> getAuditorias() {
		return auditorias;
	}
	public void setAuditorias(List<auditoria_hecho> auditorias) {
		this.auditorias = auditorias;
	}
	public List<cliente_dimension> getClientes() {
		return clientes;
	}
	public void setClientes(List<cliente_dimension> clientes) {
		this.clientes = clientes;
	}
	public List<empleado_dimension> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<empleado_dimension> empleados) {
		this.empleados = empleados;
	}
	
	public List<venta_dimension> getVentasD() {
		return ventasD;
	}
	public void setVentasD(List<venta_dimension> ventasD) {
		this.ventasD = ventasD;
	}
	public List<venta_hecho> getVentasH() {
		return ventasH;
	}
	public void setVentasH(List<venta_hecho> ventasH) {
		this.ventasH = ventasH;
	}
	public EtlEJB getEtljb() {
		return etljb;
	}
	public void setEtljb(EtlEJB etljb) {
		this.etljb = etljb;
	}

	
	
	/**
	 * 
	 * @param codigo
	 * @param navegador
	 */
	public void insertarOrigen(int codigo,String navegador){
		origen_dimension o = new origen_dimension(codigo, navegador);
		for (int i=0; i < o.hashCode(); i++);
        etljb.insertarOrigen(o);
     }
	
	/**
	 * 
	 * @param codigo
	 * @param navegador
	 */
	public void insertaNavegador(int codigo,String navegador){
		navegador_dimension n = new navegador_dimension (codigo, navegador);
        etljb.insertarNavegador(n);
     }
     /**
      * 
      * @param codigo
      * @param nombre
      * @param precio
      * @param fechaingreso
      * @param cantidad
      */
	public void insertaProducto(int codigo, String nombre, int precio, Date fechaingreso, int cantidad) {
		producto_dimension p = new producto_dimension(codigo, nombre, precio, fechaingreso, cantidad);
        etljb.insertarProducto(p);
     }
	 /**
	  * 
	  * @param codigo
	  * @param nombre
	  * @param genero
	  */
	public void insertaCliente(int codigo, String nombre, String genero) {
		cliente_dimension c = new cliente_dimension(codigo, nombre, genero);
        etljb.insertarCliente(c);
     }
	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param genero
	 * @param nombrecargo
	 */
	public void insertaEmpleado(int codigo, String nombre, String genero, String nombrecargo) {
		empleado_dimension e = new empleado_dimension(codigo, nombre, genero, nombrecargo);
       etljb.insertarEmpleado(e);
     }
	/**
	 * 
	 * @param codigo
	 * @param fecha
	 * @param total
	 * @param nombrevendedor
	 * @param nombrecliente
	 */
	
	public void insertaVentaD(int codigo, Date fecha, int total, String nombrevendedor, String nombrecliente) {
		venta_dimension v = new venta_dimension(codigo, fecha, total, nombrevendedor, nombrecliente);
       etljb.insertarVentaD(v);
     }
	/**
	 * 
	 * @param codigo
	 * @param fechaauditoria
	 * @param tablaaccion
	 * @param accion
	 * @param navegador
	 * @param origen
	 * @param tiempo
	 */
	
	public void insertaAditoria(int codigo, Date fechaauditoria, String tablaaccion, String accion,
			navegador_dimension navegador, origen_dimension origen, tiempo_dimension tiempo) {
		auditoria_hecho a = new auditoria_hecho(codigo, fechaauditoria, tablaaccion, accion, navegador, origen, tiempo);
       etljb.insertarAuditoria(a);
     }
	/**
	 * 
	 * @param codigo
	 * @param totaldetalle
	 * @param cantidad
	 * @param fechaventa
	 * @param venta
	 * @param tiempo
	 * @param producto
	 * @param cliente
	 * @param empleado
	 */
  public void insertaVentaH(int codigo, int totaldetalle, int cantidad, Date fechaventa, venta_dimension venta,
			tiempo_dimension tiempo, producto_dimension producto, cliente_dimension cliente,
			empleado_dimension empleado) {
		venta_hecho v = new venta_hecho(codigo, totaldetalle, cantidad, fechaventa, venta, tiempo, producto, cliente, empleado);
        etljb.insertarVentaH(v);
     }
	/**
	 * 
	 * @param codigo
	 * @param trimestre
	 * @param mes
	 */
	public void insertaTiempo(int codigo, String trimestre, String mes)  {
		tiempo_dimension t = new tiempo_dimension(codigo, trimestre, mes);
        etljb.insertarTiempo(t);
     }
	
	/**
	 * 
	 */
	@EJB
	private EtlEJB etljb;
	
	@EJB
	private Auditoria_hechoEJB audHecEJB;

	@EJB
	private Ventas_hechoEJB venHecEJB;

	@EJB
	private AuditoriaEJB audEJB;
	
	public void carga() {
		try{
		procesoCarga();
		Messages.addFlashGlobalInfo("Se cargaron correctamente los Datos");
		}catch (Exception e) {
			Messages.addFlashGlobalError("MIRE MIRE MIRE ERRORRRRRRRR");
		}
	}
	
    public void procesoCarga() {
		
		List<tiempo_dimension> ti = etljb.listarTiempo();
		for (tiempo_dimension tiem : ti) {
			tiempos.add(tiem);
			insertaTiempo(tiem.getCodigo(), tiem.getTrimestre(), tiem.getMes());
		}
			
		
}
}

