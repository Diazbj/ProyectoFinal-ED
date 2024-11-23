package proyecto.proyectofinaled;		

import java.io.File;
import java.io.IOException;

import proyecto.proyectofinaled.controladores.ControladorArchivosUtilitario;
import proyecto.proyectofinaled.controladores.ControladorCotizante;
import proyecto.proyectofinaled.controladores.ControladorValidarSolicitudCotizante;
import proyecto.proyectofinaled.modelos.dominio.Caracterizacion;
import proyecto.proyectofinaled.modelos.dominio.SolicitudCotizante;
import proyecto.proyectofinaled.utilidades.DirectorioUtil;
import proyecto.proyectofinaled.utilidades.FechaUtil;
import proyecto.proyectofinaled.utilidades.GenerarArchivosEntrantes;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// // System.out.println(RutasConstantes.getDirectorioSolicitud());
		// generarArchivosEntrantes();
		// validarSolicitudCotizante();
		// trasladarSolicitudCotizantesAprobados();
		// comprimirArchivos();

	}
	
	public static void generarArchivosEntrantes() {
		GenerarArchivosEntrantes generarArchivosEntrantes = new GenerarArchivosEntrantes();
		generarArchivosEntrantes.generarArchivosEntrantes();
	}
	
	public static void testCaracterizaciones() throws IOException {
		CargarArchivos cargarArchivos = new CargarArchivos(new ControladorArchivosUtilitario());
		cargarArchivos.getCaracterizaciones().actualizarCaracterizaciones();
		for(Caracterizacion c: cargarArchivos.getCaracterizaciones().getCaracterizaciones()) {
			System.out.println(c.toString());
		}
	}

	public static void testSolicitudesEntrantes() throws IOException {
		CargarArchivos cargarArchivos = new CargarArchivos(new ControladorArchivosUtilitario());
		cargarArchivos.getSolicitudesCotizante().actualizarSolicitudCotizaciones();
		for(SolicitudCotizante s: cargarArchivos.getSolicitudesCotizante().getSolicitudCotizantes()) {
			System.out.println(s.toString() + " es declarante? " + (s.esDeclarante()?"1":"0"));
		}
	}
	

	public static void validarSolicitudCotizante() throws IOException {
		ControladorArchivosUtilitario archivoUtilitario = new ControladorArchivosUtilitario();
		CargarArchivos cargarArchivos = new CargarArchivos(archivoUtilitario);
		ControladorValidarSolicitudCotizante validarSolicitudCotizante = new ControladorValidarSolicitudCotizante(cargarArchivos);
		validarSolicitudCotizante.validarSolicitudCotizantes();
	}
	
	public static void trasladarSolicitudCotizantesAprobados() throws IOException {
		CargarArchivos cargarArchivos = new CargarArchivos(new ControladorArchivosUtilitario());
		ControladorCotizante controladorCotizante = new ControladorCotizante(cargarArchivos);
		controladorCotizante.trasladarCotizantes();
	}
	
	public static void comprimirArchivos() {
		DirectorioUtil directorioUtil = new DirectorioUtil();
		// Crear el directorio de destino si no existe
		File directorioDestino = new File(RutasConstantes.getDirectorioSolicitudProcesadas());
		if (!directorioDestino.exists()) {
			directorioDestino.mkdirs();
		}
		
		String nombreArchivo = "SolicitudesProcesadas_" + FechaUtil.obtenerFechaActual() + ".zip";
		directorioUtil.comprimirDirectorio(
			RutasConstantes.getDirectorioSolicitudProceso(), 
			RutasConstantes.getDirectorioSolicitudProcesadas() + "/" + nombreArchivo
		);
		directorioUtil.limpiarCarpeta(RutasConstantes.getDirectorioSolicitudProceso());
	}

}