package co.edu.eam.ingesoft.softOper.web.servicios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.softOpe.negocio.beans.ConsultasC3EJB;

@Path("/consultas")
public class ConsultasRest {

	@EJB
	private ConsultasC3EJB conC3;

	@Secured
	@Path("/listarConsulta1")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> listarConsulta1() {
		List<Object[]> res = conC3.consulta1();
		List<Consulta> listaRet = new ArrayList<Consulta>();
		for (Object[] objects : res) {
			Consulta con = new Consulta();
			con.setProducto(objects[1].toString());
			con.setventa(Double.parseDouble(objects[0].toString()));
			listaRet.add(con);
		}
		return listaRet;

	}

	@Secured
	@Path("/listarConsulta2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> listarConsulta2() {
		List<Object[]> res = conC3.consulta2();
		List<Consulta> listaRet = new ArrayList<Consulta>();
		for (Object[] objects : res) {
			Consulta con = new Consulta();
			con.setProducto(objects[1].toString());
			con.setCantidad(Integer.parseInt(objects[0].toString()));
			listaRet.add(con);
		}
		return listaRet;

	}

	@Secured
	@Path("/listarConsulta3")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> listarConsulta3() {
		List<Object[]> res = conC3.consulta3();
		List<Consulta> listaRet = new ArrayList<Consulta>();
		for (Object[] objects : res) {
			Consulta con = new Consulta();
			con.setventa(Double.parseDouble(objects[0].toString()));
			con.setCliente(objects[1].toString());
			con.setEmpleado(objects[2].toString());
			listaRet.add(con);
		}
		return listaRet;

	}

	@Secured
	@Path("/listarConsulta4")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> listarConsulta4() throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		List<Object[]> res = conC3.consulta4();
		List<Consulta> listaRet = new ArrayList<Consulta>();
		for (Object[] objects : res) {
			Consulta con = new Consulta();
			con.setventa(Double.parseDouble(objects[0].toString()));
			Date date = inputFormat.parse(objects[1].toString());
			con.setFecha(date);
			listaRet.add(con);
		}
		return listaRet;

	}

}
