package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

import servicios.ServicioUsuario;

/**
 * Servlet implementation class insertarReporte
 */
@WebServlet("/insertarReporte")
public class insertarReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertarReporte() {
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
		
		HttpSession sesion = request.getSession(false);
		ServicioUsuario servUsuario;
		Usuario usuario1, usuario2;
		String salida = null;
		
		try{
			
			servUsuario = new ServicioUsuario();
			usuario1 = new Usuario((String)sesion.getAttribute("nombre"));
			usuario2 = new Usuario(request.getParameter("reportado"));
			servUsuario.insertarReporte(usuario1, usuario2);
			response.sendRedirect(request.getContextPath()+"/cargarLibro?nomLi="+request.getParameter("libro"));
			
		}catch (ServiceException|DomainException e) {
			if(e.getCause()==null){//Error Lógico para usuario
				request.setAttribute("error",e.getMessage() );
				salida="/PaginaError.jsp";
				getServletContext().getRequestDispatcher(salida).forward(request, response);
			}else{
				
				request.setAttribute("error","error interno" );
				salida="/PaginaError.jsp";
				e.printStackTrace();// para administrador
				getServletContext().getRequestDispatcher(salida).forward(request, response);
			}
		}
		
	}
}
