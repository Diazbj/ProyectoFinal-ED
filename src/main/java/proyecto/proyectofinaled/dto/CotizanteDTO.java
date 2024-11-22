package proyecto.proyectofinaled.dto;

public class CotizanteDTO {
	private String tipoDocumento;
	private String numeroDocumento;
	private String nombreCompleto;
	private String fechaNacimiento;
	private String departamentoNacimiento;
	private String ciudadNacimiento;
	private String departamentoResidencia;
	private String ciudadResidencia;
	
	public CotizanteDTO() {
		super();
	}

	public CotizanteDTO(String tipoDocumento, String numeroDocumento, String nombreCompleto,
			String fechaNacimiento, String departamentoNacimiento, String ciudadNacimiento,
			String departamentoResidencia, String ciudadResidencia) {
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.departamentoNacimiento = departamentoNacimiento;
		this.ciudadNacimiento = ciudadNacimiento;
		this.departamentoResidencia = departamentoResidencia;
		this.ciudadResidencia = ciudadResidencia;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDepartamentoNacimiento() {
		return departamentoNacimiento;
	}

	public void setDepartamentoNacimiento(String departamentoNacimiento) {
		this.departamentoNacimiento = departamentoNacimiento;
	}

	public String getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public String getDepartamentoResidencia() {
		return departamentoResidencia;
	}

	public void setDepartamentoResidencia(String departamentoResidencia) {
		this.departamentoResidencia = departamentoResidencia;
	}

	public String getCiudadResidencia() {
		return ciudadResidencia;
	}

	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}
		
}