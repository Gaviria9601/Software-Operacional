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

@Entity
@Table(name = "auditoria_hecho")
@NamedQueries({
		@NamedQuery(name = auditoria_hecho.LISTAR_AUDITORIA_HECHO, query = "select aud from auditoria_hecho aud") })
public class auditoria_hecho implements Serializable {

	public static final String LISTAR_AUDITORIA_HECHO = "auditoria_hecho.listarAuditoriaHecho";

	@Id
	@Column(name = "codigo", nullable = false)
	private int codigo;

	@Column(name = "fechaauditoria")
	private Date fechaauditoria;

	@Column(name = "tablaaccion")
	private String tablaaccion;

	@Column(name = "accion")
	private String accion;

	@ManyToOne
	@JoinColumn(name = "navegador", nullable = false)
	private navegador_dimension navegador;

	@ManyToOne
	@JoinColumn(name = "origen", nullable = false)
	private origen_dimension origen;

	@ManyToOne
	@JoinColumn(name = "tiempo", nullable = false)
	private tiempo_dimension tiempo;

	public auditoria_hecho() {
		// TODO Auto-generated constructor stub
	}

	public auditoria_hecho(int codigo, Date fechaauditoria, String tablaaccion, String accion,
			navegador_dimension navegador, origen_dimension origen, tiempo_dimension tiempo) {
		super();
		this.codigo = codigo;
		this.fechaauditoria = fechaauditoria;
		this.tablaaccion = tablaaccion;
		this.accion = accion;
		this.navegador = navegador;
		this.origen = origen;
		this.tiempo = tiempo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFechaauditoria() {
		return fechaauditoria;
	}

	public void setFechaauditoria(Date fechaauditoria) {
		this.fechaauditoria = fechaauditoria;
	}

	public String getTablaaccion() {
		return tablaaccion;
	}

	public void setTablaaccion(String tablaaccion) {
		this.tablaaccion = tablaaccion;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public navegador_dimension getNavegador() {
		return navegador;
	}

	public void setNavegador(navegador_dimension navegador) {
		this.navegador = navegador;
	}

	public origen_dimension getOrigen() {
		return origen;
	}

	public void setOrigen(origen_dimension origen) {
		this.origen = origen;
	}

	public tiempo_dimension getTiempo() {
		return tiempo;
	}

	public void setTiempo(tiempo_dimension tiempo) {
		this.tiempo = tiempo;
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
		auditoria_hecho other = (auditoria_hecho) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}
