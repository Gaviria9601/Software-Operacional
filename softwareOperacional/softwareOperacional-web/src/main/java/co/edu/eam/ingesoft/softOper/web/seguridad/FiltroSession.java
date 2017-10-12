package co.edu.eam.ingesoft.softOper.web.seguridad;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;

import co.edu.eam.ingesoft.softOper.entidades.Usuario;
import co.edu.eam.ingesoft.softOper.web.controladores.SessionController;




//@WebFilter(urlPatterns="/paginas/privado/*")
public class FiltroSession implements Filter {

	@Inject
	private SessionController sesion;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		Usuario usuario = sesion.getUsuario();
		if(usuario!=null){
			chain.doFilter(arg0, arg1);
		}else{
			res.sendRedirect(req.getContextPath() + "/templates/templateInicioSesion.xhtml");
		}
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
