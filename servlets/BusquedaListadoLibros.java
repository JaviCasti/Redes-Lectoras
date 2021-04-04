package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Libro;
import exceptions.DomainException;
import exceptions.ServiceException;

import servicios.ServicioLibro;

/**
 * Servlet implementation class BusquedaListadoLibros
 */
@WebServlet("/BusquedaListadoLibros")
public class BusquedaListadoLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaListadoLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicioLibro servLibro = null;
		List<Libro> listaLibros = null;
		String salida = null;
		try{
			
			servLibro = new ServicioLibro();
			listaLibros = servLibro.busquedaLibroListado(request.getParameter("busquedaTitulo"),request.getParameter("busquedaAutor"));
			request.setAttribute("libros", listaLibros);
			salida = "/ListadoLibros.jsp";
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
