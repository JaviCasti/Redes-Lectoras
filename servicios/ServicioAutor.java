package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.AutorDAO;
import daos.TransaccionesManager;
import domain.Autor;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioAutor {

	public ServicioAutor(){}
	
	public void insertarAutor(Autor autor) throws ServiceException{
		
		TransaccionesManager trans = null;
		
		try{
			trans = new TransaccionesManager();
			AutorDAO autorDAO = trans.getAutorDAO();
			autorDAO.insertarAutor(autor);
			trans.closeCommit();
			
		}catch (DAOException e) {
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
	
	public void modificarAutor(Autor autor1, Autor autor2) throws ServiceException{
		
		TransaccionesManager trans = null;
		
		try{
			trans = new TransaccionesManager();
			AutorDAO autorDAO = trans.getAutorDAO();
			autorDAO.modificarAutor(autor1,autor2);
			trans.closeCommit();
			
		}catch (DAOException e) {
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
	
	public Autor recuperarAutor(Autor autor) throws ServiceException{
		
		TransaccionesManager trans = null;
		Autor aut = new Autor();
		
		try{
			
			trans = new TransaccionesManager();
			AutorDAO autorDAO = trans.getAutorDAO();
			aut = autorDAO.recuperarAutor(autor);
			
		}catch (DAOException e) {
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
		return aut;
	}
	
	public List<Autor> recuperarTodosAutor() throws ServiceException{
		TransaccionesManager trans = null;
		List<Autor> lista = new ArrayList<Autor>();
		try{
			trans = new TransaccionesManager();
			AutorDAO autorDAO = trans.getAutorDAO();
			lista = autorDAO.recuperarTodosAutor();
			trans.closeCommit();
		}catch (DAOException e) {
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
	
	public List<Autor> busquedaAutor(String busqueda) throws ServiceException{
		TransaccionesManager trans = null;
		List<Autor> lista = new ArrayList<Autor>();
		try{
			trans = new TransaccionesManager();
			AutorDAO autorDAO = trans.getAutorDAO();
			lista = autorDAO.busquedaAutor(busqueda);
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
	
	public void borrarAutor(Autor autor) throws ServiceException{
		
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			AutorDAO autorDAO = trans.getAutorDAO();
			autorDAO.borrarAutor(autor);
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
	}
	
}
