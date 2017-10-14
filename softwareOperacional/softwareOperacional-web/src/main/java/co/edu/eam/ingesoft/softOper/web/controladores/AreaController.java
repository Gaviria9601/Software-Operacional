package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;


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
	
	private int id;
	
	private List<Area> areas;
	
	private ArrayList<Area> filtroArea = new ArrayList<Area>();
	
	private boolean busco = false;
	
	private Area ar;
	
	
	
	
	
	public Area getAr() {
		return ar;
	}

	public void setAr(Area ar) {
		this.ar = ar;
	}

	public boolean isBusco() {
		return busco;
	}

	public void setBusco(boolean busco) {
		this.busco = busco;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public ArrayList<Area> getFiltroArea() {
		return filtroArea;
	}

	public void setFiltroArea(ArrayList<Area> filtroArea) {
		this.filtroArea = filtroArea;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AreaEJB getArEJB() {
		return arEJB;
	}

	public void setArEJB(AreaEJB arEJB) {
		this.arEJB = arEJB;
	}



	@EJB
	private AreaEJB arEJB;
	
	@PostConstruct
	public void inicializador(){
		areas = arEJB.listarArea();
	}
	
	public void crear(){
		try{
			
			Area a = new Area(nombre, des);
			arEJB.crear(a);
			System.out.println(id);
			System.out.println(nombre);
			System.out.println(des);
			
			
			//limpiar();
			Messages.addFlashGlobalInfo("Area ingresada Correctamente");
				
		} catch (ExcepcionNegocio e) {
		       Messages.addGlobalError(e.getMessage());
			   }
		}
	
	public void resetearFitrosTabla(String id) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('vtWidget').clearFilters()");
	}
	
	public void modificar (Area audi) {
		ar = audi;
		nombre = audi.getNombre();
		des = audi.getDescripcion();
		busco = true;
	}

	public void editar() {
		ar.setNombre(nombre);
		ar.setDescripcion(des);
		
		//limpiar();
		
		Messages.addGlobalInfo("El area fue modificada con exito");
		//limpiar();
	}


	/**
	 * Metodo que elimina un farmaceutico
	 */
	/**public void eliminar(Area exa) {
		try {
			arEJB.eliminarArea(exa.getId());
			
			//limpiar();
			Messages.addFlashGlobalInfo("Area eliminada correctamente");
			areas = arEJB.listarArea();
			resetearFitrosTabla("tablaExamenes");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}**/


}
