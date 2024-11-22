package proyecto.proyectofinaled.modelos.dominio;

public class Departamento {
	private String codigoDepartamento;
	private String nombreDepartamento;
	
	public Departamento() {
		this.codigoDepartamento = null;
		this.nombreDepartamento = null;
	}
	
	public Departamento(String codigoDepartamento, String nombreDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getCodigoDepartamento() {
		return this.codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	
}