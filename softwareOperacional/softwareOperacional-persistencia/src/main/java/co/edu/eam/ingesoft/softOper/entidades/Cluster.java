package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

public class Cluster implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private double size;
	
	//private AuditoriaJson audi;
	
	public Cluster() {
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param name
	 * @param size
	 * @param audi
	 */
	public Cluster(String name, double size) {
		super();
		this.name = name;
		this.size = size;
		//this.audi = audi;
	}

	


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}



	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
	}



	/**
	 * @return the audi
	 
	public AuditoriaJson getAudi() {
		return audi;
	}

	/**
	 * @param audi the audi to set
	 
	public void setAudi(AuditoriaJson audi) {
		this.audi = audi;
	}

	**/
	
	
	

}
