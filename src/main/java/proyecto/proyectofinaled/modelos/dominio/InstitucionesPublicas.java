package proyecto.proyectofinaled.modelos.dominio;

public enum InstitucionesPublicas {
	ARMADA("Armada", "1"),
	INPEC("Inpec", "2"),
	POLICIA("Policia", "3"),
	MINSALUD("MinSalud", "4"),
	MININTERIOR("MinInterior", "5"),
	CIVIL("Civil", "6");
	
	private String nombreInstitucionPublica;
	private String codigoInstitucionPublica;
	
	InstitucionesPublicas(String nombreInstitucionPublica, String codigoInstitucionPublica) {
		this.nombreInstitucionPublica = nombreInstitucionPublica;
		this.codigoInstitucionPublica = codigoInstitucionPublica;
	}

	public String getNombreInstitucionPublica() {
		return nombreInstitucionPublica;
	}

	public String getCodigoInstitucionPublica() {
		return codigoInstitucionPublica;
	}
	
	public static boolean existe(String valor) {
		for(InstitucionesPublicas i: InstitucionesPublicas.values()) {
			if(i.getCodigoInstitucionPublica().equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	public static InstitucionesPublicas getInstitucionPublica(String valor) {
		for(InstitucionesPublicas i: InstitucionesPublicas.values()) {
			if(i.getCodigoInstitucionPublica().equals(valor)) {
				return i;
			}
		}
		return null;		
	}
	
}