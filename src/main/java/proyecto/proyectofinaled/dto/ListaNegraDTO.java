package proyecto.proyectofinaled.dto;

public class ListaNegraDTO {
	private String tipoEntidad;
	private String tipoDocumento;
	private String documento;
	private String nombreCompleto;
	private String tipoCaracterizacion;
	private String fechaRegistro;
	
	public ListaNegraDTO() {
		super();
	}

	public ListaNegraDTO(String tipoEntidad, String tipoDocumento, String documento, String nombreCompleto,
			String tipoCaracterizacion, String fechaRegistro) {
		super();
		this.tipoEntidad = tipoEntidad;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.nombreCompleto = nombreCompleto;
		this.tipoCaracterizacion = tipoCaracterizacion;
		this.fechaRegistro = fechaRegistro;
	}

	public String getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getTipoCaracterizacion() {
		return tipoCaracterizacion;
	}

	public void setTipoCaracterizacion(String tipoCaracterizacion) {
		this.tipoCaracterizacion = tipoCaracterizacion;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}