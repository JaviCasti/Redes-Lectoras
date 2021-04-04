package servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servicios.ServicioLibro;
import domain.Autor;
import domain.Libro;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class insertarSugerencia
 */
@WebServlet("/insertarSugerencia")
public class insertarSugerencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertarSugerencia() {
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
		String salida = null;
		HttpSession sesion = request.getSession(false);
		if(!request.getParameter("titulo").equals("")){
		Libro libro = null;
		ServicioLibro servLibro = null;
		Usuario usuario = null;
		try{
			if(!request.getParameter("numeroPaginas").equals(""))
				libro = new Libro(request.getParameter("titulo"),new Autor(request.getParameter("autor")),Integer.parseInt(request.getParameter("numeroPaginas")),request.getParameter("resumen"),util.Fecha.convertirATimestamp(request.getParameter("fechaPublicacion"),"yyyy-MM-dd"),"");
			else
				libro = new Libro(request.getParameter("titulo"),new Autor(request.getParameter("autor")),0,request.getParameter("resumen"),util.Fecha.convertirATimestamp(request.getParameter("fechaPublicacion"),"yyyy-MM-dd"),"");
			servLibro = new ServicioLibro();
			usuario = new Usuario((String)sesion.getAttribute("nombre"));
			servLibro.insertarLibroSugerido(libro,usuario);
			request.setAttribute("mensaje", "La sugerencia se ha introducido correctamente");
			salida = "/PaginaInsertarSugerencia.jsp";
			
		}catch (ServiceException|DomainException | NumberFormatException | ParseException e) {
			if(e.getCause()==null){//Error Lógico para usuario
				request.setAttribute("error",e.getMessage() );
				salida="/PaginaError.jsp";
				
			}else{
				
				request.setAttribute("error","error interno" );
				salida="/PaginaError.jsp";
				e.printStackTrace();// para administrador
				
			}
		}
		}else{
			request.setAttribute("error","El titulo es obligatorio");
			salida = "/PaginaSugerencia.jsp";
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
