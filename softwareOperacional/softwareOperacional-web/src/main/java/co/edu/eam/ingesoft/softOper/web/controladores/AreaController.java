package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.softOpe.negocio.beans.AreaEJB;
import co.edu.eam.ingesoft.softOpe.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.softOper.entidades.Area;

@Named("areaControlador")
@ViewScoped
public class AreaController implements Serializable {
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 3 y 15")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 10 y 2000")
	private String des;
	
	private Integer id;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@EJB
	private AreaEJB arEJB;
	
	@PostConstruct
	public void inicializador(){
		
	}
	
	public void crearArea(){
		try{
			
			Area a = new Area(id,nombre, des);
			arEJB.crearArea(a);
			
			
			//limpiar();
			Messages.addFlashGlobalInfo("Area ingresada Correctamente");
				
		} catch (ExcepcionNegocio e) {
		       Messages.addGlobalError(e.getMessage());
			   }
		}

}
