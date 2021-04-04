package domain;

import java.sql.Timestamp;

import exceptions.DomainException;
import util.Validator;

public class Libro {
	private String titulo;
	private Autor autor;
	private int num_pag;
	private String resumen;
	private Timestamp fecha_anadido;
	private String extension;
	
	public Libro(){}
	
	public Libro(String titulo){this.titulo = titulo;}
	
	public Libro(String titulo ,Autor autor, int num_pag, String resumen, Timestamp fecha_anadido, String extension){
		this.titulo = titulo;
		this.autor = autor;
		this.num_pag = num_pag;
		this.resumen = resumen;
		this.fecha_anadido = fecha_anadido;
		this.extension = extension;
	}
	
	public Libro(Libro libro){
		setTitulo(libro.getTitulo());
		setAutor(libro.getAutor());
		setNumPag(libro.getNumPag());
		setResumen(libro.getResumen());
		setFechaAñadido(libro.getFechaAnadido());
		setExtension(libro.getExtension());
	}
	
	public static Libro crearLibro(String titulo ,Autor autor, int num_pag, String resumen, Timestamp fecha_añadido, String extension){
		
		Libro libro = new Libro();
		
		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setNumPag(num_pag);
		libro.setResumen(resumen);
		libro.setFechaAñadido(fecha_añadido);
		libro.setExtension(extension);
		
		return libro;
	}
	
	public static Libro crearLibro(String titulo){
		Libro libro = new Libro();
		libro.setTitulo(titulo);
		return libro;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo(String titulo){
		if(Validator.length(titulo,1,50)){
			this.titulo = titulo;
		}else{
			throw new DomainException("La longitud del titulo no es valida");
		}
	}
	
	public Autor getAutor(){
		return autor;
	}
	
	public void setAutor(Autor autor){
		if(autor != null){
			this.autor = autor;
		}else{
			throw new DomainException("El autor del libro es obligatorio");
		}
	}
	
	public int getNumPag(){
		return num_pag;
	}
	
	public void setNumPag(int num_pag){
		if(Validator.length(Integer.toString(num_pag), 1, 4)){
			this.num_pag = num_pag;
		}else{
			throw new DomainException("El numero de paginas del libro no es valido");
		}
	}
	
	public String getResumen(){
		return resumen;
	}
	
	public void setResumen(String resumen){
		if(resumen != null){
			this.resumen = resumen;
		}else{
			throw new DomainException("El resumen es obligatorio");
		}
	}
	
	public Timestamp getFechaAnadido(){
		return fecha_anadido;
	}
	
	public void setFechaAñadido(Timestamp fecha_anadido){
		if(fecha_anadido != null){
			this.fecha_anadido=fecha_anadido;
		}else{
			throw new DomainException("La fecha de adicion del libro es obligatoria");
		}
	}
	
	public String getExtension(){
		return extension;
	}
	
	public void setExtension(String extension){
		if(Validator.length(extension, 1, 4)){
			this.extension = extension;
		}else{
			throw new DomainException("La longitud de la extension no es valida");
		}
	}
}
