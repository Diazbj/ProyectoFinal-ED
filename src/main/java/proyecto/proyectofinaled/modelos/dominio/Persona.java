package proyecto.proyectofinaled.modelos.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Persona {
	private TipoDocumento tipoDocumento;
	private String documento;
	private String nombreCompleto;
	private String fechaNacimiento;
	private Departamento departamentoNacimiento;
	private Municipio ciudadNacimiento;
	private Departamento departamentoResidencia;
	private Municipio ciudadResidencia;
	
	public Persona(TipoDocumento tipoDocumento, String documento, String nombreCompleto) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.nombreCompleto = nombreCompleto;
	}

	public Persona(TipoDocumento tipoDocumento, String documento, String nombreCompleto, String fechaNacimiento,
			Departamento departamentoNacimiento, Municipio ciudadNacimiento, Departamento departamentoResidencia,
			Municipio ciudadResidencia) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.departamentoNacimiento = departamentoNacimiento;
		this.ciudadNacimiento = ciudadNacimiento;
		this.departamentoResidencia = departamentoResidencia;
		this.ciudadResidencia = ciudadResidencia;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Departamento getDepartamentoNacimiento() {
		return departamentoNacimiento;
	}

	public void setDepartamentoNacimiento(Departamento departamentoNacimiento) {
		this.departamentoNacimiento = departamentoNacimiento;
	}

	public Municipio getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(Municipio ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public Departamento getDepartamentoResidencia() {
		return departamentoResidencia;
	}

	public void setDepartamentoResidencia(Departamento departamentoResidencia) {
		this.departamentoResidencia = departamentoResidencia;
	}

	public Municipio getCiudadResidencia() {
		return ciudadResidencia;
	}

	public void setCiudadResidencia(Municipio ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}
	
	public int getEdad() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate fechaNac = LocalDate.parse(this.fechaNacimiento, formatter);
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaNac, fechaActual);
		return periodo.getYears();
	}
	
	public String datosBasicos() {
		return this.tipoDocumento.getCodigoTipoDocumento() 
				+ " - " + this.documento 
				+ " - " + this.nombreCompleto;
	}
	
	public String datosBasicosFechaNacimiento() {
		return datosBasicos() 
				+ " - " + this.fechaNacimiento
				+ " - " + getEdad();
	}
	
}