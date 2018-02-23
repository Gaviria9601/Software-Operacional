package co.edu.eam.ingesoft.softOpe.negocio.beans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.origen_dimension;

public class Wiki extends ConexionWiki{

	@PersistenceContext(unitName = Conexion.OPCION)
	private EntityManager em;
	
	public void insertarUser(origen_dimension origen) {
		String consulta = "insert into origen_dimension (codigo,dispositivo)" + "values('" + origen.getCodigo() + "','"
				+ origen.getDispositivo() + "')";
		super.ejecutar(consulta);
	}
}
