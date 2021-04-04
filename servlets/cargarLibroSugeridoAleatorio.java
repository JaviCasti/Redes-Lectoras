package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.ServicioLibro;

import domain.Libro;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class cargarLibroSugeridoAleatorio
 */
@WebServlet("/cargarLibroSugeridoAleatorio")
public class cargarLibroSugeridoAleatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cargarLibroSugeridoAleatorio() {
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
		List<Libro> lista = new ArrayList<Libro>();
		String salida;
		try{
			
			servLibro = new ServicioLibro();
			lista = servLibro.recuperarTotalLibrosSugeridos();
			int numero = (int)Math.floor(Math.random() * lista.size());
			try{
				request.setAttribute("sugerido", lista.get(numero));
			}catch(IndexOutOfBoundsException e){
				request.setAttribute("mensaje", "No hay libros sugeridos por el momento");
			}
			
			salida = "/PaginaSugerencia.jsp";
			
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
