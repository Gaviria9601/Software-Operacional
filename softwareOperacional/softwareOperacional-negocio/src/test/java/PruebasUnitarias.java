
import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.junit.Test;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AreaEJB;


public class PruebasUnitarias {

	//@Inject
	@EJB
    AreaEJB ar;
	
	
	
	
	@Test
	public void pruebaBuscarAreaNombre() {
		
	  assertEquals("contaduria", ar.buscarA("contaduria"));
	}

	
	
}
