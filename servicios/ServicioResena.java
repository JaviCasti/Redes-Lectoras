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
			ResenaDAO reseñaDAO = trans.getReseñaDAO();
			reseñaDAO.insertarReseña(resena);
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
	
	public void modificarReseña(Resena reseña){}
	
	public void recuperarReseña(Resena reseña){}
	
	public List<Resena> recuperarReseñasAmigos(Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			trans = new TransaccionesManager();
			ResenaDAO reseñaDAO = trans.getReseñaDAO();
			lista = reseñaDAO.recuperarResenasAmigos(usuario);
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
	
	public List<Resena> RecuperarReseñasLibro(Libro libro) throws ServiceException{
		
		TransaccionesManager trans = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			trans = new TransaccionesManager();
			ResenaDAO reseñaDAO = trans.getReseñaDAO();
			lista = reseñaDAO.RecuperarResenasLibro(libro);
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
	
	public List<Resena> recuperarUltimasReseñasUsuarioActivo(Usuario usuario) throws ServiceException{
		TransaccionesManager trans = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			trans = new TransaccionesManager();
			ResenaDAO reseñaDAO = trans.getReseñaDAO();
			lista = reseñaDAO.recuperarUltimasResenasUsuarioActivo(usuario);
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
	
	public void modificarPaginasBiblioteca(Libro libro,Usuario usuario, int paginas, String estado) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			ResenaDAO reseñaDAO = trans.getReseñaDAO();
			reseñaDAO.modificarPaginasBiblioteca(libro, usuario, paginas, estado);
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
}
