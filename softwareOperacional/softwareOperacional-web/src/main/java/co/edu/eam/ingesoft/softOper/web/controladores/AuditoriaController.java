package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;

/**
 * 
 * Clase encargada de la logica del controlador para la auditoria
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@Named("auditoriaControl")
@ViewScoped
public class AuditoriaController implements Serializable {

	@EJB
	private AuditoriaEJB audEJB;

	private List<Auditoria> auditorias;

	private List<Auditoria> areas;

	private List<Auditoria> ventas;

	private List<Auditoria> clientes;

	private List<Auditoria> empleados;

	private List<Auditoria> inventarios;

	private List<Auditoria> tipoUsuarios;

	private List<Auditoria> usuarios;

	private List<Auditoria> procesosETL;

	private Auditoria selectedAudi;

	public List<Auditoria> getProcesosETL() {
		return procesosETL;
	}

	public void setProcesosETL(List<Auditoria> procesosETL) {
		this.procesosETL = procesosETL;
	}

	public List<Auditoria> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Auditoria> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Auditoria> getTipoUsuarios() {
		return tipoUsuarios;
	}

	public void setTipoUsuarios(List<Auditoria> tipoUsuarios) {
		this.tipoUsuarios = tipoUsuarios;
	}

	private ArrayList<Auditoria> filtroAuditoria = new ArrayList<Auditoria>();

	public List<Auditoria> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<Auditoria> inventarios) {
		this.inventarios = inventarios;
	}

	public List<Auditoria> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Auditoria> empleados) {
		this.empleados = empleados;
	}

	public List<Auditoria> getVentas() {
		return ventas;
	}

	public void setVentas(List<Auditoria> ventas) {
		this.ventas = ventas;
	}

	public Auditoria getSelectedAudi() {
		return selectedAudi;
	}

	public void setSelectedAudi(Auditoria selectedAudi) {
		this.selectedAudi = selectedAudi;
	}

	public ArrayList<Auditoria> getFiltroAuditoria() {
		return filtroAuditoria;
	}

	public void setFiltroAuditoria(ArrayList<Auditoria> filtroAuditoria) {
		this.filtroAuditoria = filtroAuditoria;
	}

	@PostConstruct
	public void inicializar() {
		auditorias = audEJB.listarAuditoriasIdeUsuarios();
		areas = audEJB.listarAuditoriasArea();
		ventas = audEJB.listarAuditoriaVentas();
		clientes = audEJB.listarAuditoriaClientes();
		empleados = audEJB.listarAuditoriaEmpleados();
		inventarios = audEJB.listarAuditoriaInventarios();
		tipoUsuarios = audEJB.listarAuditoriaTiposUsuarios();
		usuarios = audEJB.listarAuditoriaUsuarios();
		procesosETL = audEJB.listarAuditoriaProcesosETL();

	}

	public List<Auditoria> getAreas() {
		return areas;
	}

	public void setAreas(List<Auditoria> areas) {
		this.areas = areas;
	}

	public List<Auditoria> getAuditorias() {
		return auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	public AuditoriaEJB getAudEJB() {
		return audEJB;
	}

	public void setAudEJB(AuditoriaEJB audEJB) {
		this.audEJB = audEJB;
	}

	public List<Auditoria> getClientes() {
		return clientes;
	}

	public void setClientes(List<Auditoria> clientes) {
		this.clientes = clientes;
	}

}
