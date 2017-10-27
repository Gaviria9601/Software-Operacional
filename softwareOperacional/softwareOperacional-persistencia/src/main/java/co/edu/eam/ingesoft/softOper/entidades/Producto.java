package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Producto")
@NamedQueries({
	@NamedQuery(name=Producto.LISTAR_PRODUCTOS,query="select pro from Producto pro"),
	@NamedQuery(name=Producto.LISTAR_PRODUCTOS_DIF,query="select pro from Producto pro where pro.codigo<>?1")
})
public class Producto implements Serializable {
	
	public static final String LISTAR_PRODUCTOS = "ListarProductos";
	
	public static final String LISTAR_PRODUCTOS_DIF = "ListarProductosDif";
	
	
	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="nombre", length=30)
	private String nombre;
	
	@Column(name="descripcion", length=2000)
	private String descripcion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaIngreso",nullable = false)
	private Date fechaIngreso;
	
	@Column(name="cantidad")
	private int cantidad;

	@Column(name="codigoLote", length=20)
	private String codigoLote;
	
	@Column(name="peso")
	private String peso;
	
	@Column(name="dimensiones", length=30)
	private String dimensiones;
	
	@Column(name="valor")
	private int valor;
	
	@ManyToOne
	@JoinColumn(name = "empleado", nullable=false)
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "tipoProducto", nullable=false)
	private TipoProducto tipoProducto;
	
	public Producto(){
		
	}
	
	public Producto(int codigo, String nombre, String descripcion, Date fechaIngreso, int cantidad, String codigoLote,
			String peso, String dimensiones, int valor, Empleado empleado, TipoProducto tipoProducto) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaIngreso = fechaIngreso;
		this.cantidad = cantidad;
		this.codigoLote = codigoLote;
		this.peso = peso;
		this.dimensiones = dimensiones;
		this.valor = valor;
		this.empleado = empleado;
		this.tipoProducto = tipoProducto;
	}


	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the codigoLote
	 */
	public String getCodigoLote() {
		return codigoLote;
	}

	/**
	 * @param codigoLote the codigoLote to set
	 */
	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	/**
	 * @return the peso
	 */
	public String getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(String peso) {
		this.peso = peso;
	}

	/**
	 * @return the dimensiones
	 */
	public String getDimensiones() {
		return dimensiones;
	}

	/**
	 * @param dimensiones the dimensiones to set
	 */
	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	
	/**
	 * @return the tipoProducto
	 */
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	
	
}
