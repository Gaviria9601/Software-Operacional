package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "navegador_dimension")
@NamedQueries({
	@NamedQuery(name=navegador_dimension.LISTAR_NAVEGADOR_DIMENSION,query="select nav from navegador_dimension nav")
})
public class navegador_dimension implements Serializable {
	
	public static final String LISTAR_NAVEGADOR_DIMENSION = "Navegador.ListarNavegadorDimension";

	@Id
	@Column(name="codigo", nullable=false)
	private int codigo;
	
	@Column(name="navegador")
	private String navegador;
	
	public navegador_dimension() {
		// TODO Auto-generated constructor stub
	}

	public navegador_dimension(int codigo, String navegador) {
		super();
		this.codigo = codigo;
		this.navegador = navegador;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
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
		navegador_dimension other = (navegador_dimension) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
}
