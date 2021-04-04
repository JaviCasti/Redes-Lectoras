package servlets;

import java.io.File;
import java.io.FileInputStream;
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
 * Servlet implementation class insertarLibro
 */
@WebServlet("/insertarLibro")
public class insertarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public insertarLibro() {
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
		Libro libro = null;
		ServicioLibro servLibro = null;
		String salida = null;
		try{
			FileItemFactory file = new DiskFileItemFactory();
			ServletFileUpload subidorImagen = new ServletFileUpload(file);
			List items = subidorImagen.parseRequest(request);
			for(int i = 0; i<items.size();i++){
				FileItem fileItem = (FileItem)items.get(i);
				if(!fileItem.isFormField()){
					if(fileItem.getName().length()!=0){
						File f = new File("D:\\j2ee\\Proyecto\\RedesLectoras\\WebContent\\img\\portadas\\"+lista.get(0).replace("ñ","n")+"."+fileItem.getName().substring((fileItem.getName().length()-3),fileItem.getName().length()));
						fileItem.write(f);
						lista.add(fileItem.getName().substring((fileItem.getName().length()-3)));
					}else{
						
						File destino = new File("D:/j2ee/Proyecto/RedesLectoras/WebContent/img/portadas/"+lista.get(0).replace("ñ","n")+".jpg");
						File fuente = new File("D:/j2ee/Proyecto/RedesLectoras/WebContent/img/portadas/default.jpg");
						if(!destino.isFile()){
							FileUtils.copyFile(fuente,destino);
						}
		                lista.add("jpg");
					}
				}else{
					lista.add(fileItem.getString());
				}
			}
			libro = Libro.crearLibro(lista.get(0),new Autor(lista.get(1)),Integer.parseInt(lista.get(3)),lista.get(2),util.Fecha.convertirATimestamp(lista.get(4),"yyyy-MM-dd"),lista.get(5));
			servLibro = new ServicioLibro();
			servLibro.InsertarLibro(libro);
			salida = "/PaginaAdmin.jsp";
			
		}catch (ServiceException|DomainException | NumberFormatException | ParseException | FileUploadException e) {
			if(e.getCause()==null){//Error Lógico para usuario
				request.setAttribute("error",e.getMessage() );
				salida="/cargarInsertarLibro";
				
			}else{
				
				request.setAttribute("error","error interno" );
				salida="/cargarInsertarLibro";
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
