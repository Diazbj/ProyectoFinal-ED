package proyecto.proyectofinaled.modelos.dominio;

public class Cotizante extends Persona{
	private String embargado;
	public Cotizante(TipoDocumento tipoDocumento, String documento, String nombreCompleto, String fechaNacimiento,
			Departamento departamentoNacimiento, Municipio ciudadNacimiento, Departamento departamentoResidencia,
			Municipio ciudadResidencia) {
		super(tipoDocumento, documento, nombreCompleto, fechaNacimiento, departamentoNacimiento, ciudadNacimiento, departamentoResidencia, ciudadResidencia);
	}
	
	public Cotizante(TipoDocumento tipoDocumento, String documento, String nombreCompleto, String embargado) {
		super(tipoDocumento, documento, nombreCompleto);
		this.embargado = embargado;
	}

	public String getEmbargado() {
		return embargado;
	}

	public void setEmbargado(String embargado) {
		this.embargado = embargado;
	}
	
}