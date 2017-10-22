package co.edu.eam.ingesoft.softOper.web.convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;



import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;

@FacesConverter( value = "clienteConverter", forClass = Cliente.class)
@Named("clienteConverter")
public class ClienteConverter implements Converter{
	
	@EJB
	private ClienteEJB clienteEJB;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return clienteEJB.buscarCliente((Integer.parseInt(string)));
	
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Cliente) {
			Cliente cli = (Cliente) obj;
			return cli.getCodigo()+"";
		}
		
		return null;
	}

}
