package co.edu.eam.ingesoft.softOper.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_PERSONA")
public class Persona implements Serializable{

	@Id
	@Column
	private String cedula;
	
	@Column
	private String nombre;
	
}
