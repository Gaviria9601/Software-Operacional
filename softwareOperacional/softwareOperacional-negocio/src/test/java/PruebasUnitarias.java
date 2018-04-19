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
import co.edu.eam.ingesoft.softOpe.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.EmpleadoEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import junit.framework.Assert;

public class PruebasUnitarias {

	private static Log log = LogFactory.getLog(PruebasUnitarias.class);

	private static final String PERSISTENCE_UNIT = "primary";
	private static EntityManagerFactory emf;

	@BeforeClass

	public static void setUpClass() {

		log.debug("creating entity manager factory");
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

		} catch (Exception e) {
			System.out.println(" " + e);
		}

	}

	private EntityManager em;

	@Before

	public void setUpp() throws Exception {
		try {
			log.debug("creating entity manager");
			em = emf.createEntityManager();
		} catch (Exception e) {

			System.out.println(" " + e);
		}

		// cleanup();

	}

	// PRUEBA BUSCAR AREA POR IDENTIFICADOR

	@Test
	public void pruebaBuscarAreaIdentificador() {

		AreaEJB areaEJB = new AreaEJB();
		areaEJB.setEm(this.em);
		try {
			Area area = areaEJB.buscarArea(2);
			assertEquals("Contadoria", area.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	// PRUEBA BUSCAR AREA QUE NO EXISTE

	@Test
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

	// PRUEBA QUE LISTA LOS CLIENTES
	@Test
	public void pruebaListaClientes() {

		ClienteEJB cliEJB = new ClienteEJB();
		cliEJB.setEm(this.em);
		try {
			List<Cliente> cli = cliEJB.listarClientes();
			assertEquals(cli, cli);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	// PRUEBA EL REGISTRO DE UN CLIENTE
	@Test
	public void pruebaRegistrarCliente() {

		AuditoriaEJB au = new AuditoriaEJB();
		EmpleadoEJB emp = new EmpleadoEJB();
		ClienteEJB clien = new ClienteEJB();
		Cliente c = new Cliente();

		c.setNombre("Veronica");
		c.setApellido("Gómez Gil");
		c.setCedula("5687");
		c.setFechaNacimiento(au.generarFechaActual());
		c.setGenero("f");
		c.setMunicipioId(emp.buscarMunicipio(1));
		clien.crearCliente(c);
		Assert.assertNotNull(em.find(Cliente.class, c.getCedula()));
		Assert.assertEquals(null, c.getCedula());

	}

	// PRUEBA EL REGISTRO DE UN CLIENTE
	@Test
	public void pruebaRegistrarClienteSinCampo() {

		AuditoriaEJB au = new AuditoriaEJB();
		EmpleadoEJB emp = new EmpleadoEJB();
		ClienteEJB clien = new ClienteEJB();
		Cliente c = new Cliente();

		c.setNombre("");
		c.setApellido("Gómez Gil");
		c.setCedula("5687");
		c.setFechaNacimiento(au.generarFechaActual());
		c.setGenero("f");
		c.setMunicipioId(emp.buscarMunicipio(1));
		clien.crearCliente(c);
		Assert.assertNotNull(em.find(Cliente.class, c.getCedula()));
		Assert.assertEquals(null, c.getCedula());

	}
	
	 //PRUEBA PARA BUSCAR CLIENTES
	
	@Test
	public void pruebaBuscarCliente() {

		ClienteEJB cliEJB = new ClienteEJB();
		cliEJB.setEm(this.em);
		try {
			Cliente c = cliEJB.buscarCliente(7);
			assertEquals("Tomas", c.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}
	
	 //PRUEBA PARA BUSCAR CLIENTE QUE NO EXISTE
	@Test
	public void pruebaBuscarClienteNoExiste() {

		ClienteEJB cliEJB = new ClienteEJB();
		cliEJB.setEm(this.em);
		try {
			Cliente c = cliEJB.buscarCliente(15);
			assertEquals(null, c.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}
	
	
	// PRUEBA EL REGISTRO DE UN CLIENTE
		@Test
		public void pruebaEliminarCliente() {

		
			EmpleadoEJB emp = new EmpleadoEJB();
			Empleado e = new Empleado();
			e.setCodigo(2);
			emp.eliminarEmpleado(e);
			
			Assert.assertNotNull(em.find(Empleado.class,e.getCodigo()));
			Assert.assertEquals(null, e.getCodigo());
		
		}
		
		 //PRUEBA PARA BUSCAR PRODUCTO EXISTENTE
		@Test
		public void pruebaBuscarProducto() {

			ProductoEJB proEJB = new ProductoEJB();
			proEJB.setEm(this.em);
			try {
				Producto c = proEJB.buscarProduto(3);
				assertEquals(c.getCodigo(), c.getNombre());
			} catch (Exception e) {
				System.out.println(" " + e);
			}
		}
		
		 //PRUEBA PARA BUSCAR PRODUCTO  NO EXISTENTE
		@Test
		public void pruebaBuscarProductoNO() {

			ProductoEJB proEJB = new ProductoEJB();
			proEJB.setEm(this.em);
			try {
				Producto c = proEJB.buscarProduto(20);
				assertEquals(null, c.getNombre());
			} catch (Exception e) {
				System.out.println(" " + e);
			}
		}

}