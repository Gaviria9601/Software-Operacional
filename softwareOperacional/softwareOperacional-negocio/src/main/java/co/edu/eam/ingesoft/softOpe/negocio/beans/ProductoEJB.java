package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Producto;

@LocalBean
@Stateless
public class ProductoEJB {
	
	@PersistenceContext(unitName = Conexion.OPCION )
	private EntityManager em;
	
	/*
	 * 
	 * @param codigo
	 * @return
	 */
	public Producto buscarProduto(int codigo){
		return em.find(Producto.class, codigo);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Producto> listarProductos() {
		return em.createNamedQuery(Producto.LISTAR_PRODUCTOS).getResultList();
		
	}
	
	
}
