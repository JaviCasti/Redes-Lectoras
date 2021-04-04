package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Autor;
import domain.Libro;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

import servicios.ServicioAutor;
import servicios.ServicioLibro;
import servicios.ServicioUsuario;

/**
 * Servlet implementation class Busqueda
 */
@WebServlet("/busqueda")
public class busqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busqueda() {
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
		String busqueda = request.getParameter("busqueda");
		String tipoBusqueda = request.getParameter("categoria");
		HttpSession sesion = request.getSession(false);
		String salida = null;
		switch(tipoBusqueda){
		case "libros":
			request.setAttribute("tipoBusqueda", "libros");
			ServicioLibro servLibro = null;
			List<Libro> listaLibros = null;
			try{
				servLibro = new ServicioLibro();
				listaLibros = servLibro.busquedaLibro(busqueda);
				request.setAttribute("busqueda", listaLibros);
				salida = "/Busqueda.jsp";
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
			break;
		case "autor":
			request.setAttribute("tipoBusqueda", "autor");
			ServicioAutor servAutor = null;
			List<Autor> listaAutor = null;
			try{
				servAutor = new ServicioAutor();
				listaAutor = servAutor.busquedaAutor(busqueda);
				request.setAttribute("busqueda", listaAutor);
				salida = "/Busqueda.jsp";
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
			break;
		case "usuario":
			request.setAttribute("tipoBusqueda", "usuario");
			ServicioUsuario servUsuario = null;
			List<Usuario> listaUsuario = null;
			try{
				servUsuario = new ServicioUsuario();
				if(sesion.getAttribute("nombre")!=null)
					listaUsuario = servUsuario.busquedaUsuario(busqueda,new Usuario((String)sesion.getAttribute("nombre")));
				else
					listaUsuario = servUsuario.busquedaUsuario(busqueda,new Usuario(""));
				request.setAttribute("busqueda", listaUsuario);
				salida = "/Busqueda.jsp";
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
			break;
		default:
			request.setAttribute("error", "categoria no valida");
			salida = "/PaginaError.jsp";
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
