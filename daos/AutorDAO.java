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
import domain.Libro;
import exceptions.DAOException;

public class AutorDAO {
	
	private static final String DB_ERR = "Error de la base de datos";

	private static final int MYSQL_DUPLICATE_PK = 1062;
	private static final int MYSQL_DELETE_FK = 2292;
	private static final int MYSQL_FALLO_FK = 1452;
	
	private Connection con;
	
	public AutorDAO(Connection con){
		this.con = con;
	}
	
	public void insertarAutor(Autor autor) throws DAOException{
		PreparedStatement st = null;
		
		try{
			st = con.prepareStatement(DbQuery.getInsertarAutor());
			st.setString(1, autor.getNombre());
			st.setString(2, autor.getPaisNacimiento());
			st.setDate(3, autor.getFechaNacimiento());
			st.setString(4, autor.getDescripcion());
			st.setString(5, autor.getExtension());
			st.executeUpdate();
			
		}catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
				throw new DAOException(" El autor ya existe");
			}else if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else{
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
	}
	
	public Autor recuperarAutor(Autor autor) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		Autor aut = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getRecuperarAutor());
			st.setString(1,autor.getNombre());
			rs=st.executeQuery();
			if(rs.next()){
				aut = new Autor(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5));
			}
			
		}catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return aut;
	}
	
	public List<Autor> recuperarTodosAutor() throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Autor> lista = new ArrayList<Autor>();
		try{
			
			st = con.prepareStatement(DbQuery.getRecuperarTodosAutor());
			rs = st.executeQuery();
			
			while(rs.next()){
				lista.add(new Autor(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5)));
			}
			
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
		
	}
	
	public List<Autor> busquedaAutor(String busqueda) throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Autor> lista = new ArrayList<Autor>();
		try{
			st = con.prepareStatement(DbQuery.getBusquedaAutor());
			st.setString(1,"%"+busqueda+"%");
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Autor(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
		
	}
	
	public void modificarAutor(Autor autor1, Autor autor2) throws DAOException{
		PreparedStatement st = null;
		
		try{
			st = con.prepareStatement(DbQuery.getModificarAutor());
			st.setString(1, autor1.getNombre());
			st.setString(2, autor1.getPaisNacimiento());
			st.setDate(3, autor1.getFechaNacimiento());
			st.setString(4, autor1.getDescripcion());
			st.setString(5, autor2.getNombre());
			st.executeUpdate();
			
		}catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
				throw new DAOException(" El autor ya existe");
			}else if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else{
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
	}
	
	public void borrarAutor(Autor autor) throws DAOException{
		
		PreparedStatement st = null;
		
		try{
			st = con.prepareStatement(DbQuery.getBorrarAutor());
			st.setString(1, autor.getNombre());
			st.executeUpdate();
			
		}catch (SQLException e) {
			if (e.getErrorCode() ==MYSQL_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else{
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
	}
}
