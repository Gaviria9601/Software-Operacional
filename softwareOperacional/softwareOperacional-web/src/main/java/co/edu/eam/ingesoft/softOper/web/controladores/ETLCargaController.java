package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.EtlEJB;

@Named("ETLCargaController")
@ViewScoped
public class ETLCargaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@EJB
	private EtlEJB etljb;


	public void carga() {
		try {
			etljb.procesoCarga(etljb.listarTiempo(), etljb.listarCliente(), etljb.listarNavegador(),
					etljb.listarProducto(), etljb.listarVentaD(), etljb.listarEmpleado(), etljb.listarOrigen(),
					etljb.listarAuditoria(), etljb.listarVentaH());
			Messages.addFlashGlobalInfo("Se cargaron correctamente los Datos");
		} catch (Exception e) {
			Messages.addFlashGlobalError("Se ha generado un error en la carga de los Datos");
		}
	}

}
