package proyecto.proyectofinaled.controladores;

import java.io.IOException;
import java.util.ArrayList;

import proyecto.proyectofinaled.CargarArchivos;
import proyecto.proyectofinaled.utilidades.csv.EscribirRegistroArchivo;	
import proyecto.proyectofinaled.dao.impl.csv.SolicitudCotizanteAprobadoAccesoDato;
import proyecto.proyectofinaled.modelos.dominio.SolicitudCotizanteAprobado;
import proyecto.proyectofinaled.dto.SolicitudCotizanteAprobadoDTO;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class ControladorSolicitudCotizanteAprobado {
	private CargarArchivos cargarArchivos;
	private ArrayList<SolicitudCotizanteAprobado> solicitudCotizantesAprobados;

	public ControladorSolicitudCotizanteAprobado(CargarArchivos cargarArchivos) {
		this.cargarArchivos = cargarArchivos;
		this.solicitudCotizantesAprobados = new ArrayList<>();
	}
	
	public void actualizarSolicitudCotizantesAprobados() throws IOException {
		this.solicitudCotizantesAprobados = cargarSolicitudCotizantesAprobados();
	}
	
	public ArrayList<SolicitudCotizanteAprobado> cargarSolicitudCotizantesAprobados() throws IOException{
		ArrayList<SolicitudCotizanteAprobado> solicitudCotizantesAprobados = new ArrayList<>();
		for(SolicitudCotizanteAprobadoDTO s: new SolicitudCotizanteAprobadoAccesoDato().obtenerTodos()) {
			solicitudCotizantesAprobados.add(new SolicitudCotizanteAprobado(
						this.cargarArchivos.getArchivosUtilitario().getTipoDocumento(s.getTipoDocumento()),
						s.getNumeroDocumento(),
						s.getNombreCompleto(),
						s.getFechaNacimiento(),
						this.cargarArchivos.getArchivosUtilitario().getDepartamento(s.getDepartamentoNacimiento()),
						this.cargarArchivos.getArchivosUtilitario().getMunicipio(s.getCiudadNacimiento()),
						this.cargarArchivos.getArchivosUtilitario().getDepartamento(s.getDepartamentoResidencia()),
						this.cargarArchivos.getArchivosUtilitario().getMunicipio(s.getCiudadResidencia()),
						"1".equals(s.getDeclaraRenta())
					));
		}
		return solicitudCotizantesAprobados;
	}
	
	public ArrayList<SolicitudCotizanteAprobado> getSolicitudCotizantesAprobados() {
		return solicitudCotizantesAprobados;
	}

	public void setSolicitudCotizantesAprobados(ArrayList<SolicitudCotizanteAprobado> solicitudCotizantesAprobados) {
		this.solicitudCotizantesAprobados = solicitudCotizantesAprobados;
	}

	public void actualizarArchivoListaSolicitudCotizantesAprobados(ArrayList<SolicitudCotizanteAprobado> listaSolicitudesAprobadasRestante) {
		EscribirRegistroArchivo<SolicitudCotizanteAprobadoDTO> registroArchivo = new EscribirRegistroArchivo<>(RutasConstantes.getDirectorioSolicitud() + "/listaSolicitudCotizantesAprobados.csv");
		registroArchivo.actualizarListaCotizantes();
		listaSolicitudesAprobadasRestante.forEach(l -> {
			agregarSolicitudCotizanteCsv(l);
		});
	}
	
	public void agregarSolicitudCotizante(SolicitudCotizanteAprobado solicitudCotizanteAprobado) {
		for(SolicitudCotizanteAprobado c: this.solicitudCotizantesAprobados) {
			if(c.getTipoDocumento().equals(solicitudCotizanteAprobado.getTipoDocumento()) && c.getDocumento().equals(solicitudCotizanteAprobado.getDocumento())) {
				return;
			}
		}
		agregarSolicitudCotizanteCsv(solicitudCotizanteAprobado);
	}
	
	private void agregarSolicitudCotizanteCsv(SolicitudCotizanteAprobado solicitudCotizanteAprobado) {
		EscribirRegistroArchivo<SolicitudCotizanteAprobadoDTO> registroArchivo = new EscribirRegistroArchivo<>(RutasConstantes.getDirectorioSolicitud() + "/listaSolicitudCotizantesAprobados.csv");
		registroArchivo.escribirObjeto(new SolicitudCotizanteAprobadoDTO(
				solicitudCotizanteAprobado.getTipoDocumento().getCodigoTipoDocumento(),
				solicitudCotizanteAprobado.getDocumento(),
				solicitudCotizanteAprobado.getNombreCompleto(),
				solicitudCotizanteAprobado.getFechaNacimiento(),
				solicitudCotizanteAprobado.getDepartamentoNacimiento().getCodigoDepartamento(),
				solicitudCotizanteAprobado.getCiudadNacimiento().getCodigoMunicipio(),
				solicitudCotizanteAprobado.getDepartamentoResidencia().getCodigoDepartamento(),
				solicitudCotizanteAprobado.getCiudadResidencia().getCodigoMunicipio(),
				solicitudCotizanteAprobado.esDeclarante()?"1":"0"
			));
		this.solicitudCotizantesAprobados.add(solicitudCotizanteAprobado);
	}
		
}