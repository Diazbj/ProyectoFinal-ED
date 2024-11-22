package proyecto.proyectofinaled.modelos.dominio;

public enum TipoCaracterizacion {
	INHABILITAR("Inhabilitar", "1"), EMBARGAR("Embargar", "2");
	
	private String nombreTipoCaracterizacion;
	private String codigoTipoCaracterizacion;
	
	private TipoCaracterizacion(String nombreTipoCaracterizacion, String codigoTipoCaracterizacion) {
		this.nombreTipoCaracterizacion = nombreTipoCaracterizacion;
		this.codigoTipoCaracterizacion = codigoTipoCaracterizacion;
	}

	public String getNombreTipoCaracterizacion() {
		return nombreTipoCaracterizacion;
	}

	public String getCodigoTipoCaracterizacion() {
		return codigoTipoCaracterizacion;
	}
	
	public static boolean existe(String valor) {
		for(TipoCaracterizacion t: TipoCaracterizacion.values()) {
			if(t.getCodigoTipoCaracterizacion().equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	public static TipoCaracterizacion getTipoCaracterizacion(String valor) {
		for(TipoCaracterizacion t: TipoCaracterizacion.values()) {
			if(t.getCodigoTipoCaracterizacion().equals(valor)) {
				return t;
			}
		}
		return null;		
	}

}