package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.LibroDAO;
import daos.TransaccionesManager;
import domain.EstadoLibro;
import domain.Libro;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioLibro {

	public ServicioLibro(){}
	
	public void InsertarLibro(Libro libro) throws ServiceException{
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			libroDAO.insertarLibro(libro);


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
	
	public void insertarLibroSugerido(Libro libro, Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			libroDAO.insertarLibroSugerido(libro, usuario);


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
	
	public void anadirABiblioteca(Usuario usuario, Libro libro) throws ServiceException{
		
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			libroDAO.anadirABiblioteca(usuario,libro);

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
	
	public void modificarLibro(Libro libro1, Libro libro2) throws ServiceException{
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			libroDAO.modificarLibro(libro1,libro2);


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
	
	public void ModificarPaginasBiblioteca(Usuario usuario, Libro libro, int numPag) throws ServiceException{
		
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			
			LibroDAO libroDAO = trans.getLibroDAO();
			libroDAO.ModificarPaginasBiblioteca(usuario, libro, numPag);

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
	
	public Libro recuperarLibro(Libro libro) throws ServiceException{
		
		TransaccionesManager trans = null;
		Libro lib = new Libro();
		
		try{
			
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			lib = libroDAO.recuperarLibro(libro);
			
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
		return lib;
	}
	
	public List<Libro> recuperarTodosLibro() throws ServiceException{
		TransaccionesManager trans = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			lista = libroDAO.recuperarTodosLibro();
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
	
	public List<Libro> recuperarLibroSeguidos(Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			lista = libroDAO.recuperarLibroSeguidos(usuario);
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
	
	public List<Libro> recuperarLibroReseñas(Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			lista = libroDAO.recuperarLibroReseñas(usuario);
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
	
	public List<Libro> recuperarTotalLibrosSugeridos() throws ServiceException{
		TransaccionesManager trans = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			lista = libroDAO.recuperarTotalLibrosSugeridos();
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
	
	public EstadoLibro RecuperarEstadoLibro(Usuario usuario, Libro libro) throws ServiceException{
		
		TransaccionesManager trans = null;
		EstadoLibro estado = null;;
		try{
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			estado = libroDAO.recuperarEstadoLibro(usuario,libro);
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
		return estado;
		
	}
	
	public List<Libro> busquedaLibro(String busqueda) throws ServiceException{
		TransaccionesManager trans = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			lista = libroDAO.busquedaLibros(busqueda);
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
	
	public List<Libro> busquedaLibroListado(String busquedaTitulo, String busquedaAutor) throws ServiceException{
		TransaccionesManager trans = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			trans = new TransaccionesManager();
			LibroDAO libroDAO = trans.getLibroDAO();
			lista = libroDAO.busquedaLibrosListados(busquedaTitulo, busquedaAutor);
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
	
	public void borrarLibro(Libro libro) throws ServiceException{
		TransaccionesManager trans = null;
		try{
			trans = new TransaccionesManager();
		LibroDAO libroDAO = trans.getLibroDAO();
		libroDAO.borrarLibro(libro);
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
