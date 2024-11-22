package proyecto.proyectofinaled.modelos.dominio;

public enum FondosOrigen {
	PORVERNIR("Porvenir", "1"), 
	PROTECCION("Proteccion", "2"), 
	COLFONDOS("Colfondos", "3"), 
	OLD_MUTUAL("Old Mutual", "4"), 
	EXTRANJERO("Extranjero", "5");
	
	private String nombreFondoOrigen;
	private String codigoFondoOrigen;
	
	FondosOrigen(String nombreFondoOrigen, String codigoFondoOrigen){
		this.nombreFondoOrigen = nombreFondoOrigen;
		this.codigoFondoOrigen = codigoFondoOrigen;
	}

	public String getNombreFondoOrigen() {
		return nombreFondoOrigen;
	}

	public String getCodigoFondoOrigen() {
		return codigoFondoOrigen;
	}
	
	public static boolean existe(String valor) {
		for(FondosOrigen f: FondosOrigen.values()) {
			if(f.getCodigoFondoOrigen().equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	public static FondosOrigen getFondoOrigen(String valor) {
		for(FondosOrigen f: FondosOrigen.values()) {
			if(f.getCodigoFondoOrigen().equals(valor)) {
				return f;
			}
		}
		return null;		
	}
	
}