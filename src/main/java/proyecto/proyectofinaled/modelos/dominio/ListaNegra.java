package proyecto.proyectofinaled.modelos.dominio;

public class ListaNegra {
	private TipoEntidad tipoEntidad;
	private Persona persona;
	private TipoCaracterizacion tipoCaracterizacion;
	private String fechaRegistro;
	
	public ListaNegra() {
		super();
	}

	public ListaNegra(TipoEntidad tipoEntidad, Persona persona, TipoCaracterizacion tipoCaracterizacion,
			String fechaRegistro) {
		this.tipoEntidad = tipoEntidad;
		this.persona = persona;
		this.tipoCaracterizacion = tipoCaracterizacion;
		this.fechaRegistro = fechaRegistro;
	}

	public TipoEntidad getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(TipoEntidad tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoCaracterizacion getTipoCaracterizacion() {
		return tipoCaracterizacion;
	}

	public void setTipoCaracterizacion(TipoCaracterizacion tipoCaracterizacion) {
		this.tipoCaracterizacion = tipoCaracterizacion;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
}