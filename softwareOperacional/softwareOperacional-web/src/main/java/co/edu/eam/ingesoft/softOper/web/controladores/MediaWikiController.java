package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.EtlEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.MediaWikiEJB;
import co.edu.eam.ingesoft.softOper.entidades.AuditoriaWikieamHecho;

/**
 * Clase encargada..
 * 
 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
 * @date 24/02/2018
 * @version 1.0
 *
 */
@Named("MediaWikiController")
@ViewScoped
public class MediaWikiController implements Serializable {

	/**
	 * Serial de la clase
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MediaWikiEJB mediaWikiEJB;

	@EJB
	private EtlEJB etlEJB;

	private List<AuditoriaWikieamHecho> auditorias;

	@PostConstruct
	public void inicializador() {
		listarAuditoriaHecho();
	}

	/**
	 * Metodo que se encarga de cargar los datos a las tabla de hechos y
	 * dimensiones.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	public void cargar() {
		try {
			mediaWikiEJB.cargarDatos(mediaWikiEJB.listarUsuariosDimension(), mediaWikiEJB.listarPaginasDimension(),
					mediaWikiEJB.listarCambiosDimension(), mediaWikiEJB.listarAuditoria());
			Messages.addFlashGlobalInfo("Se cargaron correctamente los Datos");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Se ha presentado un error cargando los datos");
		}
	}

	/**
	 * Metodo encargado de extraer los datos de la base de datos de wikieam.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	public void extraer() {
		try {
			mediaWikiEJB.extraerDatos();
			listarAuditoriaHecho();
			Messages.addFlashGlobalInfo("Se ha extraido correctamente los Datos hasta la fecha");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Ya se han extraido el total de los Datos");
		}
	}

	/**
	 * Metodo encargado de listar las resultados de la tabla de hecho.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	private void listarAuditoriaHecho() {
		auditorias = mediaWikiEJB.listarAuditoria();
	}

	/**
	 * @return the auditorias
	 */
	public List<AuditoriaWikieamHecho> getAuditorias() {
		return auditorias;
	}

	/**
	 * @param auditorias
	 *            the auditorias to set
	 */
	public void setAuditorias(List<AuditoriaWikieamHecho> auditorias) {
		this.auditorias = auditorias;
	}

}
