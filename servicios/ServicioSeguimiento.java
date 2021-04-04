package servicios;

import daos.SeguimientoDAO;
import daos.TransaccionesManager;
import domain.Autor;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioSeguimiento {
	
	public ServicioSeguimiento(){}
	
	public void insertarSeguimiento(Usuario usuario, Autor autor) throws ServiceException{
		
		TransaccionesManager trans = null;
		
		try{
			
			trans = new TransaccionesManager();
			SeguimientoDAO seguimientoDAO = trans.getSeguimientoDAO();
			seguimientoDAO.insertarSeguimiento(usuario, autor);
			trans.commit();
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
	
	public boolean recuperarSeguimientoUsuarioAutor(Usuario usuario, Autor autor) throws ServiceException{
		
		TransaccionesManager trans = null;
		boolean seguido;
		try{
			
			trans = new TransaccionesManager();
			SeguimientoDAO seguimientoDAO = trans.getSeguimientoDAO();
			seguido = seguimientoDAO.recuperarSeguimientoUsuarioAutor(usuario, autor);
			
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
		return seguido;
	}
	
	public boolean borrarSeguimiento(Usuario usuario, Autor autor) throws ServiceException{
		TransaccionesManager trans = null;
		boolean borrado;
		try{
			
			trans = new TransaccionesManager();
			SeguimientoDAO seguimientoDAO = trans.getSeguimientoDAO();
			borrado = seguimientoDAO.borrarSeguimiento(usuario, autor);
			trans.commit();
			
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
		return borrado;
	}
}
