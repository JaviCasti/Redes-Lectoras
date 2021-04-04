package domain;

public class EstadoLibro {

	private String estado;
	private int numPag;
	
	public EstadoLibro(){}
	
	public EstadoLibro(String estado,int numPag){
		this.estado = estado;
		this.numPag = numPag;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}
	
}


