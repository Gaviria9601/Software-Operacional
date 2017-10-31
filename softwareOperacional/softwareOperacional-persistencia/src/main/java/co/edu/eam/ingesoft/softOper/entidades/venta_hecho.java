package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "venta_hecho")
public class venta_hecho implements Serializable{

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="totaldetalle")
	private int totaldetalle;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="fechaventa")
	private Date fechaventa;
	
	@ManyToOne
	@JoinColumn(name = "venta", nullable=false)
	private venta_dimension venta;
	
	@ManyToOne
	@JoinColumn(name = "tiempo", nullable=false)
	private tiempo_dimension tiempo;
	
	@ManyToOne
	@JoinColumn(name = "producto", nullable=false)
	private producto_dimension producto;
	
	@ManyToOne
	@JoinColumn(name = "cliente", nullable=false)
	private cliente_dimension cliente;
	
	@ManyToOne
	@JoinColumn(name = "empleado", nullable=false)
	private empleado_dimension empleado;
	
	public venta_hecho() {
		// TODO Auto-generated constructor stub
	}

	public venta_hecho(int codigo, int totaldetalle, int cantidad, Date fechaventa, venta_dimension venta,
			tiempo_dimension tiempo, producto_dimension producto, cliente_dimension cliente,
			empleado_dimension empleado) {
		super();
		this.codigo = codigo;
		this.totaldetalle = totaldetalle;
		this.cantidad = cantidad;
		this.fechaventa = fechaventa;
		this.venta = venta;
		this.tiempo = tiempo;
		this.producto = producto;
		this.cliente = cliente;
		this.empleado = empleado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getTotaldetalle() {
		return totaldetalle;
	}

	public void setTotaldetalle(int totaldetalle) {
		this.totaldetalle = totaldetalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaventa() {
		return fechaventa;
	}

	public void setFechaventa(Date fechaventa) {
		this.fechaventa = fechaventa;
	}

	public venta_dimension getVenta() {
		return venta;
	}

	public void setVenta(venta_dimension venta) {
		this.venta = venta;
	}

	public tiempo_dimension getTiempo() {
		return tiempo;
	}

	public void setTiempo(tiempo_dimension tiempo) {
		this.tiempo = tiempo;
	}

	public producto_dimension getProducto() {
		return producto;
	}

	public void setProducto(producto_dimension producto) {
		this.producto = producto;
	}

	public cliente_dimension getCliente() {
		return cliente;
	}

	public void setCliente(cliente_dimension cliente) {
		this.cliente = cliente;
	}

	public empleado_dimension getEmpleado() {
		return empleado;
	}

	public void setEmpleado(empleado_dimension empleado) {
		this.empleado = empleado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		venta_hecho other = (venta_hecho) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
}
