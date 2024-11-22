package proyecto.proyectofinaled.modelos.dominio;

public class Caracterizacion {
	private TipoEntidad tipoEntidad;
	private Persona persona;
	private TipoCaracterizacion tipoCaracterizacion;
	
	public Caracterizacion(TipoEntidad tipoEntidad, Persona persona, TipoCaracterizacion tipoCaracterizacion) {
		this.tipoEntidad = tipoEntidad;
		this.persona = persona;
		this.tipoCaracterizacion = tipoCaracterizacion;
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
		
	@Override
	public String toString() {
		return this.tipoEntidad.getNombreTipoEntidad() 
				+ " - " + this.persona.datosBasicos()
				+ " - " + this.tipoCaracterizacion.getNombreTipoCaracterizacion()
				;
	}
}