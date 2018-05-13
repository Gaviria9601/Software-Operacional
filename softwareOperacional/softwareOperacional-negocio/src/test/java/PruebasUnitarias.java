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
import co.edu.eam.ingesoft.softOpe.negocio.beans.TipoUsuarioEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.VentaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Area;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Empleado;
import co.edu.eam.ingesoft.softOper.entidades.Producto;
import co.edu.eam.ingesoft.softOper.entidades.TipoUsuario;
import co.edu.eam.ingesoft.softOper.entidades.Usuario;
import co.edu.eam.ingesoft.softOper.entidades.Venta;
import junit.framework.Assert;

public class PruebasUnitarias {

	private static Log log = LogFactory.getLog(PruebasUnitarias.class);

	private static final String PERSISTENCE_UNIT = "primary";
	private static EntityManagerFactory emf;
	private List<Cliente> clientes;
	private List<Area> areas;
	private List<Producto> productos;
	private List<Venta> ventas;
	private List<TipoUsuario> tiposUsu;

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

	}

	/**
	 * 
	 * PRUEBA BUSCAR AREA POR IDENTIFICADOR
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */

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

	/**
	 * 
	 * PRUEBA BUSCAR AREA QUE NO EXISTE
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */

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

	/**
	 * 
	 * PRUEBA QUE LISTA LOS CLIENTES
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaListaClientes() {

		ClienteEJB cliEJB = new ClienteEJB();
		cliEJB.setEm(this.em);
		try {
			List<Cliente> cli = cliEJB.listarClientes();
			assertEquals(clientes, cli);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	/**
	 * 
	 * PRUEBA BUSCAR CLIENTES EXISTENTES
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */

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

	/**
	 * 
	 * PRUEBA BUSCAR CLIENTE QUE NO EXISTE
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
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

	/**
	 * 
	 * PRUEBA BUSCAR PRODUCTO EXISTENTE
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaBuscarProducto() {

		ProductoEJB proEJB = new ProductoEJB();
		proEJB.setEm(this.em);
		try {
			Producto c = proEJB.buscarProduto(3);
			assertEquals("fifa", c.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	/**
	 * 
	 * PRUEBA BUSCAR CLIENTE QUE NO EXISTE
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaBuscarProductoNoExiste() {

		ProductoEJB proEJB = new ProductoEJB();
		proEJB.setEm(this.em);
		try {
			Producto c = proEJB.buscarProduto(20);
			assertEquals(null, c.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	/**
	 * 
	 * PRUEBA QUE LISTA LAS AREAS
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaListaAreas() {

		AreaEJB areaEJB = new AreaEJB();
		areaEJB.setEm(this.em);
		try {
			List<Area> a = areaEJB.listarArea();
			assertEquals(areas, a);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	/**
	 * 
	 * PRUEBA QUE LISTA LOS PRODUCTOS
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaListaProductos() {

		ProductoEJB proEJB = new ProductoEJB();
		proEJB.setEm(this.em);
		try {
			List<Producto> pro = proEJB.listarProductos();

			assertEquals(productos, pro);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	/**
	 * 
	 * PRUEBA QUE LISTA LAS VENTAS
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaListarVentas() {

		VentaEJB veEJB = new VentaEJB();
		veEJB.setEm(this.em);
		try {
			List<Venta> v = veEJB.listarVentas();

			assertEquals(ventas, v);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	/**
	 * 
	 * PRUEBA QUE LISTA LOS TIPOS DE USUARIOS
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaListarTiposUsuarios() {

		TipoUsuarioEJB tipoEJB = new TipoUsuarioEJB();
		tipoEJB.setEm(this.em);
		try {
			List<TipoUsuario> tu = tipoEJB.listarTipoUsuario();

			assertEquals(tiposUsu, tu);
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}

	/**
	 * 
	 * PRUEBA QUE BUSCA EMPLEADOS
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */

	@Test
	public void pruebaBuscarEmpleado() {

		EmpleadoEJB emEJB = new EmpleadoEJB();
		emEJB.setEm(this.em);
		try {
			Empleado e = emEJB.buscarEmpleado2(5);
			assertEquals("santiago", e.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}
	/**
	 * 
	 * PRUEBA QUE BUSCA EMPLEADOS QUE NO EXISTEN
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */
	@Test
	public void pruebaBuscarEmpleadoNoExiste() {

		EmpleadoEJB emEJB = new EmpleadoEJB();
		emEJB.setEm(this.em);
		try {
			Empleado e = emEJB.buscarEmpleado2(25);
			assertEquals(null, e.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}
	
	/**
	 * 
	 * PRUEBA QUE BUSCA USUARIOS
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */

	@Test
	public void pruebaBuscarUsuarios() {

		EmpleadoEJB emEJB = new EmpleadoEJB();
		emEJB.setEm(this.em);
		try {
			Usuario e = emEJB.buscarUsuario(1);
			assertEquals("Paula", e.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}
	/**
	 * 
	 * PRUEBA QUE BUSCA USUARIOS QUE NO EXISTE
	 * 
	 * @author <Paula Castaño Aristizabal> Email: <Paulaca.a8@gmail.com>
	 * @date 12/05/2018
	 * @version <>
	 */

	@Test
	public void pruebaBuscarUsuariosNoExiste() {

		EmpleadoEJB emEJB = new EmpleadoEJB();
		emEJB.setEm(this.em);
		try {
			Usuario e = emEJB.buscarUsuario(40);
			assertEquals(null, e.getNombre());
		} catch (Exception e) {
			System.out.println(" " + e);
		}
	}


}