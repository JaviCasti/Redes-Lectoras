package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.ServicioPeticionAmistad;
import servicios.ServicioUsuario;

import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class InsertarSolicitudAmistad
 */
@WebServlet("/InsertarSolicitudAmistad")
public class InsertarSolicitudAmistad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarSolicitudAmistad() {
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
		Usuario usuario1 = null, usuario2 = null;
		ServicioPeticionAmistad servPeticionAmistad;
		String salida = null;
		try{
			usuario1 = new Usuario(request.getParameter("nom1"));
			usuario2 = new Usuario(request.getParameter("nom2"));
			servPeticionAmistad = new ServicioPeticionAmistad();
			servPeticionAmistad.insertarPeticionAmistad(usuario1, usuario2);
			salida = "/cargarOtroUsuario?nomUsu="+request.getParameter("nom2");
		}catch (ServiceException|DomainException e) {
			if(e.getCause()==null){//Error L?gico para usuario
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
