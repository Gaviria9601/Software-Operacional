package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.inventarioEJB;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.TipoProducto;

@Named("inventarioController")
@ViewScoped
public class InventarioController implements Serializable {
	
	
	private String nombre;
	
	private TipoProducto tipoProducto;
	
	private Date fechaIngreso;
	
	private String descripcion;
	
	private int cantidadProducto;
	
	private String codigoLote;
	
	private int peso;
	
	private String dimensiones;
	
	private Empleado empleado;
	
	private int valor;
	
	private List<TipoProducto> tipoproductos;

	
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

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	@EJB
	private EmpleadoEJB empEJB;
	
	@EJB
	private inventarioEJB invEJB;
	
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
	

	
}
