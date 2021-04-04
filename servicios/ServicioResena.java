package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.ResenaDAO;
import daos.TransaccionesManager;
import domain.Resena;
import domain.Usuario;
import domain.Libro;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioResena{
	
	public ServicioResena(){}

	public void insertarResena(Resena resena) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			ResenaDAO rese�aDAO = trans.getRese�aDAO();
			rese�aDAO.insertarRese�a(resena);
			trans.closeCommit();
		}catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L�gico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
	}
	
	public void modificarRese�a(Resena rese�a){}
	
	public void recuperarRese�a(Resena rese�a){}
	
	public List<Resena> recuperarRese�asAmigos(Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			trans = new TransaccionesManager();
			ResenaDAO rese�aDAO = trans.getRese�aDAO();
			lista = rese�aDAO.recuperarResenasAmigos(usuario);
			trans.closeCommit();
		}catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L�gico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return lista;	
	}
	
	public List<Resena> RecuperarRese�asLibro(Libro libro) throws ServiceException{
		
		TransaccionesManager trans = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			trans = new TransaccionesManager();
			ResenaDAO rese�aDAO = trans.getRese�aDAO();
			lista = rese�aDAO.RecuperarResenasLibro(libro);
			trans.closeCommit();
		}catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L�gico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return lista;	
	}
	
	public List<Resena> recuperarUltimasRese�asUsuarioActivo(Usuario usuario) throws ServiceException{
		TransaccionesManager trans = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			trans = new TransaccionesManager();
			ResenaDAO rese�aDAO = trans.getRese�aDAO();
			lista = rese�aDAO.recuperarUltimasResenasUsuarioActivo(usuario);
			trans.closeCommit();
		}catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L�gico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		return lista;
	}
	
	public void modificarPaginasBiblioteca(Libro libro,Usuario usuario, int paginas, String estado) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			ResenaDAO rese�aDAO = trans.getRese�aDAO();
			rese�aDAO.modificarPaginasBiblioteca(libro, usuario, paginas, estado);
			trans.closeCommit();
		}catch (DAOException e) {
			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L�gico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
	}
}
