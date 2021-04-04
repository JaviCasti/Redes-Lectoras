package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import recursos.DbQuery;
import recursos.Recursos;

import domain.Autor;
import domain.Usuario;
import exceptions.DAOException;

public class SeguimientoDAO {
	
	private static final String DB_ERR = "Error de la base de datos";

	private static final int MYSQL_DUPLICATE_PK = 1062;
	private static final int MYSQL_DELETE_FK = 2292;
	private static final int MYSQL_FALLO_FK = 1452;
	
	private Connection con;
	
	public SeguimientoDAO(Connection con){
		this.con = con;
	}
	
	public void insertarSeguimiento(Usuario usuario, Autor autor) throws DAOException{
		
		PreparedStatement st = null;
		
		try{
			st = con.prepareStatement(DbQuery.getInsertarSeguimiento());
			st.setString(1,usuario.getNombreUsuario());
			st.setString(2,autor.getNombre());
			st.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(DB_ERR, e);
		}
	}
	
	public boolean recuperarSeguimientoUsuarioAutor(Usuario usuario, Autor autor) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = con.prepareStatement(DbQuery.getRecuperarSeguimientoUsuarioAutor());
			st.setString(1, usuario.getNombreUsuario());
			st.setString(2, autor.getNombre());
			rs = st.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
	}
	
	public boolean borrarSeguimiento(Usuario usuario, Autor autor) throws DAOException{
			
		PreparedStatement preparedStatement = null;
		boolean borrado = false;
		try{
			preparedStatement = con.prepareStatement(DbQuery.getBorrarSeguimiento());
			preparedStatement.setString(1, usuario.getNombreUsuario());
			preparedStatement.setString(2, autor.getNombre());
			int x =preparedStatement.executeUpdate();
			borrado = true;
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(preparedStatement);
		}
		return borrado;
	}
}
