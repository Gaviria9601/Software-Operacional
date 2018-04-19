import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AreaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Area;

public class PruebasUnitarias {


	private static Log log = LogFactory.getLog(PruebasUnitarias.class);
	private static final String PERSISTENCE_UNIT = "primary";
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass

	public static void setUpClass() {

		log.debug("creating entity manager factory");
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

		} catch (Exception e) {
			System.out.println(" " + e);
		}

	}

	@Before

	public void setUpp() throws Exception {
		log.debug("creating entity manager");
		em = emf.createEntityManager();
		// cleanup();

	}

	@Before

	public void setUp() throws Exception {
		em = emf.createEntityManager();
		// cleanup();

		
		
		
	}

	@Test
	public void primerPrueba() {
		AreaEJB areaEJB = new AreaEJB();
		areaEJB.setEm(this.em);
		try {
			Area area = areaEJB.buscarArea(1);

			// List<Area> areas = areaEJB.listarArea();
			// assertEquals(null, areas);
			assertEquals("Tecnologia", area.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	// PRUEBA BUSCAR AREA POR IDENTIFICADOR 
	/**@Test
	public void pruebaBuscarAreaIdentificador() {

		AreaEJB areaEJB = new AreaEJB();
		areaEJB.setEm(this.em);
		try {
			Area area = areaEJB.buscarArea(2);
			assertEquals("Contadoria", area.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}**/

	// PRUEBA BUSCAR AREA QUE NO EXISTE 
	/**@Test
	public void pruebaBuscarAreaNoExiste() {

		AreaEJB areaEJB = new AreaEJB();
		areaEJB.setEm(this.em);
		try {
			Area area = areaEJB.buscarArea(7);
			assertEquals(null, area.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	// PRUEBA PARA AGREGAR UN PRODUCTO A LA VENTA
	/**@Test
	public void pruebaBuscarArea() {
         Venta venta = new Venta();
         venta.setCodigo(3);
         
         Producto producto = new Producto();
         producto.setCodigo(37);
         
         ProductoVenta vp = new ProductoVenta();
         
         ProductoVentaEJB  ventasPro =  new ProductoVentaEJB();
		 ventasPro.setEm(this.em);
		
		try {
			
			ventasPro.agregarProductoVenta(producto, venta, 5);
			vp.setVenta_codigo(venta);
			vp.setProducto_codigo(producto_codigo);
			
			
			
			
			assertEquals("Tecnologia", area.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}**/
	
	 // PRUEBA LISTAR AREAS 
	/**@Test
	public void pruebaListaAreas() {

		AreaEJB areaEJB = new AreaEJB();
		areaEJB.setEm(this.em);
		try {
		  List<Area> areas = areaEJB.listarArea();
	      assertEquals(areas, areas);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}**/
	
	/**@Test
	public void pruebaListaClientes() {

		ClienteEJB cliEJB = new ClienteEJB();
		cliEJB.setEm(this.em);
		try {
		  List<Cliente> cli = cliEJB.listarClientes();
	      assertEquals(cli, cli);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}**/

	@After
	public void tearDownn() throws Exception {
		try {

			log.debug("tearDown() started, em=" + em);
			em.getTransaction().begin();
			em.flush();
			// logAutos();
			em.getTransaction().commit();
			em.close();
			log.debug("tearDown() complete, em=" + em);

		} catch (Exception ex) {
			log.fatal("tearDown failed", ex);
			throw ex;

		}

	}

	@AfterClass
	public static void tearDownClass() {
		log.debug("closing entity manager factory");
		emf.close();

	}

	/*
	 * public void cleanup() {
	 * 
	 * em.getTransaction().begin();
	 * 
	 * Query query = em.createNativeQuery("delete from EM_AUTO");
	 * 
	 * int rows = query.executeUpdate();
	 * 
	 * em.getTransaction().commit();
	 * 
	 * log.info("removed " + rows + " rows");
	 * 
	 * }
	 **/

}

