package domain;

import java.sql.Date;
import java.sql.Timestamp;

import util.Fecha;

import exceptions.DomainException;

public class Resena {
	
	private Usuario usuario;
	private Libro libro;
	private Timestamp fecha_hora;
	private double estrellas;
	private String descripcion;
	private int num_paginas;
	
	public Resena(){}
	
	public Resena(Usuario usuario, Libro libro, Timestamp fecha_hora){
		this.usuario = usuario;
		this.libro = libro;
		this.fecha_hora = fecha_hora;
	}
	
	public Resena(Usuario usuario, Libro libro, Timestamp fecha_hora, double estrellas, String descripcion, int num_paginas){
		this.usuario = usuario;
		this.libro = libro;
		this.fecha_hora = fecha_hora;
		this.estrellas = estrellas;
		this.descripcion = descripcion;
		this.num_paginas = num_paginas;
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		if(usuario != null){
			this.usuario = usuario;
		}else{
			throw new DomainException("El usuario es obligatorio");
		}
	}
	
	public Libro getLibro(){
		return libro;
	}
	
	public void setLibro(Libro libro){
		if(libro != null){
			this.libro = libro;
		}else{
			throw new DomainException("El libro es obligatorio");
		}
	}
	
	public Timestamp getFechaHora(){
		return fecha_hora;
	}
	
	public void setFechaHora(Timestamp fecha_hora){
		Timestamp hoy = Fecha.fechaActual();
		if(fecha_hora.before(hoy)){
			this.fecha_hora = fecha_hora;
		}else{
			throw new DomainException("La fecha no puede ser futura");
		}
	}
	
	public double getEstrellas(){
		return estrellas;
	}
	
	public void setEstrellas(double estrellas){
		if(estrellas>=0 && estrellas<=5){
			this.estrellas = estrellas;
		}else{
			throw new DomainException("Numero de estrellas no validas");
		}
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	
	public int getNumPaginas(){
		return num_paginas;
	}
	
	public void setNumPaginas(int num_paginas){
		if(num_paginas>0 && num_paginas<10000){
			this.num_paginas = num_paginas;
		}else{
			throw new DomainException("El numero de paginas no es valido");
		}
	}
}
