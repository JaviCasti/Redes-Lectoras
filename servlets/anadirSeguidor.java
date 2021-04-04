package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Autor;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

import servicios.ServicioSeguimiento;

/**
 * Servlet implementation class añadirSeguidor
 */
@WebServlet("/anadirSeguidor")
public class anadirSeguidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public anadirSeguidor() {
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
		
		System.out.println("entro");
		ServicioSeguimiento servSeguimiento;
		Autor autor = null;
		Usuario usuario = null;
		String salida = null; 
		try{
			
			autor = new Autor((String)request.getParameter("nomAut"));
			HttpSession sesion = request.getSession(false);
			usuario = new Usuario((String)sesion.getAttribute("nombre"));
			servSeguimiento = new ServicioSeguimiento();
			servSeguimiento.insertarSeguimiento(usuario, autor);
			System.out.println("insertado correctamente");
			salida = "/cargarAutor?nomAut="+request.getParameter("nomAut");
			
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
