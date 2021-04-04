package recursos;

public class DbQuery {
	
		//Usuarios	
		private static final String RecuperarUsuario = "select nombre_usuario, contraseña, tipo_usuario, baneado from usuarios where nombre_usuario = ?";
		private static final String RecuperarAmigo = "select '1' from amigos where usuario1 = ? and usuario2 = ?";
		private static final String RecuperarTodosUsuario = "select nombre_usuario, contraseña, tipo_usuario, baneado from usuarios";
		private static final String InsertarUsuario = "insert into usuarios(nombre_usuario, contraseña, tipo_usuario, baneado) values(?,?,?,?)";
		private static final String InsertarAmigo = "insert into amigos(usuario1, usuario2) values (?,?)";
		private static final String ModificarUsuario = "update usuarios set contraseña=?,tipo_usuario=? where nombre_usuario=?";
		private static final String BusquedaUsuario = "select nombre_usuario from usuarios where tipo_usuario = 0 and nombre_usuario like ? and nombre_usuario <> ?";
		private static final String BusquedaListadoUsuario = "select nombre_usuario, contraseña, tipo_usuario, baneado from usuarios where tipo_usuario = 0 and nombre_usuario like ?";
		private static final String BanearUsuario = "update usuarios set baneado = 1 where nombre_usuario = ?";
		private static final String DesbanearUsuario = "update usuarios set baneado = 0 where nombre_usuario = ?";
		private static final String BorrarAmigo = "delete from amigos where usuario1 = ? and usuario2 = ?";
		
		//Libros 	
		private static final String RecuperarLibro = "select titulo, autor, num_pag, resumen, fecha_añadido, extension from libros where titulo = ?";
		private static final String RecuperarTodoslibro = "select titulo, autor, num_pag, resumen, fecha_añadido, extension from libros order by 5 desc";
		private static final String RecuperarLibroReseñas = "select titulo, autor, num_pag, resumen, fecha_añadido, extension from libros where titulo in(select libro from resenas where usuario in (select usuario1 from amigos where usuario2 = ?))";
		private static final String RecuperarLibroSeguidos = "select titulo, autor, num_pag, resumen, fecha_añadido, extension from libros where autor in (select autor from seguidores where usuario=?)order by 5 desc";
		private static final String BusquedaLibro = "select titulo, autor, num_pag, resumen, fecha_añadido, extension from libros where titulo like ? order by 1";
		private static final String BusquedaLibroListado = "select titulo, autor, num_pag, resumen, fecha_añadido, extension from libros where titulo like ? and autor like ? order by 1";
		private static final String InsertarLibro = "insert into libros(titulo,autor,num_pag,resumen,fecha_añadido, extension) values(?,?,?,?,?,?)";
		private static final String ModificarLibro = "update libros set titulo = ? ,autor=? ,num_pag = ? ,resumen = ? , fecha_añadido = ?, extension=? where titulo = ?";
		private static final String BorrarLibro = "delete from libros where titulo = ?";
		
		//Libros de usuario
		private static final String AnadirABiblioteca = "insert into libros_usuario values (?,?,0,0)";
		private static final String ModificarPaginasBiblioteca = "update libros_usuario set num_paginas = ?, relacion = ? where usuario = ? and libro = ?";
		private static final String RecuperarEstadoLibro = "select relacion, num_paginas from libros_usuario where usuario = ? and libro = ?"; 
		
		//Autor
		private static final String RecuperarAutor = "select nombre, pais_nacimiento, fecha_nacimiento, descripcion, extension from autores where nombre=?";
		private static final String RecuperarTodosAutor = "select nombre, pais_nacimiento, fecha_nacimiento, descripcion, extension from autores";
		private static final String BusquedaAutor = "select nombre, pais_nacimiento, fecha_nacimiento, descripcion, extension from autores where nombre like ? order by 1";
		private static final String ModificarAutor = "update autores set nombre = ?, pais_nacimiento = ?, fecha_nacimiento = ?, descripcion = ? where nombre = ?";
		private static final String InsertarAutor = "insert into autores values (?,?,?,?,?)";
		private static final String BorrarAutor = "delete from autores where nombre = ? ";
		
		//Reseñas
		private static final String RecuperarResena = "select usuario, libro, fecha_hora, estrellas, descripcion, num_paginas from resenas where usuario=? and libro=? and fecha_hora=?";
		private static final String RecuperarResenasAmigos = "select usuario, libro, fecha_hora, estrellas, descripcion, num_paginas from resenas where usuario in (select usuario1 from amigos where usuario2 = ?) order by 3 desc";
		private static final String RecuperarResenasLibro = "select usuario, libro, fecha_hora, estrellas, descripcion, num_paginas from resenas where libro = ? order by 3 desc";
		private static final String RecuperarUltimasResenasUsuarioActivo = "select usuario, libro, fecha_hora, estrellas, descripcion, num_paginas from resenas where usuario = ? order by 3 limit 2";
		private static final String InsertarResena = "insert into resenas values (?,?,?,?,?,?)";
		
		//Seguimientos
		private static final String RecuperarSeguimientos = "select autor from seguidores where usuario=?";
		private static final String RecuperarSeguimientoUsuarioAutor = "select '1' from seguidores where usuario = ? and autor = ?";
		private static final String InsertarSeguimiento = "insert into seguidores(usuario, autor) values(?, ?)";
		private static final String BorrarSeguimiento = "delete from seguidores where usuario = ? and autor = ?";
		
		//Libros Sugeridos
		private static final String InsertarLibroSugerido = "insert into libros_sugeridos values(null,?,?,?,?,?,?)";
		private static final String RecuperarTotalLibrosSugeridos = "select titulo, autor, num_pag, resumen, fecha_publicacion from libros_sugeridos";
		private static final String BorrarLibrosSugeridos = "delete from libros_sugeridos where titulo = ?";
		
		//Reportes
		private static final String insertarReporte = "insert into reportes value(null,?,?)";
		private static final String RecuperarReportesPorUsuario = "select reportado, count(emisor) from reportes group by reportado order by 2 desc";
		
		//Peticiones Amistad
		private static final String InsertarPeticionAmistad = "insert into solicitudes_amistad values(?,?)";
		private static final String RecuperarPeticionAmistad = "select '1' from solicitudes_amistad where usuario1 = ? and usuario2 = ?";
		private static final String RecuperarPeticionesAmistad = "select usuario1 from solicitudes_amistad where usuario2 = ?";
		private static final String BorrarPeticionAmistad = "delete from solicitudes_amistad where usuario1 = ? and usuario2 = ?";
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
		
	//Usuarios	
	public static String getRecuperarUsuario(){
		return RecuperarUsuario;
	}
	
	public static String getRecuperarAmigo(){
		return RecuperarAmigo;
	}
	
	public static String getRecuperarTodosUsuarios(){
		return RecuperarTodosUsuario;
	}
	
	public static String getRecuperarLibroSeguidos(){
		return RecuperarLibroSeguidos;
	}
	
	public static String getInsertarUsuario(){
		return InsertarUsuario;
	}
	
	public static String getInsertarAmigo(){
		return InsertarAmigo;
	}
	
	public static String getModificarUsuario(){
		return ModificarUsuario;
	}
	
	public static String getBusquedaUsuario(){
		return BusquedaUsuario;
	}
	
	public static String getBusquedaListadoUsuario(){
		return BusquedaListadoUsuario;
	}
	
	public static String getBanearUsuario(){
		return BanearUsuario;
	}
	
	public static String getDesbanearUsuario(){
		return DesbanearUsuario;
	}
	
	public static String getBorrarAmigo(){
		return BorrarAmigo;
	}

	//Libros
	public static String getRecuperarLibro(){
		return RecuperarLibro;
	}
	
	public static String getRecuperarTodosLibro(){
		return RecuperarTodoslibro;
	}
	
	public static String getRecuperarLibroReseñas(){
		return RecuperarLibroReseñas;
	}
	
	public static String getBusquedaLibro(){
		return BusquedaLibro;
	}
	
	public static String getBusquedaLibroListado(){
		return BusquedaLibroListado;
	}
	
	public static String getInsertarLibro(){
		return InsertarLibro;
	}
	
	public static String getModificarLibro(){
		return ModificarLibro;
	}
	
	public static String getBorrarLibro(){
		return BorrarLibro;
	}
	
	//Libros de usuario
	public static String getAnadirABiblioteca(){
		return AnadirABiblioteca;
	}
	
	public static String getModificarPaginasBiblioteca(){
		return ModificarPaginasBiblioteca;
	}
	
	public static String getRecuperarEstadoLibro(){
		return RecuperarEstadoLibro;
	}
	
	//Autores
	public static String getRecuperarAutor(){
		return RecuperarAutor;
	}
	
	public static String getRecuperarTodosAutor(){
		return RecuperarTodosAutor;
	}
	
	public static String getBusquedaAutor(){
		return BusquedaAutor;
	}
	
	public static String getModificarAutor(){
		return ModificarAutor;
	}
	
	public static String getInsertarAutor(){
		return InsertarAutor;
	}
	
	public static String getBorrarAutor(){
		return BorrarAutor;
	}
	
	//Reseñas
	public static String getRecuperarResena(){
		return RecuperarResena;
	}
	
	public static String getRecuperarResenasAmigos(){
		return RecuperarResenasAmigos;
	}
	public static String getRecuperarResenasLibro(){
		return RecuperarResenasLibro;
	}
	
	public static String getRecuperarUltimasResenasUsuarioActivo(){
		return RecuperarUltimasResenasUsuarioActivo;
	}
	
	public static String getInsertarResena(){
		return InsertarResena;
	}
	
	//Seguimientos
	public static String getRecuperarSeguimientos(){
		return RecuperarSeguimientos;
	}
	
	public static String getRecuperarSeguimientoUsuarioAutor(){
		return RecuperarSeguimientoUsuarioAutor;
	}
	
	public static String getInsertarSeguimiento(){
		return InsertarSeguimiento;
	}
	
	public static String getBorrarSeguimiento(){
		return BorrarSeguimiento;
	}
	
	//Libros Sugeridos
	public static String getInsertarLibroSugerido(){
		return InsertarLibroSugerido;
	}
	
	public static String getRecuperarTotalLibrosSugeridos(){
		return RecuperarTotalLibrosSugeridos;
	}
	
	public static String getBorrarLibrosSugeridos(){
		return BorrarLibrosSugeridos;
	}
	
	//Reportes
	public static String getInsertarReporte(){
		return insertarReporte;
	}
	
	public static String getRecuperarReportesPorUsuario(){
		return RecuperarReportesPorUsuario;
	}
	
	//Peticiones Amistad
	public static String getInsertarPeticionAmistad(){
		return InsertarPeticionAmistad;
	}
	
	public static String getRecuperarPeticionAmistad(){
		return RecuperarPeticionAmistad;
	}
	
	public static String getRecuperarPeticionesAmistad(){
		return RecuperarPeticionesAmistad;
	}
	
	public static String getBorrarPeticionAmistad(){
		return BorrarPeticionAmistad;
	}
}
