package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "venta_dimension")
@NamedQueries({
	@NamedQuery(name=venta_dimension.LISTAR_VENTA_DIMENSION,query="select ven from venta_dimension ven")
})
public class venta_dimension implements Serializable{
	
	public static final String LISTAR_VENTA_DIMENSION = "ListarVentaDimension";

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="total")
	private int total;
	
	@Column(name="nombrevendedor")
	private String nombrevendedor;
	
	@Column(name="nombrecliente")
	private String nombrecliente;
	
	public venta_dimension() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param codigo
	 */
	public venta_dimension(int codigo) {
		super();
		this.codigo = codigo;
	}



	public venta_dimension(int codigo, Date fecha, int total, String nombrevendedor, String nombrecliente) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.nombrevendedor = nombrevendedor;
		this.nombrecliente = nombrecliente;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getNombrevendedor() {
		return nombrevendedor;
	}

	public void setNombrevendedor(String nombrevendedor) {
		this.nombrevendedor = nombrevendedor;
	}

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
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
		venta_dimension other = (venta_dimension) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
	
}
