package co.edu.eam.ingesoft.softOpe.negocio.excepciones;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ExcepcionNegocio extends RuntimeException{

	public ExcepcionNegocio(String mensaje){
		super(mensaje);
	}
	
}
