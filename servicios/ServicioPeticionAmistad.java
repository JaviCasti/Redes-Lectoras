package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.PeticionAmistadDAO;
import daos.TransaccionesManager;
import daos.UsuarioDAO;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioPeticionAmistad {
	
	public void insertarPeticionAmistad(Usuario usuario1, Usuario usuario2) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			PeticionAmistadDAO peticionAmistadDAO = trans.getPeticionAmistadDAO();
			peticionAmistadDAO.insertarPeticionAmistad(usuario1,usuario2);
			
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
	
	public boolean recuperarPeticionAmistad(Usuario usuario1, Usuario usuario2) throws ServiceException{
		
		TransaccionesManager trans = null;
		boolean peticion = false;
		
		try{
			trans = new TransaccionesManager();
			
			PeticionAmistadDAO peticionAmistadDAO = trans.getPeticionAmistadDAO();
			peticion = peticionAmistadDAO.recuperarPeticionAmistad(usuario1, usuario2);
			
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
		return peticion;
	}
	
	public List<Usuario> recuperarPeticionesAmistad(Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try{
			trans = new TransaccionesManager();
			
			PeticionAmistadDAO peticionAmistadDAO = trans.getPeticionAmistadDAO();
			lista = peticionAmistadDAO.recuperarPeticionesAmistad(usuario);
			
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
		return lista;
	}
		
	
	public void borrarPeticionAmistad(Usuario usuario1, Usuario usuario2) throws ServiceException{
		
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
			PeticionAmistadDAO peticionAmistadDAO = trans.getPeticionAmistadDAO();
			peticionAmistadDAO.borrarPeticionAmistad(usuario1,usuario2);
			
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
