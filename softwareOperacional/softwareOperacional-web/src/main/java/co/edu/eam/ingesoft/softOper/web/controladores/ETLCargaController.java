package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.Auditoria_hechoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EtlEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Ventas_hechoEJB;
import co.edu.eam.ingesoft.softOper.entidades.auditoria_hecho;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

/**
 * 
 * Clase encargada de la logica del controlador para el proceso de ETL
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version 1.0
 */
@Named("ETLCargaController")
@ViewScoped
public class ETLCargaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<auditoria_hecho> auditorias;

	private List<venta_hecho> ventas;

	/**
	 * 
	 */
	@EJB
	private EtlEJB etljb;

	@EJB
	private Ventas_hechoEJB venEJB;

	@EJB
	private Auditoria_hechoEJB audEJB;

	@PostConstruct
	public void inicializar() {
		listarAuditorias();
		listarVentas();
	}

	/**
	 * 
	 * Metodo que se encarga del realizar la carga en el proceso de ETL
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version 1.0
	 */
	public void carga() {
		try {

			etljb.eliminarRegistros();

			etljb.procesoCarga(etljb.listarTiempo(), etljb.listarCliente(), etljb.listarNavegador(),
					etljb.listarProducto(), etljb.listarVentaD(), etljb.listarEmpleado(), etljb.listarOrigen(),
					etljb.listarAuditoria(), etljb.listarVentaH());
			Messages.addFlashGlobalInfo("Se cargaron correctamente los Datos");
			listarAuditorias();
			listarVentas();
		} catch (Exception e) {
			Messages.addFlashGlobalError("Se ha generado un error en la carga de los Datos");
		}
	}

	/**
	 * 
	 * Metodo que se encarga listar las auditorias
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	private void listarAuditorias() {
		auditorias = audEJB.listarAuditoriasHecho();

	}

	/**
	 * 
	 * Metodo que se encarga listar las ventas
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	private void listarVentas() {
		ventas = venEJB.listarVentasHecho();
	}

	/**
	 * 
	 * Metodo que se encarga listar las las auditorias hecho *
	 * 
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public List<auditoria_hecho> getAuditorias() {
		return auditorias;
	}

	/**
	 * @param auditorias
	 *            the auditorias to set
	 */
	public void setAuditorias(List<auditoria_hecho> auditorias) {
		this.auditorias = auditorias;
	}

	/**
	 * @return the ventas
	 */
	public List<venta_hecho> getVentas() {
		return ventas;
	}

	/**
	 * @param ventas
	 *            the ventas to set
	 */
	public void setVentas(List<venta_hecho> ventas) {
		this.ventas = ventas;
	}

}
