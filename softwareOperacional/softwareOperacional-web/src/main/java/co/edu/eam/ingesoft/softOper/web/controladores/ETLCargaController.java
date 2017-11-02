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
	
	
	List<origen_dimension> origenes = new ArrayList<origen_dimension>();
	
	List<navegador_dimension> navegador = new ArrayList<navegador_dimension>();
	
	List<producto_dimension> productos = new ArrayList<producto_dimension>();
	
	List<auditoria_hecho> auditorias = new ArrayList<auditoria_hecho>();
	
	List<cliente_dimension> clientes = new ArrayList<cliente_dimension>();
	
	List<empleado_dimension> empleados = new ArrayList<empleado_dimension>();
	
	List<tiempo_dimension> tiempos = new ArrayList<tiempo_dimension>();
	
	List<venta_dimension> ventasD = new ArrayList<venta_dimension>();
	
	List<venta_hecho> ventasH = new ArrayList<venta_hecho>();
	
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
  public void insertaVentaH(venta_hecho ven) {
		        etljb.insertarVentaH(ven);
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
		
    	//TIEMPOS
		List<tiempo_dimension> ti = etljb.listarTiempo();
		for (tiempo_dimension tiem : ti) {
			tiempos.add(tiem);
			insertaTiempo(tiem.getCodigo(), tiem.getTrimestre(), tiem.getMes());
			
	  
	   //CLIENTE
		List<cliente_dimension> cli = etljb.listarCliente();
		for(cliente_dimension c: cli){
			clientes.add(c);
			insertaCliente(c.getCodigo(), c.getNombre(), c.getGenero());
			
	  //NAVEGADOR
		List<navegador_dimension> na = etljb.listarNavegador();
			for(navegador_dimension d: na){
				navegador.add(d);
				insertaNavegador(d.getCodigo(), d.getNavegador());
			
       //PRODUCTO
		List<producto_dimension> po = etljb.listarProducto();
				for(producto_dimension p: po){
					productos.add(p);
					insertaProducto(p.getCodigo(), p.getNombre(), p.getPrecio(), p.getFechaingreso(), p.getCantidad());
					
	   //VENTA DIMENSION
		List<venta_dimension> ve = etljb.listarVentaD();
				for(venta_dimension v: ve){
					ventasD.add(v);
					insertaVentaD(v.getCodigo(), v.getFecha(), v.getTotal(), v.getNombrevendedor(), v.getNombrecliente());
				
		//EMPLEADO
		 List<empleado_dimension> em = etljb.listarEmpleado();
				for(empleado_dimension e: em){
					empleados.add(e);
					insertaEmpleado(e.getCodigo(), e.getNombre(), e.getGenero(), e.getNombrecargo());
								
					 
		//ORIGEN
		List <origen_dimension> ori = etljb.listarOrigen();
				for(origen_dimension og: ori){
					origenes.add(og);
					insertarOrigen(og.getCodigo(), og.getDispositivo());
								
		//AUDITORIA
								
	   //VENTA HECHO
		List <venta_hecho> vh = etljb.listarVentaH();
				for(venta_hecho g: vh){
					
										venta_hecho w = new venta_hecho();
										w.setCodigo(g.getCodigo());
										w.setTotaldetalle(g.getTotaldetalle());
										w.setCantidad(g.getCantidad());
										w.setFechaventa(g.getFechaventa());
										w.setVenta(venHecEJB.buscarVentaDimension(g.getVenta().getCodigo()));
										w.setTiempo(etljb.buscarPTiempoDimension(g.getTiempo().getCodigo()));
										w.setProducto(venHecEJB.buscarProductoDimension(g.getProducto().getCodigo()));
										w.setCliente(etljb.buscarClienteDimension(g.getCliente().getCodigo()));
										w.setEmpleado(etljb.buscarEmpleadoDimension(g.getEmpleado().getCodigo()));
										insertaVentaH(w);
									
										}
								}
							
							
								
									}
						    	}
							}
								
							}
		
		
	}
			
			
		}
    }
    }



		



