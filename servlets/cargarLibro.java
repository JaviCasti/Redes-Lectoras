package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servicios.ServicioLibro;
import servicios.ServicioResena;

import domain.EstadoLibro;
import domain.Libro;
import domain.Resena;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class cargarLibro
 */
@WebServlet("/cargarLibro")
public class cargarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cargarLibro() {
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
		ServicioLibro servLibro;
		ServicioResena servReseña;
		Usuario usuario;
		Libro libro = null;
		List<Resena> listareseñas = new ArrayList<Resena>();
		String salida = null;
		EstadoLibro estado;
		
		try{
			libro = new Libro(request.getParameter("nomLi"));
			servLibro = new ServicioLibro();
			if(sesion.getAttribute("nombre")!=null){
				usuario = new Usuario((String)sesion.getAttribute("nombre"));
				estado = servLibro.RecuperarEstadoLibro(usuario, libro);
				request.setAttribute("estado", estado);
			}
			
			servReseña = new ServicioResena();
			listareseñas = servReseña.RecuperarReseñasLibro(libro);
			request.setAttribute("resenas", listareseñas);
			libro = servLibro.recuperarLibro(libro);
			request.setAttribute("libro",libro);
			salida = "/PaginaLibro.jsp";
			
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
