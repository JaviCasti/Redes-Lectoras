package servlets;

import java.io.File;
import java.io.IOException;
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

import servicios.ServicioLibro;

import domain.Autor;
import domain.Libro;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class EditarLibro
 */
@WebServlet("/EditarLibro")
public class EditarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarLibro() {
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
		Libro libro, libro1;
		ServicioLibro servLibro;
		String salida = null;
		List<String> lista = new ArrayList<String>();
		try{
			servLibro = new ServicioLibro();
			libro = new Libro(request.getParameter("nomLi"));
			libro = servLibro.recuperarLibro(libro);
			
			FileItemFactory file = new DiskFileItemFactory();
			ServletFileUpload subidorImagen = new ServletFileUpload(file);
			List items = subidorImagen.parseRequest(request);
			for(int i = 0; i<items.size();i++){
				FileItem fileItem = (FileItem)items.get(i);
				if(!fileItem.isFormField()){
					if(fileItem.getName().length()!=0){
						File f = new File("D:\\j2ee\\Proyecto\\RedesLectoras\\WebContent\\img\\portadas\\"+lista.get(0).replace("ñ","n")+"."+fileItem.getName().substring((fileItem.getName().length()-3),fileItem.getName().length()));
						lista.add(fileItem.getName().substring((fileItem.getName().length()-3)));
						fileItem.write(f);
						System.out.println("foto");
					}
				}else{
					lista.add(fileItem.getString());
					System.out.println("no foto");
				}
			}
			
			if(lista.size() == 5)
				lista.add(libro.getExtension());
			
			libro1 = new Libro(lista.get(0),new Autor(lista.get(1)),Integer.parseInt(lista.get(3)),lista.get(2),util.Fecha.convertirATimestamp(lista.get(4),"yyyy-MM-dd"),lista.get(5));

			if(!libro1.equals(libro))
				servLibro.modificarLibro(libro, libro1);
			salida = "/mostrarLibros";
		} catch (ServiceException|DomainException | NumberFormatException | ParseException | FileUploadException e) {
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
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
