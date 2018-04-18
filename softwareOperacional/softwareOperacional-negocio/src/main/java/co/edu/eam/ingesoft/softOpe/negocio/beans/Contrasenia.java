package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Contrasenia {

	public String getPassword(String archivo) {
		FileReader f = null;
		BufferedReader b = null;
		try {
			String cadena;
			f = new FileReader(archivo);
			b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				return cadena;
			}
			b.close();
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				f.close();
				b.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "No se encontro archivos";

	}
}