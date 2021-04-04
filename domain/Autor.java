package domain;

import java.sql.Date;

import exceptions.DomainException;

import util.Validator;

public class Autor {
	private String nombre;
	private String pais_nacimiento;
	private Date fecha_nacimiento;
	private String descripcion;
	private String extension;

	public Autor(){}
	
	public Autor(String nombre){this.nombre = nombre;}
	
	public Autor(String nombre, String pais_nacimiento, Date fecha_nacimiento, String descripcion, String extension){
		this.nombre = nombre;
		this.pais_nacimiento = pais_nacimiento;
		this.fecha_nacimiento = fecha_nacimiento;
		this.descripcion = descripcion;
		this.extension = extension;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		if(Validator.length(nombre, 1, 35)){
			this.nombre = nombre;
		}else{
			throw new DomainException("La longitud del nombre no es valida");
		}
	}
	
	public String getPaisNacimiento(){
		return pais_nacimiento;
	}
	
	public void setPaisNacimiento(String pais_nacimiento){
		if(Validator.length(pais_nacimiento, 1, 25)){
			this.pais_nacimiento = pais_nacimiento;
		}else{
			throw new DomainException("La longitud del pais no es valida");
		}
	}
	
	public Date getFechaNacimiento(){
		return fecha_nacimiento;
	}
	
	public void setFechaNacimiento(Date fecha_nacimiento){
		if(fecha_nacimiento != null){
			this.fecha_nacimiento = fecha_nacimiento;
		}else{
			throw new DomainException("La fecha de nacimiento es obligatoria");
		}
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String descripcion){
		if(Validator.length(descripcion, 1, 200)){
			this.descripcion = descripcion;
		}else{
			throw new DomainException("La longitud de la descripcion no es valida");
		}
	}
	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}
