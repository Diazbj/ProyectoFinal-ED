package proyecto.proyectofinaled.controladores;

import java.io.IOException;
import java.util.ArrayList;

import proyecto.proyectofinaled.CargarArchivos;
import proyecto.proyectofinaled.utilidades.csv.EscribirRegistroArchivo;	
import proyecto.proyectofinaled.dao.impl.csv.SolicitudCotizanteRechazadoAccesoDato;
import proyecto.proyectofinaled.modelos.dominio.SolicitudCotizanteRechazado;
import proyecto.proyectofinaled.dto.SolicitudCotizanteRechazadoDTO;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class ControladorSolicitudCotizanteRechazado {
	private CargarArchivos cargarArchivos;
	private ArrayList<SolicitudCotizanteRechazado> solicitudCotizantesRechazados;
	
	public ControladorSolicitudCotizanteRechazado(CargarArchivos cargarArchivos) {
		this.cargarArchivos = cargarArchivos;
		this.solicitudCotizantesRechazados = new ArrayList<>();
	}
	
	public void actualizarSolicitudCotizantesRechazados() throws IOException {
		this.solicitudCotizantesRechazados = cargarSolicitudCotizantesRechazados();
	}
	
	public ArrayList<SolicitudCotizanteRechazado> cargarSolicitudCotizantesRechazados() throws IOException{
		ArrayList<SolicitudCotizanteRechazado> solicitudCotizantesRechazados = new ArrayList<>();
		for(SolicitudCotizanteRechazadoDTO s: new SolicitudCotizanteRechazadoAccesoDato().obtenerTodos()) {
			solicitudCotizantesRechazados.add(new SolicitudCotizanteRechazado(
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
		return solicitudCotizantesRechazados;
	}

	public ArrayList<SolicitudCotizanteRechazado> getSolicitudCotizantesRechazados() {
		return solicitudCotizantesRechazados;
	}

	public void setSolicitudCotizantesRechazados(ArrayList<SolicitudCotizanteRechazado> solicitudCotizantesRechazados) {
		this.solicitudCotizantesRechazados = solicitudCotizantesRechazados;
	}
	
	public void agregarSolicitudCotizante(SolicitudCotizanteRechazado solicitudCotizanteRechazado) {
		for(SolicitudCotizanteRechazado c: this.solicitudCotizantesRechazados) {
			if(c.getTipoDocumento().equals(solicitudCotizanteRechazado.getTipoDocumento()) && c.getDocumento().equals(solicitudCotizanteRechazado.getDocumento())) {
				return;
			}
		}
		agregarSolicitudCotizanteCsv(solicitudCotizanteRechazado);
	}
	
	private void agregarSolicitudCotizanteCsv(SolicitudCotizanteRechazado solicitudCotizanteRechazado) {
		EscribirRegistroArchivo<SolicitudCotizanteRechazadoDTO> registroArchivo = new EscribirRegistroArchivo<>(RutasConstantes.getDirectorioSolicitud() + "/listaSolicitudCotizantesRechazados.csv");
		registroArchivo.escribirObjeto(new SolicitudCotizanteRechazadoDTO(
				solicitudCotizanteRechazado.getTipoDocumento().getCodigoTipoDocumento(),
				solicitudCotizanteRechazado.getDocumento(),
				solicitudCotizanteRechazado.getNombreCompleto(),
				solicitudCotizanteRechazado.getFechaNacimiento(),
				solicitudCotizanteRechazado.getDepartamentoNacimiento().getCodigoDepartamento(),
				solicitudCotizanteRechazado.getCiudadNacimiento().getCodigoMunicipio(),
				solicitudCotizanteRechazado.getDepartamentoResidencia().getCodigoDepartamento(),
				solicitudCotizanteRechazado.getCiudadResidencia().getCodigoMunicipio(),	
				solicitudCotizanteRechazado.esDeclarante()?"1":"0"
			));
		this.solicitudCotizantesRechazados.add(solicitudCotizanteRechazado);
	}

}