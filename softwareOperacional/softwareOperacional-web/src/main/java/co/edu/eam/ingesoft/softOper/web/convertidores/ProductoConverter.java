package co.edu.eam.ingesoft.softOper.web.convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.softOpe.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.softOper.entidades.Cliente;
import co.edu.eam.ingesoft.softOper.entidades.Producto;

@FacesConverter( value = "productoConverter", forClass = Producto.class)
@Named("productoConverter")
public class ProductoConverter implements Converter {

	@EJB
	private ProductoEJB productoEJB;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.trim().length() == 0 || string.equals("Seleccione...")) {
			return null;
		}
		return productoEJB.buscarProduto((Integer.parseInt(string)));
	
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj instanceof Producto) {
			Producto pro = (Producto) obj;
			return pro.getCodigo()+"";
		}
		
		return null;
	}
	
}
