package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;

import domain.Libro;
import domain.Resena;
import domain.Usuario;
import exceptions.DAOException;

public class ResenaDAO {

	private static final String DB_ERR = "Error de la base de datos";

	private static final int MYSQL_DUPLICATE_PK = 1062;
	private static final int MYSQL_DELETE_FK = 2292;
	private static final int MYSQL_FALLO_FK = 1452;
	
	private Connection con;
	
	public ResenaDAO(Connection con){
		this.con = con;
	}
	
	public void insertarReseña(Resena reseña) throws DAOException{
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getInsertarResena());
			st.setString(1,reseña.getUsuario().getNombreUsuario());
			st.setString(2, reseña.getLibro().getTitulo());
			st.setTimestamp(3, reseña.getFechaHora());
			st.setDouble(4, reseña.getEstrellas());
			st.setString(5, reseña.getDescripcion());
			st.setInt(6, reseña.getNumPaginas());
			st.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(st);
		}
	}
	
	public List<Resena> recuperarResenasAmigos(Usuario usuario) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarResenasAmigos());
			st.setString(1,usuario.getNombreUsuario());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Resena(new Usuario(rs.getString(1)),new Libro(rs.getString(2)), rs.getTimestamp(3),rs.getDouble(4),rs.getString(5),rs.getInt(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Resena> RecuperarResenasLibro(Libro libro) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarResenasLibro());
			st.setString(1,libro.getTitulo());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Resena(new Usuario(rs.getString(1)),new Libro(rs.getString(2)), rs.getTimestamp(3),rs.getDouble(4),rs.getString(5),rs.getInt(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public List<Resena> recuperarUltimasResenasUsuarioActivo(Usuario usuario) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Resena> lista = new ArrayList<Resena>();
		try{
			st = con.prepareStatement(DbQuery.getRecuperarUltimasResenasUsuarioActivo());
			st.setString(1,usuario.getNombreUsuario());
			rs = st.executeQuery();
			while(rs.next()){
				lista.add(new Resena(new Usuario(rs.getString(1)),new Libro(rs.getString(2)), rs.getTimestamp(3),rs.getDouble(4),rs.getString(5),rs.getInt(6)));
			}
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return lista;
	}
	
	public void modificarPaginasBiblioteca(Libro libro,Usuario usuario, int paginas, String estado) throws DAOException{
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(DbQuery.getModificarPaginasBiblioteca());
			st.setString(1,Integer.toString(paginas));
			st.setString(2, estado);
			st.setString(3, usuario.getNombreUsuario());
			st.setString(4, libro.getTitulo());
			System.out.println(st);
			st.executeUpdate();
		}catch(SQLException e){
			throw new DAOException(DB_ERR,e);
		}finally{
			Recursos.closePreparedStatement(st);
		}
	}
}
