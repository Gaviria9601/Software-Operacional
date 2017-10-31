package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "origen_dimension")
public class origen_dimension implements Serializable{

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="dispotivo")
	private String dispositivo;
	
	public origen_dimension() {
		// TODO Auto-generated constructor stub
	}

	public origen_dimension(int codigo, String dispositivo) {
		super();
		this.codigo = codigo;
		this.dispositivo = dispositivo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
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
		origen_dimension other = (origen_dimension) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
}
