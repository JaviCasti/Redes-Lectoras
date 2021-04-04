package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroSesion
 */
@WebFilter("/FiltroSesion")
public class FiltroSesion implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroSesion() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (request.getSession(false)!=null) {
        	//pasa por el filtro Sesion  y delega en el siguiente filtro(admin) y si es el ultimo a la peticion"); 
        	chain.doFilter(request, response);	
        }
        	 
         else  { 
        	 // lo mando a validar abortamos la peticion
        	 // el sendRedirect no añade la url de ContextPath() finalizo peticion
        	response.sendRedirect(request.getContextPath()+"/IniciarSesion.jsp"); 
        	
         }
       	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
