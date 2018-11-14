package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Objeto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private ArrayList<Cluster> children;
	
	public Objeto() {
		// TODO Auto-generated constructor stub
	}
	


	/**
	 * @param name
	 * @param children
	 */
	public Objeto(String name, ArrayList<Cluster> children) {
		super();
		this.name = name;
		this.children = children;
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
	 * @return the children
	 */
	public ArrayList<Cluster> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<Cluster> children) {
		this.children = children;
	}
	
	
	
	
	
}
