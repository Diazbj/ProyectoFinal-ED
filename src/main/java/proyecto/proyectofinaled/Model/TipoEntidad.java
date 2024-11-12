package proyecto.proyectofinaled.Model;

public enum TipoEntidad {
	FISCALIA("Fiscalia", "01"), PROCURADURIA("Procuraduria", "02"), CONTRALORIA("Contraloria", "03");
	
	private String nombreTipoEntidad;
	private String codigoTipoEntidad;
	
	private TipoEntidad(String nombreTipoEntidad, String codigoTipoEntidad) {
		this.nombreTipoEntidad = nombreTipoEntidad;
		this.codigoTipoEntidad = codigoTipoEntidad;
	}

	public String getNombreTipoEntidad() {
		return nombreTipoEntidad;
	}

	public String getCodigoTipoEntidad() {
		return codigoTipoEntidad;
	}

	public static boolean existe(String valor) {
		for(TipoEntidad t: TipoEntidad.values()) {
			if(t.getCodigoTipoEntidad().equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	public static TipoEntidad getTipoEntidad(String valor) {
		for(TipoEntidad t: TipoEntidad.values()) {
			if(t.getCodigoTipoEntidad().equals(valor)) {
				return t;
			}
		}
		return null;
	}
	
}