package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

import servicios.ServicioUsuario;

/**
 * Servlet implementation class BusquedaListadoUsuarios
 */
@WebServlet("/BusquedaListadoUsuarios")
public class BusquedaListadoUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaListadoUsuarios() {
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
		ServicioUsuario servUsuario;
		List<Usuario> lista = new ArrayList<Usuario>();
		String salida = null;
		try{
			
			servUsuario = new ServicioUsuario();
			lista = servUsuario.busquedaListadoUsuario(request.getParameter("busqueda"));
			request.setAttribute("usuarios", lista);
			salida = "/ListadoUsuarios.jsp";
			
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
