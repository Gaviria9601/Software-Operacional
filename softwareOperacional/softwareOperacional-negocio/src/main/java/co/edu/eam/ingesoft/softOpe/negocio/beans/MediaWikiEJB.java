/**
 * 
 */
package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.AuditoriaWikieamHecho;
import co.edu.eam.ingesoft.softOper.entidades.CambiosDimensionWikieam;
import co.edu.eam.ingesoft.softOper.entidades.PaginaDimensionWikieam;
import co.edu.eam.ingesoft.softOper.entidades.UsuarioDimensionWikieam;

/**
 * Clase encargada de la logica del negocio para el EJB de Mediawiki.
 *
 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
 * @date 24/02/2018
 * @version <Version de la clase>
 *
 */
@LocalBean
@Stateless
public class MediaWikiEJB extends ConexionWikieam {

	/**
	 * Loger de la clase.
	 */
	private Logger logger = Logger.getLogger(MediaWikiEJB.class.getName());

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	@EJB
	private ConexionETL conexionETL;

	/**
	 * Metodo encargado de extraer los datos de la bd de wikieam.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	public void extraerDatos() {
		try {

			// Se realiza la consulta a la bd de wikieam para obtener los usuarios.
			extraerDatosDimensionUsuario();

			// Se realiza la consulta a la bd de wikieam para obtener las paginas.
			extraerDatosDimensionPaginas();

			// Se realiza la consulta a la bd de wikieam para obtener los cambios
			// realizados.
			extraerDatosDimensionCambios();

			// Se realiza la consulta a la bd de wikieam para obtener la auditoria
			StringBuilder consultaAudi = new StringBuilder();
			consultaAudi.append("SELECT rc_id,rc_user,rc_cur_id FROM recentchanges");
			ResultSet resultAudi = super.ejecutarRetorno(consultaAudi.toString());
			while (resultAudi.next()) {
				AuditoriaWikieamHecho adw = new AuditoriaWikieamHecho();

				Integer idCambio = resultAudi.getInt(1);
				if (idCambio != null) {
					adw.setCambio(em.find(CambiosDimensionWikieam.class, idCambio));
					adw.setCodigo(idCambio);
				}
				Integer idUsuario = resultAudi.getInt(2);
				if (idUsuario != null) {
					adw.setUsuario(em.find(UsuarioDimensionWikieam.class, idUsuario));
				}
				Integer idPagina = resultAudi.getInt(3);
				if (idPagina != null) {
					adw.setPagina(em.find(PaginaDimensionWikieam.class, idPagina));
				}
				AuditoriaWikieamHecho adwTemp = em.find(AuditoriaWikieamHecho.class, adw.getCodigo());
				if (adwTemp != null && adwTemp.getCodigo() != null) {
					em.merge(adw);
				} else {
					em.persist(adw);
				}
			}

		} catch (Exception e) {
			logger.info("MediaWikiEJB: extraerDatos() - Exception General: " + e.getMessage());
		}

	}

	/**
	 * Metodo encargado de extraer los datos para la dimension de usuarios.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	private void extraerDatosDimensionUsuario() {

		try {

			StringBuilder consulta = new StringBuilder();
			consulta.append("select user_id,user_name, user_real_name from user");

			ResultSet result = super.ejecutarRetorno(consulta.toString());

			while (result.next()) {
				UsuarioDimensionWikieam udw = new UsuarioDimensionWikieam();
				Integer id = result.getInt(1);
				if (id != null) {
					udw.setCodigo(id);
				}
				String nombreUsu = result.getString(2);
				if (nombreUsu != null) {
					udw.setNombreUsuario(nombreUsu);
				}
				String nombre = result.getString(3);
				if (nombre != null) {
					udw.setNombre(nombre);
				}
				UsuarioDimensionWikieam udwTemp = em.find(UsuarioDimensionWikieam.class, udw.getCodigo());
				if (udwTemp != null && udwTemp.getCodigo() != null) {
					em.merge(udw);
				} else {
					em.persist(udw);
				}

			}
		} catch (SQLException e) {
			logger.info("MediaWikiEJB: extraerDatosDimensionUsuario() - Exception General: " + e.getMessage());
		}

	}

	/**
	 * Metodo encargado de extraer los datos para la dimension de paginas
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	private void extraerDatosDimensionPaginas() {
		try {

			StringBuilder consultaPag = new StringBuilder();
			consultaPag.append("select page_id,page_title from page");

			ResultSet resultPag = super.ejecutarRetorno(consultaPag.toString());

			while (resultPag.next()) {
				PaginaDimensionWikieam pdw = new PaginaDimensionWikieam();
				Integer id = resultPag.getInt(1);
				if (id != null) {
					pdw.setCodigo(id);
				}
				String tituloPagina = resultPag.getString(2);
				if (tituloPagina != null) {
					pdw.setTituloPagina(tituloPagina);
				}
				PaginaDimensionWikieam pdwTemp = em.find(PaginaDimensionWikieam.class, pdw.getCodigo());
				if (pdwTemp != null && pdwTemp.getCodigo() != null) {
					em.merge(pdw);
				} else {
					em.persist(pdw);
				}
			}
		} catch (SQLException e) {
			logger.info("MediaWikiEJB: extraerDatosDimensionPaginas() - Exception General: " + e.getMessage());
		}

	}

	/**
	 * Metodo encargado de extraer los datos para la dimension de cambios.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	private void extraerDatosDimensionCambios() {
		try {

			StringBuilder consultaCambios = new StringBuilder();
			consultaCambios.append(
					"SELECT rc_id as id,(select DATE_FORMAT(rc_timestamp, '%Y-%m-%d %H:%i:%s') from recentchanges where ");
			consultaCambios.append(
					"rc_old_len=0 and rc_id = id) fechaCreacion,(select DATE_FORMAT(rc_timestamp, '%Y-%m-%d %H:%i:%s') ");
			consultaCambios.append("from recentchanges where ");
			consultaCambios.append("rc_old_len>0 and rc_id = id) fechaEdicion,");
			consultaCambios.append("rc_old_len,rc_new_len FROM wikieam.recentchanges ");

			ResultSet resultCambios = super.ejecutarRetorno(consultaCambios.toString());

			while (resultCambios.next()) {
				CambiosDimensionWikieam cdw = new CambiosDimensionWikieam();

				Integer id = resultCambios.getInt(1);
				if (id != null) {
					cdw.setCodigo(id);
				}
				Date fechaC = resultCambios.getDate(2);
				if (fechaC != null) {
					cdw.setFechaC(fechaC);
				}
				Date fechaE = resultCambios.getDate(3);
				if (fechaE != null) {
					cdw.setFechaE(fechaE);
				}
				int numeroLineasC = resultCambios.getInt(4);
				if (numeroLineasC > -1) {
					cdw.setNumeroLineasC(numeroLineasC);
				}
				int numeroLineasE = resultCambios.getInt(5);
				if (numeroLineasE > -1) {
					cdw.setNumeroLineasE(numeroLineasE);
				}
				CambiosDimensionWikieam cdwTemp = em.find(CambiosDimensionWikieam.class, cdw.getCodigo());
				if (cdwTemp != null && cdwTemp.getCodigo() != null) {
					em.merge(cdw);
				} else {
					em.persist(cdw);
				}
			}
		} catch (SQLException e) {
			logger.info("MediaWikiEJB: extraerDatosDimensionCambios() - Exception General: " + e.getMessage());
		}
	}

	/**
	 * Metodo encargado de cargar los datos hacia la bd de etl.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	public void cargarDatos(List<UsuarioDimensionWikieam> usuarios, List<PaginaDimensionWikieam> paginas,
			List<CambiosDimensionWikieam> cambios, List<AuditoriaWikieamHecho> auditorias) {
		try {
			for (UsuarioDimensionWikieam usuarioDimensionWikieam : usuarios) {
				ResultSet res = conexionETL
						.ejecutarRetorno("select codigo from usuario_dimension_wikieam where codigo = "
								+ usuarioDimensionWikieam.getCodigo());
				Integer codigo = -1;
				while (res.next()) {
					codigo = res.getInt(1);
				}
				StringBuilder sql = new StringBuilder();
				if (codigo != -1) {
					sql.append("update usuario_dimension_wikieam set nombreusuario='")
							.append(usuarioDimensionWikieam.getNombreUsuario());
					sql.append("',nombre='").append(usuarioDimensionWikieam.getNombre());
					sql.append(" where codigo = " + codigo);
				} else {
					sql.append("insert into usuario_dimension_wikieam(codigo,nombreusuario,nombre) values(");
					sql.append(usuarioDimensionWikieam.getCodigo());
					sql.append(",'").append(usuarioDimensionWikieam.getNombreUsuario()).append("'");
					sql.append(",'").append(usuarioDimensionWikieam.getNombre()).append("')");
					System.out.println("Consulta " + sql.toString());
					conexionETL.ejecutarRetorno(sql.toString());
				}

			}

			for (PaginaDimensionWikieam paginaDimensionWikieam : paginas) {
				ResultSet res = conexionETL
						.ejecutarRetorno("select codigo from pagina_dimension_wikieam where codigo = "
								+ paginaDimensionWikieam.getCodigo());
				Integer codigo = -1;
				while (res.next()) {
					codigo = res.getInt(1);
				}
				StringBuilder sql = new StringBuilder();
				if (codigo != -1) {
					sql.append("update pagina_dimension_wikieam set titulopagina='")
							.append(paginaDimensionWikieam.getTituloPagina());
					sql.append(" where codigo = " + codigo);
				} else {
					sql.append("insert into pagina_dimension_wikieam(codigo,titulopagina) values(");
					sql.append(paginaDimensionWikieam.getCodigo());
					sql.append(",'").append(paginaDimensionWikieam.getTituloPagina()).append("')");
					System.out.println("Consulta " + sql.toString());
					conexionETL.ejecutar(sql.toString());
				}

			}

			for (CambiosDimensionWikieam cambiosDimensionWikieam : cambios) {
				ResultSet res = conexionETL.ejecutarRetorno("select codigo from cambios_dimension_wikieam where codigo="
						+ cambiosDimensionWikieam.getCodigo());
				Integer codigo = -1;
				while (res.next()) {
					codigo = res.getInt(1);
				}
				StringBuilder sql = new StringBuilder();
				if (codigo != -1) {
					sql.append("update cambios_dimension_wikieam set fechac='")
							.append(cambiosDimensionWikieam.getFechaC());
					sql.append("',fechae='").append(cambiosDimensionWikieam.getFechaE());
					sql.append(",numerolineasc='").append(cambiosDimensionWikieam.getNumeroLineasC());
					sql.append(",numerolineasc='").append(cambiosDimensionWikieam.getNumeroLineasE());
					sql.append(" where codigo = " + codigo);
				} else {
					sql.append(
							"insert into cambios_dimension_wikieam(codigo,fechac,fechae,numerolineasc,numerolinease) values(");
					sql.append(cambiosDimensionWikieam.getCodigo());
					if (cambiosDimensionWikieam.getFechaC() != null) {
						sql.append(",'").append(cambiosDimensionWikieam.getFechaC()).append("'");
					} else {
						sql.append(",").append(cambiosDimensionWikieam.getFechaC());
					}
					if (cambiosDimensionWikieam.getFechaE() != null) {
						sql.append(",'").append(cambiosDimensionWikieam.getFechaE()).append("'");
					} else {
						sql.append(",").append(cambiosDimensionWikieam.getFechaE());
					}
					sql.append(",").append(cambiosDimensionWikieam.getNumeroLineasC());
					sql.append(",").append(cambiosDimensionWikieam.getNumeroLineasE()).append(")");
					System.out.println("Consulta " + sql.toString());
					conexionETL.ejecutar(sql.toString());
				}

			}
			for (AuditoriaWikieamHecho auditoriaWikieamHecho : auditorias) {
				ResultSet res = conexionETL.ejecutarRetorno(
						"select codigo from auditoria_wikieam_hecho where codigo=" + auditoriaWikieamHecho.getCodigo());
				Integer codigo = -1;
				while (res.next()) {
					codigo = res.getInt(1);
				}
				StringBuilder sql = new StringBuilder();
				if (codigo != -1) {
					sql.append("update usuario_dimension_wikieam set cambiosrecientes=")
							.append(auditoriaWikieamHecho.getCambio().getCodigo());
					sql.append(",usuario=").append(auditoriaWikieamHecho.getUsuario().getCodigo());
					sql.append(",pagina=").append(auditoriaWikieamHecho.getPagina().getCodigo());
					sql.append(" where codigo = " + codigo);
				} else {
					sql.append("insert into auditoria_wikieam_hecho(codigo,cambiosrecientes,usuario,pagina) values(");
					sql.append(auditoriaWikieamHecho.getCodigo()).append(",");
					sql.append(auditoriaWikieamHecho.getCambio().getCodigo()).append(",");
					sql.append(auditoriaWikieamHecho.getUsuario().getCodigo()).append(",");
					sql.append(auditoriaWikieamHecho.getPagina().getCodigo()).append(")");
					System.out.println("Consulta " + sql.toString());
					conexionETL.ejecutar(sql.toString());
				}

			}
		} catch (

		SQLException e) {
			logger.info("MediaWikiEJB: cargarDatos() - SQLException: " + e.getMessage());
		}
	}

	/**
	 * Metodo encargado de listar la auditoria de wikieam.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<AuditoriaWikieamHecho> listarAuditoria() {
		return em.createNamedQuery(AuditoriaWikieamHecho.LISTAR_AUDITORIA_WIKI).getResultList();
	}

	/**
	 * Metodo encargado de listar los elementos de UsuarioDimensionWikieam.
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioDimensionWikieam> listarUsuariosDimension() {
		return em.createNamedQuery(UsuarioDimensionWikieam.LISTAR_USW).getResultList();
	}

	/**
	 * Metodo encargado de listar los elementos de PaginaDimensionWikieam
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<PaginaDimensionWikieam> listarPaginasDimension() {
		return em.createNamedQuery(PaginaDimensionWikieam.LISTAR_PDW).getResultList();
	}

	/**
	 * Metodo encargado de listar los elementos de CambiosDimensionWikieam
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<CambiosDimensionWikieam> listarCambiosDimension() {
		return em.createNamedQuery(CambiosDimensionWikieam.LISTAR_CDW).getResultList();
	}

}
