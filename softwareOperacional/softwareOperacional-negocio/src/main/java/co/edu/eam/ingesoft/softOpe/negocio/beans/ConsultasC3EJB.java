package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@LocalBean
@Stateless
public class ConsultasC3EJB {

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Object[]> consulta1() {
		return (List<Object[]>) em
				.createNativeQuery(
						"select avg(vehe.totaldetalle) promedio,pro.nombre producto from venta_hecho vehe\r\n"
								+ "inner join producto_dimension pro on vehe.producto = pro.codigo group by pro.NOMBRE")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultaFiltro1(Long produc, Long client) {
		List<Object[]> retorno = new ArrayList<Object[]>();
		if (produc != -1 && client != -1) {
			retorno = em
					.createNativeQuery(
							"select avg(vehe.totaldetalle) promedio,pro.nombre producto from venta_hecho vehe\r\n"
									+ "inner join producto_dimension pro on vehe.producto = pro.codigo where "
									+ "vehe.producto = ? and vehe.cliente = ? group by pro.NOMBRE")
					.setParameter(1, produc).setParameter(2, client).getResultList();
		} else if (produc != -1) {
			retorno = em.createNativeQuery(
					"select avg(vehe.totaldetalle) promedio,pro.nombre producto from venta_hecho vehe\r\n"
							+ "inner join producto_dimension pro on vehe.producto = pro.codigo where "
							+ "vehe.producto = ? group by pro.NOMBRE")
					.setParameter(1, produc).getResultList();
		} else if (client != 1) {
			retorno = em.createNativeQuery(
					"select avg(vehe.totaldetalle) promedio,pro.nombre producto from venta_hecho vehe\r\n"
							+ "inner join producto_dimension pro on vehe.producto = pro.codigo where "
							+ "vehe.cliente = ? group by pro.NOMBRE")
					.setParameter(1, client).getResultList();
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consulta2() {
		return (List<Object[]>) em
				.createNativeQuery("select sum(vehe.cantidad) cantidad,pro.nombre producto from venta_hecho vehe\r\n"
						+ "inner join producto_dimension pro on vehe.producto = pro.codigo group by pro.NOMBRE")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultaFiltro2(Long produc, Long client) {
		List<Object[]> retorno = new ArrayList<Object[]>();
		if (produc != -1 && client != -1) {
			retorno = em
					.createNativeQuery("select sum(vehe.cantidad) cantidad,pro.nombre producto from venta_hecho vehe\r\n"
									+ "inner join producto_dimension pro on vehe.producto = pro.codigo where "
									+ "vehe.producto = ? and vehe.cliente = ? group by pro.NOMBRE")
					.setParameter(1, produc).setParameter(2, client).getResultList();
		} else if (produc != -1) {
			retorno = em.createNativeQuery(
					"select sum(vehe.cantidad) cantidad,pro.nombre producto from venta_hecho vehe\r\n"
							+ "inner join producto_dimension pro on vehe.producto = pro.codigo where "
							+ "vehe.producto = ? group by pro.NOMBRE")
					.setParameter(1, produc).getResultList();
		} else if (client != 1) {
			retorno = em.createNativeQuery(
					"select sum(vehe.cantidad) cantidad,pro.nombre producto from venta_hecho vehe\r\n"
							+ "inner join producto_dimension pro on vehe.producto = pro.codigo where "
							+ "vehe.cliente = ? group by pro.NOMBRE")
					.setParameter(1, client).getResultList();
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consulta3() {
		return (List<Object[]>) em.createNativeQuery(
				"select round(avg(vehe.totaldetalle),2) promedio,cli.nombre cliente from venta_hecho vehe\r\n"
						+ "inner join cliente_dimension cli on vehe.cliente = cli.codigo \r\n" + "group by cli.NOMBRE")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultaFiltro3(Long produc, Long client) {
		List<Object[]> retorno = new ArrayList<Object[]>();
		if (produc != -1 && client != -1) {
			retorno = em
					.createNativeQuery(
							"select round(avg(vehe.totaldetalle),2) promedio,cli.nombre cliente from venta_hecho vehe\r\n"
									+ "inner join cliente_dimension cli on vehe.cliente = cli.codigo where "
									+ "vehe.producto = ? and vehe.cliente = ? group by cli.NOMBRE")
					.setParameter(1, produc).setParameter(2, client).getResultList();
		} else if (produc != -1) {
			retorno = em.createNativeQuery(
					"select round(avg(vehe.totaldetalle),2) promedio,cli.nombre cliente from venta_hecho vehe\r\n"
							+ "inner join cliente_dimension cli on vehe.cliente = cli.codigo where "
							+ "vehe.producto = ? group by cli.NOMBRE")
					.setParameter(1, produc).getResultList();
		} else if (client != 1) {
			retorno = em.createNativeQuery(
					"select round(avg(vehe.totaldetalle),2) promedio,cli.nombre cliente from venta_hecho vehe\r\n"
							+ "inner join cliente_dimension cli on vehe.cliente = cli.codigo  where "
							+ "vehe.cliente = ? group by cli.NOMBRE")
					.setParameter(1, client).getResultList();
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consulta4() {
		return (List<Object[]>) em.createNativeQuery(
				"select round(avg(vehe.totaldetalle),2) promedio,trunc(vehe.FECHAVENTA) fecha from venta_hecho vehe \r\n"
						+ "group by trunc(vehe.FECHAVENTA) order by fecha asc")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultaFiltro4(Long produc, Long client) {
		List<Object[]> retorno = new ArrayList<Object[]>();
		if (produc != -1 && client != -1) {
			retorno = em
					.createNativeQuery(
							"select round(avg(vehe.totaldetalle),2) promedio,trunc(vehe.FECHAVENTA) fecha from venta_hecho vehe\r\n"
									+ "where vehe.producto = ? and vehe.cliente = ? group by trunc(vehe.FECHAVENTA) order by fecha asc")
					.setParameter(1, produc).setParameter(2, client).getResultList();
		} else if (produc != -1) {
			retorno = em.createNativeQuery(
					"select round(avg(vehe.totaldetalle),2) promedio,trunc(vehe.FECHAVENTA) fecha from venta_hecho vehe\r\n"
							+ "where vehe.producto = ? group by trunc(vehe.FECHAVENTA) order by fecha asc")
					.setParameter(1, produc).getResultList();
		} else if (client != 1) {
			retorno = em.createNativeQuery(
					"select round(avg(vehe.totaldetalle),2) promedio,trunc(vehe.FECHAVENTA) fecha from venta_hecho vehe\r\n"
							+ "where vehe.cliente = ? group by trunc(vehe.FECHAVENTA) order by fecha asc")
					.setParameter(1, client).getResultList();
		}

		return retorno;
	}

}
