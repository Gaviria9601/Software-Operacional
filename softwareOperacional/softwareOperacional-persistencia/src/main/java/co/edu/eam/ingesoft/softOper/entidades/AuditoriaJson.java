package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

public class AuditoriaJson implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String fechaHora;
	
	private String sistemaOperativo;

	private String navegador;
	
	private String accion;
	
	private String registroRealizoAccion;
	
	
	public AuditoriaJson() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param fechaHora
	 * @param sistemaOperativo
	 * @param navegador
	 * @param accion
	 * @param registroRealizoAccion
	 */
	public AuditoriaJson(String fechaHora, String sistemaOperativo, String navegador, String accion,
			String registroRealizoAccion) {
		super();
		this.fechaHora = fechaHora;
		this.sistemaOperativo = sistemaOperativo;
		this.navegador = navegador;
		this.accion = accion;
		this.registroRealizoAccion = registroRealizoAccion;
	}



	/**
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the sistemaOperativo
	 */
	public String getSistemaOperativo() {
		return sistemaOperativo;
	}

	/**
	 * @param sistemaOperativo the sistemaOperativo to set
	 */
	public void setSistemaOperativo(String sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	/**
	 * @return the navegador
	 */
	public String getNavegador() {
		return navegador;
	}

	/**
	 * @param navegador the navegador to set
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
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the registroRealizoAccion
	 */
	public String getRegistroRealizoAccion() {
		return registroRealizoAccion;
	}

	/**
	 * @param registroRealizoAccion the registroRealizoAccion to set
	 */
	public void setRegistroRealizoAccion(String registroRealizoAccion) {
		this.registroRealizoAccion = registroRealizoAccion;
	}
	
	

}
