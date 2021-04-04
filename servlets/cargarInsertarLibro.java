package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.ServicioAutor;

import domain.Autor;
import domain.Libro;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class cargarInsertarLibro
 */
@WebServlet("/cargarInsertarLibro")
public class cargarInsertarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cargarInsertarLibro() {
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
		
		ServicioAutor servAutor;
		String salida = null;
		Libro libro = new Libro();
		List<Autor> lista = new ArrayList<Autor>();
		Timestamp tiempo = new Timestamp(2000,1,1,12,0,0,1);
		try{
			if(request.getParameter("fecha")!=null){
				try{
					libro = new Libro(request.getParameter("nombre"),new Autor(request.getParameter("autor")),Integer.parseInt(request.getParameter("numPag")),request.getParameter("resumen"),util.Fecha.convertirATimestamp(request.getParameter("fecha"), "yyyy-MM-dd"),"");
				}catch(NumberFormatException e){
					try {
						libro = new Libro(request.getParameter("nombre"),new Autor(request.getParameter("autor")),0,request.getParameter("resumen"),util.Fecha.convertirATimestamp("", "yyyy-MM-dd"),"");
					} catch (ParseException e1) {
						libro = new Libro(request.getParameter("nombre"),new Autor(request.getParameter("autor")),0,request.getParameter("resumen"),tiempo,"");
						e1.printStackTrace();
					}
				} catch (ParseException e) {
					libro = new Libro(request.getParameter("nombre"),new Autor(request.getParameter("autor")),Integer.parseInt(request.getParameter("numPag")),request.getParameter("resumen"),tiempo,"");
					e.printStackTrace();
				}
			}
			request.setAttribute("libro", libro);
			servAutor = new ServicioAutor();
			lista = servAutor.recuperarTodosAutor();
			request.setAttribute("autores", lista);
			salida = "/InsertarLibro.jsp";
			
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
