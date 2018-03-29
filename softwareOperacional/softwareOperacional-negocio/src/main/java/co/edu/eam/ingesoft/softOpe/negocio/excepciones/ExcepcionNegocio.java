package co.edu.eam.ingesoft.softOpe.negocio.excepciones;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ExcepcionNegocio extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionNegocio(String mensaje) {
		super(mensaje);
	}

}
