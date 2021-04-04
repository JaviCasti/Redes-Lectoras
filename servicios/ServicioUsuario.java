package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.AutorDAO;
import daos.TransaccionesManager;
import daos.UsuarioDAO;
import domain.Autor;
import domain.NumReportes;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioUsuario {
	
	public ServicioUsuario(){}
	
	public void insertarUsuario(Usuario usuario) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.insertarUsuario(usuario);
			
			trans.closeCommit();
			
		}catch(DAOException e){

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}
			
		}
	}
	
	public void insertarAmigo(Usuario usuario1, Usuario usuario2) throws ServiceException{
		
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.insertarAmistad(usuario1,usuario2);
			usuarioDAO.insertarAmistad(usuario2,usuario1);
			
			trans.closeCommit();
			
		}catch(DAOException e){

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}
			
		}
		
	}
	
	public void insertarReporte(Usuario usuario1, Usuario usuario2) throws ServiceException{
		
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.insertarReporte(usuario1,usuario2);
			
			trans.closeCommit();
			
		}catch(DAOException e){

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
			
		}
		
	}
	
	public Usuario recuperarUsuario(Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans = null;
		
		try{
			trans = new TransaccionesManager();
			
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuario = usuarioDAO.recuperarUsuario(usuario);
			
			trans.closeCommit();
		}catch(DAOException e){
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return usuario;
	}
	
	public boolean recuperarAmigo(Usuario usuario1, Usuario usuario2) throws ServiceException{
		
		TransaccionesManager trans = null;
		boolean amistad = false;
		
		try{
			trans = new TransaccionesManager();
			
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			amistad = usuarioDAO.recuperarAmigo(usuario1, usuario2);
			
			trans.closeCommit();
		}catch(DAOException e){
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return amistad;
		
	}
	
	public List<NumReportes> recuperarReportesPorUsuario() throws ServiceException{
		
		TransaccionesManager trans = null;
		List<NumReportes> lista = new ArrayList<NumReportes>();
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			lista = usuarioDAO.recuperarReportesPorUsuario();
			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return lista;
		
	}
	
	public List<Usuario> busquedaUsuario(String busqueda, Usuario usuario) throws ServiceException{
		TransaccionesManager trans = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			lista = usuarioDAO.busquedaUsuario(busqueda,usuario);
			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return lista;
	}
	
	public List<Usuario> busquedaListadoUsuario(String busqueda) throws ServiceException{
		TransaccionesManager trans = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			lista = usuarioDAO.busquedaListadoUsuario(busqueda);
			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return lista;
	}
	
	public List<Usuario> mostrarTodosUsuarios() throws ServiceException{
		TransaccionesManager trans = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			lista = usuarioDAO.recuperarTodosUsuarios();
			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return lista;
	}
	
	public void banearUsuario(Usuario usuario) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.banearUsuario(usuario);
			
			trans.closeCommit();
			
		}catch(DAOException e){

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}	
		}
	}
	
	public void desbanearUsuario(Usuario usuario) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.desbanearUsuario(usuario);
			
			trans.closeCommit();
			
		}catch(DAOException e){

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}	
		}
	}
	
	public void borrarAmigo(Usuario usuario1, Usuario usuario2) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.borrarAmigo(usuario1,usuario2);
			usuarioDAO.borrarAmigo(usuario2,usuario1);
			
			trans.closeCommit();
			
		}catch(DAOException e){

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}
			
		}
		
	}
}
