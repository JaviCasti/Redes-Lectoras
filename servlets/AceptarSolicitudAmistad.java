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
 * Servlet implementation class AceptarSolicitudAmistad
 */
@WebServlet("/AceptarSolicitudAmistad")
public class AceptarSolicitudAmistad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AceptarSolicitudAmistad() {
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
		ServicioUsuario servUsuario;
		ServicioPeticionAmistad servPeticionAmistad;
		String salida = null;
		try{
			usuario1 = new Usuario(request.getParameter("nom1"));
			usuario2 = new Usuario(request.getParameter("nom2"));
			servUsuario = new ServicioUsuario();
			servPeticionAmistad = new ServicioPeticionAmistad();
			servPeticionAmistad.borrarPeticionAmistad(usuario2, usuario1);
			servUsuario.insertarAmigo(usuario1, usuario2);
			response.sendRedirect(request.getContextPath()+"/cargarOtroUsuario?nomUsu="+request.getParameter("nom2"));
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
