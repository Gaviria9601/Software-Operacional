package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.io.BufferedReader;
import java.io.FileReader;

public class Contrasenia {

	public String getPassword(String archivo) {
		try {
			String cadena;
			FileReader f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				return cadena;
			}
			b.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "No se encontro archivos";

	}
}