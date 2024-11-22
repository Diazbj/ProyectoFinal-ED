package proyecto.proyectofinaled.modelos.dominio;

public class SolicitudCotizanteRechazado extends Cotizante{
	private boolean declarante;
	
	public SolicitudCotizanteRechazado(TipoDocumento tipoDocumento, String documento, String nombreCompleto,
			String fechaNacimiento, Departamento departamentoNacimiento, Municipio ciudadNacimiento,
			Departamento departamentoResidencia, Municipio ciudadResidencia) {
		super(tipoDocumento, documento, nombreCompleto, fechaNacimiento, departamentoNacimiento, ciudadNacimiento,
				departamentoResidencia, ciudadResidencia);
	}

	public SolicitudCotizanteRechazado(TipoDocumento tipoDocumento, String documento, String nombreCompleto,
			String fechaNacimiento, Departamento departamentoNacimiento, Municipio ciudadNacimiento,
			Departamento departamentoResidencia, Municipio ciudadResidencia, boolean declarante) {
		super(tipoDocumento, documento, nombreCompleto, fechaNacimiento, departamentoNacimiento, ciudadNacimiento,
				departamentoResidencia, ciudadResidencia);
		this.declarante = declarante;
	}

	public boolean esDeclarante() {
		return declarante;
	}

	public void setDeclarante(boolean declarante) {
		this.declarante = declarante;
	}
	
}