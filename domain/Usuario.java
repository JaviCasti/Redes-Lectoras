package domain;

import exceptions.DomainException;
import util.Validator;

public class Usuario {
	
	private String nombreUsuario;
	private String contraseña;
	private String tipoUsuario;
	private String baneado;
	
	public Usuario(){}
	
	public Usuario(String nombreUsuario){this.nombreUsuario = nombreUsuario;}
	
	public Usuario(String nombreUsuario, String contraseña, String tipoUsuario, String baneado){
		
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.tipoUsuario = tipoUsuario;
		this.baneado = baneado;
	}
	
	public Usuario(Usuario usuario){
		setNombreUsuario(usuario.nombreUsuario);
		setContraseña(usuario.contraseña);
		setTipoUsuario(usuario.tipoUsuario);
	}
	
	public static Usuario crearUsuario(String nombreUsuario, String contraseña, String tipoUsuario){
		Usuario usuario = new Usuario();
		
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setContraseña(contraseña);
		usuario.setTipoUsuario(tipoUsuario);
		
		return usuario;
	}
	
	public String getNombreUsuario(){
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario){
		if(Validator.length(nombreUsuario, 1, 50)){
			this.nombreUsuario = nombreUsuario;
		}else{
			throw new DomainException("La longitud del nombre de usuario no es valida");
		}
	}
	
	public String getContraseña(){
		return contraseña;
	}
	
	public void setContraseña(String contraseña){
		if(Validator.length(contraseña, 1, 32)){
			this.contraseña = contraseña;
		}else{
			throw new DomainException("La longitud de la contraseña no es valida");
		}
	}
	
	public String getTipoUsuario(){
		return tipoUsuario;
	}
	
	public void setTipoUsuario(String tipoUsuario){
		if(Validator.length(tipoUsuario, 1, 1)){
			this.tipoUsuario = tipoUsuario;
		}else{
			throw new DomainException("La longitud de el tipo de usuario no es valido");
		}
	}
	
	public String getBaneado(){
		return baneado;
	}
	
	public void setbaneado(String baneado){
		if(Validator.length(baneado, 1, 1)){
			this.baneado = baneado;
		}else{
			throw new DomainException("La longitud del tipo de baneo no es valido");
		}
	}
}
