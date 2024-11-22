package proyecto.proyectofinaled.controladores;

import java.io.IOException;
import java.util.ArrayList;

import proyecto.proyectofinaled.CargarArchivos;
import proyecto.proyectofinaled.dao.impl.csv.CaracterizacionRegistroAccesoDato;
import proyecto.proyectofinaled.modelos.dominio.Caracterizacion;
import proyecto.proyectofinaled.modelos.dominio.Persona;
import proyecto.proyectofinaled.modelos.dominio.TipoCaracterizacion;
import proyecto.proyectofinaled.modelos.dominio.TipoEntidad;
import proyecto.proyectofinaled.dto.CaracterizacionDTO;
import proyecto.proyectofinaled.utilidades.DirectorioUtil;
import proyecto.proyectofinaled.utilidades.FechaUtil;
import proyecto.proyectofinaled.utilidades.RutasConstantes;	

public class ControladorCaracterizacion {
	private static final String DIRECTORIO = RutasConstantes.getDirectorioSolicitudCaracterizaciones();
	private static final int TAMANO_NOMBRE_ARCHIVO = 14; 
	
	private CargarArchivos cargarArchivos;
	private ArrayList<Caracterizacion> caracterizaciones;
	
	public ControladorCaracterizacion(CargarArchivos cargarArchivos) {
		this.cargarArchivos = cargarArchivos;
		this.caracterizaciones = new ArrayList<>();
	}
	
	public void actualizarCaracterizaciones() throws IOException{
		this.caracterizaciones = cargarCaracterizaciones();
	}
	
	private ArrayList<Caracterizacion> cargarCaracterizaciones() throws IOException{
		ArrayList<Caracterizacion> caracterizaciones = new ArrayList<>();
		
		for(String a: new DirectorioUtil().obtenerNombresObjetosEnDirectorio(DIRECTORIO)) {
			if(validarEstructuraNombreArchivo(a)) {
				try {
					for(CaracterizacionDTO c: new CaracterizacionRegistroAccesoDato(DIRECTORIO + "/" + a).obtenerTodos()) {
						if(validarEstructuraRegistroArchivo(c)) {
							caracterizaciones.add(new Caracterizacion(
									TipoEntidad.getTipoEntidad(a.substring(0,  2)),
									(new Persona(this.cargarArchivos.getArchivosUtilitario().getTipoDocumento(c.getTipoDocumento()), c.getDocumento(), c.getNombreCompleto())),
									TipoCaracterizacion.getTipoCaracterizacion(c.getCaracterizacion())));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return caracterizaciones;
	}
	
	private boolean validarEstructuraNombreArchivo(String nombreArchivo) {		
		if(nombreArchivo.length() > TAMANO_NOMBRE_ARCHIVO || !TipoEntidad.existe(nombreArchivo.substring(0,  2)) || !FechaUtil.validarFormatoFecha(nombreArchivo.substring(2, 10))) {
			return false;
		}
		return true;
	}
	
	private boolean validarEstructuraRegistroArchivo(CaracterizacionDTO CaracterizacionDTO) {
		if(!this.cargarArchivos.getArchivosUtilitario().existeTipoDocumento(CaracterizacionDTO.getTipoDocumento()) || !TipoCaracterizacion.existe(CaracterizacionDTO.getCaracterizacion())) {
			return false;
		}
		return true;
	}

	public ArrayList<Caracterizacion> getCaracterizaciones() {
		return caracterizaciones;
	}

	public void setCaracterizaciones(ArrayList<Caracterizacion> caracterizaciones) {
		this.caracterizaciones = caracterizaciones;
	}
	
	public Caracterizacion obtenerCaracterizacion(Persona persona) {
		for(Caracterizacion c: this.caracterizaciones) {
			if(c.getPersona().getTipoDocumento().equals(persona.getTipoDocumento()) && c.getPersona().getDocumento().equals(persona.getDocumento())) {
				return c;
			}
		}
		return null;
	}
	
}