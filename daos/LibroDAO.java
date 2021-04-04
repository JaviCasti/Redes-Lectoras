package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;

import domain.Autor;
import domain.EstadoLibro;
import domain.Libro;
import domain.Usuario;
import exceptions.DAOException;

public class LibroDAO {
	
	private static final String DB_ERR = "Error de la base de datos";

	private static final int MYSQL_DUPLICATE_PK = 1062;
	private static final int MYSQL_DELETE_FK = 2292;
	private static final int MYSQL_FALLO_FK = 1452;
	
	private Connection con;
	
	public LibroDAO(Connection con){
		this.con = con;
	}
	
	public void insertarLibro(Libro libro) throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs = null;
		try{
			
			st = con.prepareStatement(DbQuery.getInsertarLibro());
			st.setString(1, libro.getTitulo());
			st.setString(2,libro.getAutor().getNombre());
			st.setInt(3, libro.getNumPag());
			st.setString(4, libro.getResumen());
			st.setTimestamp(5, libro.getFechaAnadido());
			st.setString(6, libro.getExtension());
			try{
				sti = con.prepareStatement(DbQuery.getRecuperarAutor());
				sti.setString(1, libro.getAutor().getNombre());
				rs=sti.executeQuery();
				  if(!rs.next())	
				  throw new DAOException("El Autor del Libro no existe");
				 }  finally {
					  Recursos.closeResultSet( rs);	
				 }
			System.out.println(st);
			st.executeUpdate();
		
		}catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
				throw new DAOException("El Libro ya existe");
			}else if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
	}
	
	public void insertarLibroSugerido(Libro libro, Usuario usuario) throws DAOException{
		
		PreparedStatement st = null;
		try{
			
			st = con.prepareStatement(DbQuery.getInsertarLibroSugerido());
			st.setString(1,libro.getTitulo());
			st.setString(2,libro.getAutor().getNombre());
			st.setInt(3, libro.getNumPag());
			st.setString(4, libro.getResumen());
			st.setTimestamp(5, libro.getFechaAnadido());
			st.setString(6,usuario.getNombreUsuario());
			st.executeUpdate();
			
		}catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
				throw new DAOException("El Libro ya existe");
			}else if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
	}
	
	public void anadirABiblioteca(Usuario usuario, Libro libro) throws DAOException{
		
		PreparedStatement st = null;
		try{
			
			st = con.prepareStatement(DbQuery.getAnadirABiblioteca());
			st.setString(1,usuario.getNombreUsuario());
			st.setString(2,libro.getTitulo());
			st.executeUpdate();
			
		}catch (SQLException e) {
			if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		
	}
	
	public Libro recuperarLibro(Libro libro) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		Libro lib = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getRecuperarLibro());
			st.setString(1, libro.getTitulo());
			rs=st.executeQuery();
			if(rs.next()){
				lib = new Libro(rs.getString(1),new Autor(rs.getString(2)),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6));
			}
		}catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lib;
	}
	
	public List<Libro> recuperarTodosLibro() throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarTodosLibro());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Libro(rs.getString(1),new Autor(rs.getString(2)),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Libro> recuperarLibroSeguidos(Usuario usuario) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarLibroSeguidos());
			st.setString(1,usuario.getNombreUsuario());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Libro(rs.getString(1),new Autor(rs.getString(2)),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Libro> recuperarLibroReseñas(Usuario usuario) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarLibroReseñas());
			st.setString(1,usuario.getNombreUsuario());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Libro(rs.getString(1),new Autor(rs.getString(2)),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Libro> busquedaLibros(String busqueda) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			st = con.prepareStatement(DbQuery.getBusquedaLibro());
			st.setString(1,"%"+busqueda+"%");
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Libro(rs.getString(1),new Autor(rs.getString(2)),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Libro> busquedaLibrosListados(String busquedaTitulo, String busquedaAutor) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			st = con.prepareStatement(DbQuery.getBusquedaLibroListado());
			st.setString(1,"%"+busquedaTitulo+"%");
			st.setString(2,"%"+busquedaAutor+"%");
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Libro(rs.getString(1),new Autor(rs.getString(2)),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),rs.getString(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Libro> recuperarTotalLibrosSugeridos() throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Libro> lista = new ArrayList<Libro>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarTotalLibrosSugeridos());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Libro(rs.getString(1),new Autor(rs.getString(2)),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),""));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public Libro RecuperarFilaElegida(String fila) throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		Libro lib = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getRecuperarLibro());
			st.setString(1, fila);
			rs=st.executeQuery();
			if(rs.next()){
				lib = new Libro(rs.getString(2),new Autor(rs.getString(3)),rs.getInt(4),rs.getString(5),rs.getTimestamp(6),rs.getString(7));
			}
		}catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lib;
		
	}
	
	public EstadoLibro recuperarEstadoLibro(Usuario usuario, Libro libro) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		EstadoLibro estado = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getRecuperarEstadoLibro());
			st.setString(1, usuario.getNombreUsuario());
			st.setString(2, libro.getTitulo());
			rs=st.executeQuery();
			if(rs.next()){
				estado = new EstadoLibro(rs.getString(1),Integer.parseInt(rs.getString(2)));
			}
		}catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return estado;
		
	}
	
	public void modificarLibro(Libro libro2, Libro libro1) throws DAOException{
		
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getModificarLibro());
			st.setString(1,libro1.getTitulo());
			st.setString(2,libro1.getAutor().getNombre());
			st.setInt(3, libro1.getNumPag());
			st.setString(4, libro1.getResumen());
			st.setTimestamp(5, libro1.getFechaAnadido());
			st.setString(6, libro1.getExtension());
			st.setString(7, libro1.getTitulo());
			st.executeUpdate();
			
			
		}catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
				throw new DAOException("El Libro ya existe");
			}else if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		
	}
	
	public void ModificarPaginasBiblioteca(Usuario usuario, Libro libro, int numPag) throws DAOException{
		
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getModificarLibro());
			st.setString(1,usuario.getNombreUsuario());
			st.setString(2,libro.getTitulo());
			st.setInt(3, numPag);
			st.executeUpdate();
			
		}catch (SQLException e) {
			if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		
	}
	
	public void borrarLibro(Libro libro) throws DAOException{
		
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getBorrarLibro());
			st.setString(1,libro.getTitulo());
			st.executeUpdate();

		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(st);
		}
	}
	
	public void borrarLibrosSugeridos(Libro libro) throws DAOException{
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getBorrarLibrosSugeridos());
			st.setString(1,libro.getTitulo());
			st.executeUpdate();

		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(st);
		}
	}
}
