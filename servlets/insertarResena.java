package servlets;

import java.io.IOException;
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

import servicios.ServicioResena;

/**
 * Servlet implementation class insertarReseña
 */
@WebServlet("/insertarResena")
public class insertarResena extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertarResena() {
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
		HttpSession sesion= request.getSession(false);
		ServicioResena servResena;
		Resena resena;
		Usuario usuario;
		Libro libro;
		String salida = null;
		try{
			servResena = new ServicioResena();
			usuario = new Usuario((String)sesion.getAttribute("nombre"));
			libro = new Libro(request.getParameter("nomLi"));
			servResena.modificarPaginasBiblioteca(libro, usuario, Integer.parseInt(request.getParameter("numPag")), request.getParameter("estado"));
			resena = new Resena(usuario,libro,util.Fecha.fechaActual(),Double.parseDouble(request.getParameter("estrellas")),request.getParameter("descripcion"),Integer.parseInt(request.getParameter("numPag")));
			servResena.insertarResena(resena);
			response.sendRedirect(request.getContextPath()+"/cargarLibro?nomLi="+request.getParameter("nomLi"));
			
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
