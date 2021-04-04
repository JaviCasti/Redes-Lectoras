package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;


import daos.TransaccionesManager;
import daos.UsuarioDAO;
import domain.Usuario;
import exceptions.DAOException;

/**
 * Servlet implementation class iniciarSesion
 */
@WebServlet("/iniciarSesion")
public class iniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iniciarSesion() {
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
		TransaccionesManager trans = null;
		Usuario usuario = new Usuario(request.getParameter("nombreUsuario"));
		String contraseña = DigestUtils.md5Hex(request.getParameter("contraseña"));
		String salida = null;
		UsuarioDAO usuarioDAO = null;
		try{
			trans = new TransaccionesManager();
			
			usuarioDAO = trans.getUsuarioDAO();
			usuario = usuarioDAO.recuperarUsuario(usuario);
			
				try{
					
					if(usuario.getBaneado().equals("1")){
						salida = "/PaginaError.jsp";
						request.setAttribute("error","Estas baneado");
						getServletContext().getRequestDispatcher(salida).forward(request, response); 
					}else if(contraseña.equals(usuario.getContraseña())){
						System.out.println(usuario.getBaneado());
						HttpSession sesion=request.getSession(false);
						if (sesion!=null){
							sesion.invalidate();
						}
						sesion=request.getSession(true);
						sesion.setAttribute("nombre", request.getParameter("nombreUsuario"));
						sesion.setAttribute("tipoUsuario", usuario.getTipoUsuario());
						if(sesion.getAttribute("tipoUsuario").equals("0")){
						salida = "/cargarIndexUsuario";
						getServletContext().getRequestDispatcher(salida).forward(request, response); 
						}else
							response.sendRedirect(request.getContextPath()+"/PaginaAdmin.jsp");
					}else{
						request.setAttribute("error", "La contraseña no es valida, vuelva a intentarlo");
						salida = "/IniciarSesion.jsp";
						getServletContext().getRequestDispatcher(salida).forward(request, response); 
					}
				}catch(NullPointerException e){
					request.setAttribute("error","El usuario no existe, vuelva a intentarlo");
					salida = "/IniciarSesion.jsp";
					getServletContext().getRequestDispatcher(salida).forward(request, response); 
				}
		}catch(DAOException e){}
	}

}
