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

import servicios.ServicioSeguimiento;

/**
 * Servlet implementation class borrarSeguidor
 */
@WebServlet("/borrarSeguidor")
public class borrarSeguidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrarSeguidor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServicioSeguimiento servSeguimiento;
		Autor autor = null;
		Usuario usuario = null;
		String salida = null;
		boolean borrado;
		
		try{
			
			autor = new Autor(request.getParameter("nomAut"));
			HttpSession sesion = request.getSession(false);
			usuario = new Usuario((String)sesion.getAttribute("nombre"));
			servSeguimiento = new ServicioSeguimiento();
			borrado = servSeguimiento.borrarSeguimiento(usuario, autor);
			if(borrado){
				salida = "/cargarAutor?nomAut="+request.getParameter("nomAut");
			}else{
				request.setAttribute("error", "Ha ocurrido un error interno, vuelve a intentarlo");
				salida = "/paginaError.jsp";
			}
			
		}catch (ServiceException|DomainException e) {
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
