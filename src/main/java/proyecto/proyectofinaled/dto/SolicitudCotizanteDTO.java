package proyecto.proyectofinaled.dto;

public class SolicitudCotizanteDTO {
	private String tipoDocumento;
	private String numeroDocumento;
	private String nombreCompleto;
	private String fechaNacimiento;
	private String departamentoNacimiento;
	private String ciudadNacimiento;
	private String departamentoResidencia;
	private String ciudadResidencia;
	private String fondoOrigen;
	private String prePensionado;
	private String institucionPublica;
	private String condecoracion;
	private String hijosInpec;
	private String familiaPolicia;
	private String observacionDisciplinaria;
	private String numeroSemanas;
	private String declaraRenta;
	
	public SolicitudCotizanteDTO() {		
	}
	
	public SolicitudCotizanteDTO(String tipoDocumento, String numeroDocumento, String nombreCompleto,
			String fechaNacimiento, String departamentoNacimiento, String ciudadNacimiento,
			String departamentoResidencia, String ciudadResidencia, String fondoOrigen, String prePensionado,
			String institucionPublica, String condecoracion, String hijosInpec, String familiaPolicia,
			String observacionDisciplinaria, String numeroSemanas, String declaraRenta) {
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.departamentoNacimiento = departamentoNacimiento;
		this.ciudadNacimiento = ciudadNacimiento;
		this.departamentoResidencia = departamentoResidencia;
		this.ciudadResidencia = ciudadResidencia;
		this.fondoOrigen = fondoOrigen;
		this.prePensionado = prePensionado;
		this.institucionPublica = institucionPublica;
		this.condecoracion = condecoracion;
		this.hijosInpec = hijosInpec;
		this.familiaPolicia = familiaPolicia;
		this.observacionDisciplinaria = observacionDisciplinaria;
		this.numeroSemanas = numeroSemanas;
		this.declaraRenta = declaraRenta;
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
	
	public String getFondoOrigen() {
		return fondoOrigen;
	}
	
	public void setFondoOrigen(String fondoOrigen) {
		this.fondoOrigen = fondoOrigen;
	}
	
	public String getPrePensionado() {
		return prePensionado;
	}
	
	public void setPrePensionado(String prePensionado) {
		this.prePensionado = prePensionado;
	}
	
	public String getInstitucionPublica() {
		return institucionPublica;
	}
	
	public void setInstitucionPublica(String institucionPublica) {
		this.institucionPublica = institucionPublica;
	}
	
	public String getCondecoracion() {
		return condecoracion;
	}
	
	public void setCondecoracion(String condecoracion) {
		this.condecoracion = condecoracion;
	}
	
	public String getHijosInpec() {
		return hijosInpec;
	}
	
	public void setHijosInpec(String hijosInpec) {
		this.hijosInpec = hijosInpec;
	}
	
	public String getFamiliaPolicia() {
		return familiaPolicia;
	}
	
	public void setFamiliaPolicia(String familiaPolicia) {
		this.familiaPolicia = familiaPolicia;
	}
	
	public String getObservacionDisciplinaria() {
		return observacionDisciplinaria;
	}
	
	public void setObservacionDisciplinaria(String observacionDisciplinaria) {
		this.observacionDisciplinaria = observacionDisciplinaria;
	}
	
	public String getNumeroSemanas() {
		return numeroSemanas;
	}
	
	public void setNumeroSemanas(String numeroSemanas) {
		this.numeroSemanas = numeroSemanas;
	}

	public String getDeclaraRenta() {
		return declaraRenta;
	}

	public void setDeclaraRenta(String declaraRenta) {
		this.declaraRenta = declaraRenta;
	}
		
}