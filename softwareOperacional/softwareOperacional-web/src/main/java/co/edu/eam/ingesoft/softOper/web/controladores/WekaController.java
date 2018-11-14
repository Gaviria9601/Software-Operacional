package co.edu.eam.ingesoft.softOper.web.controladores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.UploadedFile;

import co.edu.eam.ingesoft.softOpe.negocio.beans.WekaEJB;

@Named("wekaController")
@ViewScoped
public class WekaController implements Serializable {

	@EJB
	private WekaEJB wekaEJB;

	private UploadedFile file;
	private String data;
	private String resData;
	private String dataArbol;
	
	
	

	/**
	 * @return the dataArbol
	 */
	public String getDataArbol() {
		return dataArbol;
	}

	/**
	 * @param dataArbol the dataArbol to set
	 */
	public void setDataArbol(String dataArbol) {
		this.dataArbol = dataArbol;
	}

	

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * @return the resData
	 */
	public String getResData() {
		return resData;
	}

	/**
	 * @param resData
	 *            the resData to set
	 */
	public void setResData(String resData) {
		this.resData = resData;
	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public void upload() {
		if (file != null) {
			try {

				BufferedReader buRead = new BufferedReader(new InputStreamReader(file.getInputstream()));
				setData(convertir(buRead));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesMessage message = new FacesMessage("Se ha cargado Exitosamente el Archivo",null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @param file
	 * @return
	 * @throws IOException
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	private String convertir(BufferedReader file) throws IOException {
		// Almacena la linea leida del file
		String temp;
		// cadena final
		String cadena = "";
		// Mientras pueda leer una nueva linea del buffer
		while ((temp = file.readLine()) != null) {
			cadena = cadena + temp + "\n";
		}
		return cadena;
	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public void mineria() {
		resData = wekaEJB.mineriaApriori(getData());
		dataArbol = wekaEJB.mineriaArbol(getData());
		FacesMessage message = new FacesMessage("Aplicado Correctamente Minería.",null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
