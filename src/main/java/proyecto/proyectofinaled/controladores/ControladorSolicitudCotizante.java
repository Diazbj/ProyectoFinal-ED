package proyecto.proyectofinaled.controladores;

import java.io.IOException;
import java.util.ArrayList;

import proyecto.proyectofinaled.CargarArchivos;
import proyecto.proyectofinaled.dao.impl.csv.SolicitudCotizanteRegistroAccesoDato;
import proyecto.proyectofinaled.modelos.dominio.FondosOrigen;
import proyecto.proyectofinaled.modelos.dominio.InstitucionesPublicas;
import proyecto.proyectofinaled.modelos.dominio.Persona;
import proyecto.proyectofinaled.modelos.dominio.SolicitudCotizante;
import proyecto.proyectofinaled.dto.SolicitudCotizanteDTO;
import proyecto.proyectofinaled.utilidades.DirectorioUtil;	
import proyecto.proyectofinaled.utilidades.FechaUtil;
import proyecto.proyectofinaled.utilidades.RutasConstantes;
public class ControladorSolicitudCotizante {
	private static final String DIRECTORIO_SOLICITUD_ENTRANTES = RutasConstantes.getDirectorioSolicitudEntrantes();
	private static final String DIRECTORIO_SOLICITUD_PROCESO = RutasConstantes.getDirectorioSolicitudProceso();
	
	private CargarArchivos cargarArchivos;
	private ArrayList<SolicitudCotizante> solicitudCotizantes;
	
	public ControladorSolicitudCotizante(CargarArchivos cargarArchivos) {
		this.cargarArchivos = cargarArchivos; 
		this.solicitudCotizantes = new ArrayList<>();
	}
	
	public void actualizarSolicitudCotizaciones() throws IOException {
		this.solicitudCotizantes = cargarSolicitudCotizantes();
	}
	
	private ArrayList<SolicitudCotizante> cargarSolicitudCotizantes() throws IOException{
		ArrayList<SolicitudCotizante> solicitudCotizantes = new ArrayList<>();
		
		for(String a: new DirectorioUtil().obtenerNombresObjetosEnDirectorio(DIRECTORIO_SOLICITUD_ENTRANTES)) {
			for(SolicitudCotizanteDTO s: new SolicitudCotizanteRegistroAccesoDato(DIRECTORIO_SOLICITUD_ENTRANTES + "/" + a).obtenerTodos()) {
				if(validarEstructuraRegistroArchivo(s)) {
					solicitudCotizantes.add(
							new SolicitudCotizante(
									a,
									new Persona(
											this.cargarArchivos.getArchivosUtilitario().getTipoDocumento(s.getTipoDocumento()), 
											s.getNumeroDocumento(),
											s.getNombreCompleto(),
											s.getFechaNacimiento(),
											this.cargarArchivos.getArchivosUtilitario().getDepartamento(s.getDepartamentoNacimiento()),
											this.cargarArchivos.getArchivosUtilitario().getMunicipio(s.getCiudadNacimiento()),
											this.cargarArchivos.getArchivosUtilitario().getDepartamento(s.getDepartamentoNacimiento()),
											this.cargarArchivos.getArchivosUtilitario().getMunicipio(s.getCiudadResidencia())),
									FondosOrigen.getFondoOrigen(s.getFondoOrigen()),
									Integer.parseInt(s.getPrePensionado()),
									InstitucionesPublicas.getInstitucionPublica(s.getInstitucionPublica()),
									Integer.parseInt(s.getCondecoracion()),
									Integer.parseInt(s.getHijosInpec()),
									Integer.parseInt(s.getFamiliaPolicia()),
									Integer.parseInt(s.getObservacionDisciplinaria()),
									Integer.parseInt(s.getNumeroSemanas()),
									("1".equals(s.getDeclaraRenta()))
								)
						);
				}
			}
			moverArchivoSolicitudProceso(a);
		};
		return solicitudCotizantes;
	}
	
	private boolean validarEstructuraRegistroArchivo(SolicitudCotizanteDTO SolicitudCotizanteDTO) {
		if(!this.cargarArchivos.getArchivosUtilitario().existeTipoDocumento(SolicitudCotizanteDTO.getTipoDocumento())) {
			return false;
		}
				
		if(!FechaUtil.validarFormatoFecha(SolicitudCotizanteDTO.getFechaNacimiento())) {
			return false;
		}
		
		if(!this.cargarArchivos.getArchivosUtilitario().existeDepartamento(SolicitudCotizanteDTO.getDepartamentoNacimiento())) {
			return false;
		}
		
		if(!this.cargarArchivos.getArchivosUtilitario().existeMunicipio(SolicitudCotizanteDTO.getCiudadNacimiento())) {
			return false;
		}
		
		if(!this.cargarArchivos.getArchivosUtilitario().existeDepartamento(SolicitudCotizanteDTO.getDepartamentoResidencia())) {
			return false;
		}
		
		if(!this.cargarArchivos.getArchivosUtilitario().existeMunicipio(SolicitudCotizanteDTO.getCiudadResidencia())) {
			return false;
		}
		
		if(!FondosOrigen.existe(SolicitudCotizanteDTO.getFondoOrigen())) {
			return false;
		}
		
		if(!FechaUtil.esNumero(SolicitudCotizanteDTO.getPrePensionado())) {
			return false;
		} else if(Integer.parseInt(SolicitudCotizanteDTO.getPrePensionado()) > 1){
			return false;
		}
		
		if(!InstitucionesPublicas.existe(SolicitudCotizanteDTO.getInstitucionPublica())) {
			return false;
		}
		
		if(!FechaUtil.esNumero(SolicitudCotizanteDTO.getCondecoracion())) {
			return false;
		} else if(Integer.parseInt(SolicitudCotizanteDTO.getCondecoracion()) > 1){
			return false;
		}
		
		if(!FechaUtil.esNumero(SolicitudCotizanteDTO.getHijosInpec())) {
			return false;
		} else if(Integer.parseInt(SolicitudCotizanteDTO.getHijosInpec()) > 1){
			return false;
		}
		
		if(!FechaUtil.esNumero(SolicitudCotizanteDTO.getFamiliaPolicia())) {
			return false;
		} else if(Integer.parseInt(SolicitudCotizanteDTO.getFamiliaPolicia()) > 1){
			return false;
		}
		
		if(!FechaUtil.esNumero(SolicitudCotizanteDTO.getObservacionDisciplinaria())) {
			return false;
		} else if(Integer.parseInt(SolicitudCotizanteDTO.getObservacionDisciplinaria()) > 1){
			return false;
		}
		
		if(!FechaUtil.esNumero(SolicitudCotizanteDTO.getNumeroSemanas())) {
			return false;
		}
		
		if(!FechaUtil.esNumero(SolicitudCotizanteDTO.getDeclaraRenta())) {
			return false;
		} else if(Integer.parseInt(SolicitudCotizanteDTO.getDeclaraRenta()) > 1){
			return false;
		}
		
		return true;
	}
	
	private void moverArchivoSolicitudProceso(String nombreArchivo) throws IOException {
		DirectorioUtil DirectorioUtil = new DirectorioUtil();
		DirectorioUtil.moverArchivo(DIRECTORIO_SOLICITUD_ENTRANTES + "/" + nombreArchivo, DIRECTORIO_SOLICITUD_PROCESO);
	}

	public ArrayList<SolicitudCotizante> getSolicitudCotizantes() {
		return solicitudCotizantes;
	}

	public void setSolicitudCotizantes(ArrayList<SolicitudCotizante> solicitudCotizantes) {
		this.solicitudCotizantes = solicitudCotizantes;
	}
	
}