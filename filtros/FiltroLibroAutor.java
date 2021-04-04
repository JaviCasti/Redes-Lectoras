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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroLibro
 */
@WebFilter("/FiltroLibro")
public class FiltroLibroAutor implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroLibroAutor() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String salida = null;
		if(request.getParameter("nomLi")!=null || request.getParameter("nomAut")!= null){
			chain.doFilter(request, response);
		}else{
			if(request.getSession(false)!= null){
				response.sendRedirect(request.getContextPath()+"/index.jsp"); 
			}else{
				response.sendRedirect(request.getContextPath()+"/indexUsuario.jsp"); 
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
