package co.edu.eam.ingesoft.softOper.web.convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOper.entidades.TipoProducto;

@FacesConverter( value = "tipoProConverter", forClass = TipoProducto.class)
@Named("tipoProConverter")
public class TipoProductoConverter implements Converter {

	@EJB
	private ProductoEJB productoEJB;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return productoEJB.buscarTipoProducto((Integer.parseInt(string)));
	
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof TipoProducto) {
			TipoProducto pro = (TipoProducto) obj;
			return pro.getId()+"";
		}
		
		return null;
	}
	
}
