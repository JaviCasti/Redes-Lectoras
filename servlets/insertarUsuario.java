package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import servicios.ServicioUsuario;

import daos.TransaccionesManager;
import daos.UsuarioDAO;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;

/**
 * Servlet implementation class prueba
 */
@WebServlet("/insertarUsuario")
public class insertarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public insertarUsuario() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {
		String salida = null;
		if(request.getParameter("contraseña").equals(request.getParameter("contraseña2"))){
			String nombreUsuario = request.getParameter("nombreUsuario");
			String contraseña = DigestUtils.md5Hex(request.getParameter("contraseña"));
			Usuario usuario = new Usuario(nombreUsuario,contraseña,"0","0");
			ServicioUsuario servUsuario = null;
			try {
	
				servUsuario = new ServicioUsuario();
				servUsuario.insertarUsuario(usuario);
				salida = "/IniciarSesion.jsp";
				
				File destino = new File("D:/j2ee/Proyecto/RedesLectoras/WebContent/img/usuarios/"+usuario.getNombreUsuario()+".jpg");
				File fuente = new File("D:/j2ee/Proyecto/RedesLectoras/WebContent/img/usuarios/default.jpg");
                FileUtils.copyFile(fuente,destino);
				
			}catch (ServiceException|DomainException e) {
				if(e.getCause()==null){//Error Lógico para usuario
					request.setAttribute("error",e.getMessage() );
					salida="/Registrarse.jsp";
					
				}else{
					
					request.setAttribute("error","error interno" );
					salida="/Registrarse.jsp";
					e.printStackTrace();// para administrador
					
				}
			}
		}else{
			request.setAttribute("error","Las contraseñas no coinciden" );
			salida="/Registrarse.jsp";
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}
}
