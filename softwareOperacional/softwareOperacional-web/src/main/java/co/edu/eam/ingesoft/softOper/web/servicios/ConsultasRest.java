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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.softOpe.negocio.beans.ConsultasC3EJB;
import co.edu.eam.ingesoft.softOpe.negocio.beans.WekaEJB;
import co.edu.eam.ingesoft.softOper.entidades.Objeto;

@Path("/consultas")
public class ConsultasRest {

	@EJB
	private ConsultasC3EJB conC3;
	
	@EJB
	private WekaEJB wekaEJB;

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
	public List<Consulta2> listarConsulta2() {
		List<Object[]> res = conC3.consulta2();
		List<Consulta2> listaRet = new ArrayList<Consulta2>();
		for (Object[] objects : res) {
			Consulta2 con = new Consulta2();
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
	public List<Consulta3> listarConsulta3() {
		List<Object[]> res = conC3.consulta3();
		List<Consulta3> listaRet = new ArrayList<Consulta3>();
		for (Object[] objects : res) {
			Consulta3 con = new Consulta3();
			con.setVenta(Double.parseDouble(objects[0].toString()));
			con.setCliente(objects[1].toString());
			listaRet.add(con);
		}
		return listaRet;

	}

	@Secured
	@Path("/listarConsulta4")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta4> listarConsulta4() throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		List<Object[]> res = conC3.consulta4();
		List<Consulta4> listaRet = new ArrayList<Consulta4>();
		for (Object[] objects : res) {
			Consulta4 con = new Consulta4();
			con.setVenta(Double.parseDouble(objects[0].toString()));
			Date date = inputFormat.parse(objects[1].toString());
			con.setFecha(date);
			listaRet.add(con);
		}
		return listaRet;

	}

	@Secured
	@Path("/filtroConsulta1")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> filtroConsulta1(@QueryParam("prod") String prod,@QueryParam("clien") String clien) throws ParseException {

		List<Object[]> res = conC3.consultaFiltro1(Long.parseLong(prod),Long.parseLong(clien));
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
	@Path("/filtroConsulta2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta2> filtroConsulta2(@QueryParam("prod") String prod,@QueryParam("clien") String clien) throws ParseException {

		List<Object[]> res = conC3.consultaFiltro2(Long.parseLong(prod),Long.parseLong(clien));
		List<Consulta2> listaRet = new ArrayList<Consulta2>();
		for (Object[] objects : res) {
			Consulta2 con = new Consulta2();
			con.setProducto(objects[1].toString());
			con.setCantidad(Integer.parseInt(objects[0].toString()));
			listaRet.add(con);
		}
		return listaRet;

	}
	
	@Secured
	@Path("/filtroConsulta3")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta3> filtroConsulta3(@QueryParam("prod") String prod,@QueryParam("clien") String clien) throws ParseException {

		List<Object[]> res = conC3.consultaFiltro3(Long.parseLong(prod),Long.parseLong(clien));
		List<Consulta3> listaRet = new ArrayList<Consulta3>();
		for (Object[] objects : res) {
			Consulta3 con = new Consulta3();
			con.setVenta(Double.parseDouble(objects[0].toString()));
			con.setCliente(objects[1].toString());
			listaRet.add(con);
		}
		return listaRet;

	}
	
	@Secured
	@Path("/filtroConsulta4")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta4> filtroConsulta4(@QueryParam("prod") String prod,@QueryParam("clien") String clien) throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		List<Object[]> res = conC3.consultaFiltro4(Long.parseLong(prod),Long.parseLong(clien));
		List<Consulta4> listaRet = new ArrayList<Consulta4>();
		for (Object[] objects : res) {
			Consulta4 con = new Consulta4();
			con.setVenta(Double.parseDouble(objects[0].toString()));
			Date date = inputFormat.parse(objects[1].toString());
			con.setFecha(date);
			listaRet.add(con);
		}
		return listaRet;

	}
	
	@Secured
	@Path("/cluster")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Objeto wekaCluster(@QueryParam("datos") String datos){
		return wekaEJB.mineriaCluster(datos);
	
	}
	
	

}
