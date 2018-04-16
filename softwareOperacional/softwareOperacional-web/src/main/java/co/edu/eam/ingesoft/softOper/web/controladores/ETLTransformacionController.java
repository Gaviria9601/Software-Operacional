package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.Ventas_hechoEJB;
import co.edu.eam.ingesoft.softOper.entidades.Auditoria;
import co.edu.eam.ingesoft.softOper.entidades.venta_hecho;

/**
 * 
 * Clase encargada de la logica del controlador para el area
 * 
 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
 * @date 15/04/2018
 * @version <Numero Version>
 */
@Named("ETLtransformacionController")
@ViewScoped
public class ETLTransformacionController implements Serializable {

	List<venta_hecho> ventas;

	public List<venta_hecho> getVentas() {
		return ventas;
	}

	public void setVentas(List<venta_hecho> ventas) {
		this.ventas = ventas;
	}

	@EJB
	private Ventas_hechoEJB venHecEJB;

	@Inject
	private SessionController sesion;

	@EJB
	private AuditoriaEJB audEJB;

	@PostConstruct
	public void inicializador() {
		ventas = venHecEJB.listarVentasHecho();

	}

	/**
	 * 
	 * Metodo encargado de borrar los valores menos de 0
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void borrarMenores0() {
		venHecEJB.borrarMenores0();
		ventas = venHecEJB.listarVentasHecho();
		registrarAuditoria("Aplicación RN <0");
	}

	/**
	 * 
	 * Metodo encargado de borrar los valores menos de 20
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void borrarMenores20() {
		venHecEJB.borrarMenores20();
		ventas = venHecEJB.listarVentasHecho();
		registrarAuditoria("Aplicación RN < 20000");
	}

	/**
	 * 
	 * Metodo encargado de registrar la auditoria
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <paulaca.a8@gmail.com>
	 * @date 15/04/2018
	 * @version <Numero Version>
	 */
	public void registrarAuditoria(String accion) {
		try {
			Auditoria audi = new Auditoria();
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			audi.setAccion(accion);
			audi.setRegistroRealizoAccion("ETL");
			audi.setUsuario(sesion.getUsuario());
			audEJB.registrarAuditoria(audi, browserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
