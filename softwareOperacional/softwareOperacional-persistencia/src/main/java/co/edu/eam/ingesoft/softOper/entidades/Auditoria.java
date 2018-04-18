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
@Table(name = "Auditoria")
@NamedQueries({
		@NamedQuery(name = Auditoria.LISTAR_AUDITORIA_ID_USUARIOS, query = "select aud from Auditoria aud where aud.ingreso = ?1 and aud.registroRealizoAccion = ?2"),
		@NamedQuery(name = Auditoria.LISTAR_AUDITORIA, query = "select aud from Auditoria aud where aud.registroRealizoAccion = ?1")

})
public class Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LISTAR_AUDITORIA_ID_USUARIOS = "Auditoria.listarAuditoriaIdUsuarios";

	public static final String LISTAR_AUDITORIA = "Auditoria.Listar";

	@Id
	@Column(name = "codigo", nullable = false)
	private int codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaHora", nullable = false)
	private Date fechaHora;

	@Column(name = "ingreso", length = 50)
	private String ingreso;

	@Column(name = "origen", length = 50)
	private String origen;

	@Column(name = "navegador", length = 50)
	private String navegador;

	@Column(name = "accion", length = 20)
	private String accion;

	@Column(name = "registroRealizoAccion", length = 20)
	private String registroRealizoAccion;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	public Auditoria() {
		super();
	}

	public Auditoria(int codigo, Date fechaHora, String ingreso, String origen, String navegador, String accion,
			String registroRealizoAccion, Usuario usuario) {
		super();
		this.codigo = codigo;
		this.fechaHora = fechaHora;
		this.ingreso = ingreso;
		this.origen = origen;
		this.navegador = navegador;
		this.accion = accion;
		this.registroRealizoAccion = registroRealizoAccion;
		this.usuario = usuario;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the ingreso
	 */
	public String getIngreso() {
		return ingreso;
	}

	/**
	 * @param ingreso
	 *            the ingreso to set
	 */
	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * @return the navegador
	 */
	public String getNavegador() {
		return navegador;
	}

	/**
	 * @param navegador
	 *            the navegador to set
	 */
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion
	 *            the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Auditoria other = (Auditoria) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	/**
	 * @return the fechaHora
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the registroRealizoAccion
	 */
	public String getRegistroRealizoAccion() {
		return registroRealizoAccion;
	}

	/**
	 * @param registroRealizoAccion
	 *            the registroRealizoAccion to set
	 */
	public void setRegistroRealizoAccion(String registroRealizoAccion) {
		this.registroRealizoAccion = registroRealizoAccion;
	}

}
