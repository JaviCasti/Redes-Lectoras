package servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Libro;
import domain.Resena;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

import servicios.ServicioLibro;
import servicios.ServicioPeticionAmistad;
import servicios.ServicioResena;

/**
 * Servlet implementation class cargarLibros
 */
@WebServlet("/cargarIndexUsuario")
public class cargarIndexUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cargarIndexUsuario() {
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
		ServicioLibro servLibro;
		ServicioResena servReseña;
		ServicioPeticionAmistad servPeticionAmistad;
		List<Usuario>listausuarios = null;
		List<Libro> listalibros = null;
		List<Libro> listalibrosreseñas = null;
		List<Resena> listareseñas = null;
		List<Resena> ultimasreseñasusuarioactivo = null;
		String salida = null;
		Usuario usuario = null;
		HttpSession sesion = request.getSession();
		try{
			usuario = new Usuario((String)sesion.getAttribute("nombre"));
			servLibro = new ServicioLibro();
			servReseña = new ServicioResena();
			servPeticionAmistad = new ServicioPeticionAmistad();
			listausuarios = servPeticionAmistad.recuperarPeticionesAmistad(usuario);
			request.setAttribute("peticiones", listausuarios);
			listalibros = servLibro.recuperarLibroSeguidos(new Usuario((String)sesion.getAttribute("nombre")));
			request.setAttribute("libros",listalibros);
			listareseñas = servReseña.recuperarReseñasAmigos(usuario);
			request.setAttribute("reseñas", listareseñas);
			listalibrosreseñas = servLibro.recuperarLibroReseñas(usuario);
			request.setAttribute("librosreseñas", listalibrosreseñas);
			ultimasreseñasusuarioactivo = servReseña.recuperarUltimasReseñasUsuarioActivo(usuario);
			request.setAttribute("ultimasReseñasUsuarioActivo", ultimasreseñasusuarioactivo);
			request.setAttribute("mensaje", "s");
			salida = "/indexUsuario.jsp";
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
