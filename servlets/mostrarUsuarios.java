package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.ServicioUsuario;

import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class mostrarUsuarios
 */
@WebServlet("/mostrarUsuarios")
public class mostrarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mostrarUsuarios() {
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
		List<Usuario> lista = new ArrayList<Usuario>();
		ServicioUsuario servUsuario;
		String salida = null;
		try{
			
			servUsuario = new ServicioUsuario();
			lista = servUsuario.mostrarTodosUsuarios();
			request.setAttribute("usuarios", lista);
			salida = "/ListadoUsuarios.jsp";
			
		} catch (ServiceException|DomainException e) {
			if(e.getCause()==null){//Error L?gico para usuario
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
