package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Autor;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

import servicios.ServicioAutor;
import servicios.ServicioSeguimiento;

/**
 * Servlet implementation class cargarAutor
 */
@WebServlet("/cargarAutor")
public class cargarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cargarAutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicioAutor servAutor;
		ServicioSeguimiento servSeguimiento;
		Autor autor = null;
		Usuario usuario = null;
		String salida = null;
		boolean siguiendo;
		
		try{
			HttpSession sesion = request.getSession(false);
			if(sesion != null){
				usuario = new Usuario((String)sesion.getAttribute("nombre"));
			}
			autor = new Autor(request.getParameter("nomAut"));
			servSeguimiento = new ServicioSeguimiento();
			servAutor = new ServicioAutor();
			autor = servAutor.recuperarAutor(autor);
			siguiendo = servSeguimiento.recuperarSeguimientoUsuarioAutor(usuario, autor);
			request.setAttribute("autor", autor);
			if(siguiendo)
				request.setAttribute("siguiendo","si");
			else
				request.setAttribute("siguiendo","no");
			salida = "/PaginaAutor.jsp";
			
		} catch (ServiceException|DomainException e) {
			if(e.getCause()==null){//Error Lógico para usuario
				request.setAttribute("error",e.getMessage() );
				salida="/PaginaError.jsp";
				
			}else{
				
				request.setAttribute("error","error interno" );
				salida="/PaginaError.jsp";
				e.printStackTrace();// para administrador
				
			}
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
		
	}
}
