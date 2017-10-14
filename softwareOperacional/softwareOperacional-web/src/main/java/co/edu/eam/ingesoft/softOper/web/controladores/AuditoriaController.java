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

@Named("auditoriaControl")
@ViewScoped
public class AuditoriaController implements Serializable {
	
	@EJB
	private AuditoriaEJB audEJB;

	private List<Auditoria> auditorias;

	private Auditoria selectedAudi;

	private ArrayList<Auditoria> filtroAuditoria = new ArrayList<Auditoria>();

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
	}

	public List<Auditoria> getAuditorias() {
		return auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

}
