package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import servicios.ServicioAutor;
import servicios.ServicioLibro;
import domain.Autor;
import domain.Libro;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class editarAutor
 */
@WebServlet("/editarAutor")
public class editarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editarAutor() {
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
		List<String> lista = new ArrayList<String>();
		Autor autor1 = null, autor2 = null;
		ServicioAutor servAutor = null;
		String salida = null;
		try{
			FileItemFactory file = new DiskFileItemFactory();
			ServletFileUpload subidorImagen = new ServletFileUpload(file);
			List items = subidorImagen.parseRequest(request);
			autor2 = new Autor(request.getParameter("nomAut"));
			servAutor = new ServicioAutor();
			autor2 = servAutor.recuperarAutor(autor2);
			for(int i = 0; i<items.size();i++){
				FileItem fileItem = (FileItem)items.get(i);
				if(!fileItem.isFormField()){
					if(fileItem.getName().length()!=0){
						File f = new File("D:\\j2ee\\Proyecto\\RedesLectoras\\WebContent\\img\\autores\\"+lista.get(0).replace("ñ","n")+"."+fileItem.getName().substring((fileItem.getName().length()-3),fileItem.getName().length()));
						lista.add(fileItem.getName().substring((fileItem.getName().length()-3)));
						fileItem.write(f);
					}else{
						lista.add(autor2.getExtension());
					}
				}else{
					lista.add(fileItem.getString());
				}
			}
			autor1 = new Autor(lista.get(0),lista.get(1),util.Fecha.convertirADate(lista.get(2), "yyyy-MM-dd"),lista.get(3),lista.get(4));
			servAutor.modificarAutor(autor1,autor2);
			salida = "/mostrarAutores";
			
		}catch (ServiceException|DomainException | NumberFormatException | ParseException | FileUploadException e) {
			if(e.getCause()==null){//Error Lógico para usuario
				request.setAttribute("error",e.getMessage() );
				salida="/PaginaError.jsp";
				
			}else{
				
				request.setAttribute("error","error interno" );
				salida="/PaginaError.jsp";
				e.printStackTrace();// para administrador
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error",e.getMessage() );
			salida="/PaginaError.jsp";
			e.printStackTrace();// para administrador
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
