package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import domain.Usuario;
import exceptions.DAOException;

public class PeticionAmistadDAO {
	
	private static final String DB_ERR = "Error de la base de datos";

	private static final int MYSQL_DUPLICATE_PK = 1062;
	private static final int MYSQL_DELETE_FK = 2292;
	private static final int MYSQL_FALLO_FK = 1452;
	
	private Connection con;
	
	public PeticionAmistadDAO(Connection con){
		this.con=con;
	}
	
	public void insertarPeticionAmistad(Usuario usu1, Usuario usu2) throws DAOException{
		
		PreparedStatement st = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getInsertarPeticionAmistad());
			
			st.setString(1, usu1.getNombreUsuario());
			st.setString(2, usu2.getNombreUsuario());
			
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
	
	public boolean recuperarPeticionAmistad(Usuario usuario1, Usuario usuario2) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			st = con.prepareStatement(DbQuery.getRecuperarPeticionAmistad());
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
	
	public List<Usuario> recuperarPeticionesAmistad(Usuario usuario) throws DAOException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<Usuario>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarPeticionesAmistad());
			st.setString(1,usuario.getNombreUsuario());
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
	
	public void borrarPeticionAmistad(Usuario usuario1, Usuario usuario2) throws DAOException{
		
		PreparedStatement st = null;
		
		try{
			
			st = con.prepareStatement(DbQuery.getBorrarPeticionAmistad());
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
