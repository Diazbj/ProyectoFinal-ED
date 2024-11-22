package proyecto.proyectofinaled;		

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
		args = new String[] {"comprimirArchivos"};
		if(args.length == 0)
			return;
		
		switch(args[0]) {
			case "generarArchivosEntrantes":
				generarArchivosEntrantes();
				break;
			case "validarSolicitudCotizante":
				validarSolicitudCotizante();
				break;
			case "trasladarSolicitudCotizantesAprobados":
				trasladarSolicitudCotizantesAprobados();
				break;
			case "comprimirArchivos":
				comprimirArchivos();
				break;
			default:
				System.out.println("metodo no reconocido");
		}

	}
	
	/**
	 * Prueba - Generar 10.000 archivos de prueba en la carpeta SolicitudesEntrantes
	 */
	public static void generarArchivosEntrantes() {
		GenerarArchivosEntrantes generarArchivosEntrantes = new GenerarArchivosEntrantes();
		generarArchivosEntrantes.generarArchivosEntrantes();
	}
	
	/**
	 * Prueba - Cargar Caracterizaciones
	 * @throws IOException
	 */
	public static void testCaracterizaciones() throws IOException {
		CargarArchivos cargarArchivos = new CargarArchivos(new ControladorArchivosUtilitario());
		cargarArchivos.getCaracterizaciones().actualizarCaracterizaciones();
		for(Caracterizacion c: cargarArchivos.getCaracterizaciones().getCaracterizaciones()) {
			System.out.println(c.toString());
		}
	}

	/**
	 * Prueba - Cargar Solicitudes Entrantes
	 * @throws IOException
	 */
	public static void testSolicitudesEntrantes() throws IOException {
		CargarArchivos cargarArchivos = new CargarArchivos(new ControladorArchivosUtilitario());
		cargarArchivos.getSolicitudesCotizante().actualizarSolicitudCotizaciones();
		for(SolicitudCotizante s: cargarArchivos.getSolicitudesCotizante().getSolicitudCotizantes()) {
			System.out.println(s.toString() + " es declarante? " + (s.esDeclarante()?"1":"0"));
		}
	}
	
	/**
	 * Metodo que se llamara cada 60min
	 * CargaLista: Caracterizaciones - SolicitudCotizantes
	 * Resultado: ListaNegraInhabilitados - SolicitudCotizantesAprobados - SolicitudCotizantesRechazados 
	 * Aplica: ReglasEntidades - ReglasNegocio
	 * @throws IOException 
	 */
	public static void validarSolicitudCotizante() throws IOException {
		ControladorArchivosUtilitario archivoUtilitario = new ControladorArchivosUtilitario();
		CargarArchivos cargarArchivos = new CargarArchivos(archivoUtilitario);
		ControladorValidarSolicitudCotizante validarSolicitudCotizante = new ControladorValidarSolicitudCotizante(cargarArchivos);
		validarSolicitudCotizante.validarSolicitudCotizantes();
	}
	
	/**
	 * Metodo que se llamara cada 24horas
	 * CargaLista: SolicitudCotizanteAprobado
	 * @throws IOException 
	 */
	public static void trasladarSolicitudCotizantesAprobados() throws IOException {
		CargarArchivos cargarArchivos = new CargarArchivos(new ControladorArchivosUtilitario());
		ControladorCotizante controladorCotizante = new ControladorCotizante(cargarArchivos);
		controladorCotizante.trasladarCotizantes();
	}
	
	/**
	 * Metodo que se llamara cada 24horas
	 * Comprimi todos los archivos existentes en la carpeta SolicitudesEnProcesamiento
	 * El archivo .zip se llamará SolicitudesProcesadas_yyyy_mm_dd.zip
	 */
	public static void comprimirArchivos() {
		DirectorioUtil DirectorioUtil = new DirectorioUtil();
        String nombreArchivo = "SolicitudesProcesadas_" + FechaUtil.obtenerFechaActual() + ".zip";
        DirectorioUtil.comprimirDirectorio(RutasConstantes.getDirectorioSolicitudProceso(), RutasConstantes.getDirectorioSolicitudProcesadas() + "\\" + nombreArchivo);
        DirectorioUtil.limpiarCarpeta(RutasConstantes.getDirectorioSolicitudProceso());
	}

}