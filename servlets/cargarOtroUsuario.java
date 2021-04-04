package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servicios.ServicioPeticionAmistad;
import servicios.ServicioUsuario;

import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class cargarOtroUsuario
 */
@WebServlet("/cargarOtroUsuario")
public class cargarOtroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cargarOtroUsuario() {
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
		Usuario usuario1 = null;
		ServicioUsuario servUsuario;
		ServicioPeticionAmistad servPeticionAmistad;
		Usuario usuario2 = null;
		String salida = null;
		boolean peticion = false, amigo = false,peticionOtro = false;
		
		try{
			servUsuario = new ServicioUsuario();
			servPeticionAmistad = new ServicioPeticionAmistad();
			usuario1 = new Usuario(request.getParameter("nomUsu"));
			usuario2 = new Usuario((String)sesion.getAttribute("nombre"));
			usuario1 = servUsuario.recuperarUsuario(usuario1);
			peticion = servPeticionAmistad.recuperarPeticionAmistad(usuario2, usuario1);
			peticionOtro = servPeticionAmistad.recuperarPeticionAmistad(usuario1, usuario2);
			amigo = servUsuario.recuperarAmigo(usuario2, usuario1);
			request.setAttribute("usuario", usuario1);
			if(peticion)request.setAttribute("peticion", "1");else request.setAttribute("peticion", "0");
			if(peticionOtro)request.setAttribute("peticionOtro", "1");else request.setAttribute("peticionOtro", "0");
			if(amigo)request.setAttribute("amigo", "1");else request.setAttribute("amigo","0");
			salida = "/PaginaOtroUsuario.jsp";
			
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
