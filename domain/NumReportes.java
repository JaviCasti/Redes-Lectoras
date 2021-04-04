package domain;

import exceptions.DomainException;

public class NumReportes {

	private String usuario;
	private int reportes;
	
	public NumReportes(){}
	
	public NumReportes(String usuario, int reportes){
		this.usuario = usuario;
		this.reportes = reportes;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		if(usuario != null)
			this.usuario = usuario;
		else
			throw new DomainException("El usuario es obligatorio");
	}

	public int getReportes() {
		return reportes;
	}

	public void setReportes(int reportes) {
		if(reportes<0)
			this.reportes = reportes;
		else
			throw new DomainException("Un usuario no puede tener menos que 0 reportes");
	}
	
	
	
}
