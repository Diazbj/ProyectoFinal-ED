package proyecto.proyectofinaled.modelos.dominio;

public class Municipio {
	private String codigoDepartamento;
	private String codigoMunicipio;
	private String nombreMunicipio;
	
	public Municipio() {
		this.codigoDepartamento = null;
		this.codigoMunicipio = null;
		this.nombreMunicipio = null;
	}

	public Municipio(String codigoDepartamento, String codigoMunicipio, String nombreMunicipio) {
		this.codigoDepartamento = codigoDepartamento;
		this.codigoMunicipio = codigoMunicipio;
		this.nombreMunicipio = nombreMunicipio;
	}

	public String getCodigoDepartamento() {
		return this.codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getCodigoMunicipio() {
		return this.codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getNombreMunicipio() {
		return this.nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	
}