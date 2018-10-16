package co.edu.eam.ingesoft.softOpe.negocio.beans;

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
	public List<Object[]> consulta2() {
		return (List<Object[]>) em
				.createNativeQuery("select sum(vehe.cantidad) cantidad,pro.nombre producto from venta_hecho vehe\r\n"
						+ "inner join producto_dimension pro on vehe.producto = pro.codigo group by pro.NOMBRE")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consulta3() {
		return (List<Object[]>) em.createNativeQuery(
				"select round(avg(vehe.totaldetalle),2) promedio,cli.nombre cliente,emp.nombre empleado from venta_hecho vehe\r\n"
						+ "inner join cliente_dimension cli on vehe.cliente = cli.codigo inner join empleado_dimension emp on vehe.empleado =\r\n"
						+ "emp.codigo group by cli.NOMBRE,emp.NOMBRE")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consulta4() {
		return (List<Object[]>) em.createNativeQuery(
				"select round(avg(vehe.totaldetalle),2) promedio,trunc(vehe.FECHAVENTA) fecha from venta_hecho vehe \r\n"
						+ "group by trunc(vehe.FECHAVENTA) order by fecha asc")
				.getResultList();
	}

}
