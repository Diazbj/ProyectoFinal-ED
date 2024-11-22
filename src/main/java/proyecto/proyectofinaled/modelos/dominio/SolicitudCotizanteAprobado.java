package proyecto.proyectofinaled.modelos.dominio;

public class SolicitudCotizanteAprobado extends Cotizante{
	private boolean declarante;

	public SolicitudCotizanteAprobado(TipoDocumento tipoDocumento, String documento, String nombreCompleto,
			String fechaNacimiento, Departamento departamentoNacimiento, Municipio ciudadNacimiento,
			Departamento departamentoResidencia, Municipio ciudadResidencia) {
		super(tipoDocumento, documento, nombreCompleto, fechaNacimiento, departamentoNacimiento, ciudadNacimiento,
				departamentoResidencia, ciudadResidencia);
	}

	public SolicitudCotizanteAprobado(TipoDocumento tipoDocumento, String documento, String nombreCompleto,
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
	
	@Override 
	public String toString() {
		return this.getTipoDocumento().getCodigoTipoDocumento()
				+ " - " + this.getDocumento() 
				+ " - " + this.getNombreCompleto()
				+ " - " + this.getFechaNacimiento()
				+ " - " + this.getEdad() 
				+ " - " + (this.esDeclarante()?"Si declara renta":"No declara renta") 
					;
	}

}