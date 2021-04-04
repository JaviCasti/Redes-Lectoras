package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.ServicioAutor;
import servicios.ServicioLibro;
import domain.Autor;
import domain.Libro;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class prepararEditarAutor
 */
@WebServlet("/prepararEditarAutor")
public class prepararEditarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prepararEditarAutor() {
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
		Autor autor;
		ServicioAutor servAutor;
		String salida = null;
		
		try{
			
			autor = new Autor(request.getParameter("nomAut"));
			servAutor = new ServicioAutor();
			autor = servAutor.recuperarAutor(autor);
			request.setAttribute("autor", autor);
			salida = "/EditarAutor.jsp";
			
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
