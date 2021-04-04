package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import domain.NumReportes;
import domain.Usuario;
import exceptions.DAOException;

public class UsuarioDAO {

	private static final String DB_ERR = "Error de la base de datos";

	private static final int MYSQL_DUPLICATE_PK = 1062;
	private static final int MYSQL_DELETE_FK = 2292;
	private static final int MYSQL_FALLO_FK = 1452;
	
	private Connection con;
	
	public UsuarioDAO(Connection con){
		this.con=con;
	}
	
	public void insertarUsuario(Usuario usuario)throws DAOException{
		
		PreparedStatement st = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getInsertarUsuario());
			
			st.setString(1,usuario.getNombreUsuario());
			st.setString(2, usuario.getContraseña());
			st.setString(3, usuario.getTipoUsuario());
			st.setString(4, usuario.getBaneado());
			
			st.executeUpdate();
		}catch(SQLException e){
			if(e.getErrorCode()==MYSQL_DUPLICATE_PK){
				throw new DAOException("El usuario ya exite");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		}finally{
			Recursos.closePreparedStatement(st);
		}
	}
	
	public void insertarAmistad(Usuario usuario1, Usuario usuario2) throws DAOException{
		
		PreparedStatement st = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getInsertarAmigo());
			
			st.setString(1, usuario1.getNombreUsuario());
			st.setString(2, usuario2.getNombreUsuario());
			
			st.executeUpdate();
			
		}catch(SQLException e){
			if(e.getErrorCode()==MYSQL_DUPLICATE_PK){
				throw new DAOException("El usuario ya exite");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		}finally{
			Recursos.closePreparedStatement(st);
		}
		
	}
	
	public void insertarReporte(Usuario usuario1, Usuario usuario2) throws DAOException{
		
		PreparedStatement st = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getInsertarReporte());
			
			st.setString(1, usuario1.getNombreUsuario());
			st.setString(2, usuario2.getNombreUsuario());
			
			st.executeUpdate();
			
		}catch(SQLException e){
				throw new DAOException(DB_ERR, e);
		}finally{
			Recursos.closePreparedStatement(st);
		}
		
		
	}
	
	public List<Usuario> busquedaUsuario(String busqueda, Usuario usuario) throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try{
			st = con.prepareStatement(DbQuery.getBusquedaUsuario());
			st.setString(1,"%"+busqueda+"%");
			st.setString(2, usuario.getNombreUsuario());
			System.out.println(st);
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Usuario(rs.getString(1)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Usuario> busquedaListadoUsuario(String busqueda) throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try{
			st = con.prepareStatement(DbQuery.getBusquedaListadoUsuario());
			st.setString(1,"%"+busqueda+"%");
			System.out.println(st);
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
		
	}
	
	public boolean recuperarAmigo(Usuario usuario1, Usuario usuario2) throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = con.prepareStatement(DbQuery.getRecuperarAmigo());
			st.setString(1,usuario1.getNombreUsuario());
			st.setString(2,usuario2.getNombreUsuario());
			rs = st.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
		Recursos.closeResultSet(rs);
		Recursos.closePreparedStatement(st);
		}
		
	}
	
	public Usuario recuperarUsuario(Usuario usuario) throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		Usuario usu = null;
		
		try{
			st = con.prepareStatement(DbQuery.getRecuperarUsuario());
			st.setString(1,usuario.getNombreUsuario());
			rs = st.executeQuery();
			if(rs.next()){
				usu = new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
		Recursos.closeResultSet(rs);
		Recursos.closePreparedStatement(st);
		}
		return usu;
	}
	
	public List<NumReportes> recuperarReportesPorUsuario() throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<NumReportes> lista = new ArrayList<NumReportes>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarReportesPorUsuario());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new NumReportes(rs.getString(1),Integer.parseInt(rs.getString(2))));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Usuario> recuperarTodosUsuarios() throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarTodosUsuarios());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
		
	}
	
	public void banearUsuario(Usuario usuario) throws DAOException{
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getBanearUsuario());
			st.setString(1, usuario.getNombreUsuario());
			st.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(st);
		}
	}
	
	public void desbanearUsuario(Usuario usuario) throws DAOException{
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getDesbanearUsuario());
			st.setString(1, usuario.getNombreUsuario());
			st.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(st);
		}
	}
	
	public void borrarAmigo(Usuario usuario1, Usuario usuario2) throws DAOException{
		PreparedStatement st = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getBorrarAmigo());
			st.setString(1,usuario1.getNombreUsuario());
			st.setString(2,usuario2.getNombreUsuario());
			st.executeUpdate();
			
		} catch (SQLException e) {
			
			throw new DAOException(DB_ERR, e);
			
		} finally {
			
		Recursos.closePreparedStatement(st);
		
		}
	}
}
