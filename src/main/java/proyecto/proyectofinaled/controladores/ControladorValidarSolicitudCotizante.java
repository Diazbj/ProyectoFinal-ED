package proyecto.proyectofinaled.controladores;

import java.io.IOException;

import proyecto.proyectofinaled.CargarArchivos;
import proyecto.proyectofinaled.modelos.dominio.Caracterizacion;
import proyecto.proyectofinaled.modelos.dominio.FondosOrigen;
import proyecto.proyectofinaled.modelos.dominio.InstitucionesPublicas;
import proyecto.proyectofinaled.modelos.dominio.ListaNegra;
import proyecto.proyectofinaled.modelos.dominio.SolicitudCotizante;
import proyecto.proyectofinaled.modelos.dominio.SolicitudCotizanteAprobado;
import proyecto.proyectofinaled.modelos.dominio.SolicitudCotizanteRechazado;
import proyecto.proyectofinaled.modelos.dominio.TipoCaracterizacion;
import proyecto.proyectofinaled.utilidades.FechaUtil;

public class ControladorValidarSolicitudCotizante {
	private CargarArchivos cargarArchivos;
	
	public ControladorValidarSolicitudCotizante(CargarArchivos cargarArchivos) {
		this.cargarArchivos = cargarArchivos;
	}
	
	public void validarSolicitudCotizantes() throws IOException {
		/**
		 * Se actualiza la lista de caracterizaciones, solicitud de cotizantes, cotizantes inhabilitados (lista negra), cotizantes rechazados y cotizantes aprobados
		 */
		this.cargarArchivos.getCaracterizaciones().actualizarCaracterizaciones();
		this.cargarArchivos.getSolicitudesCotizante().actualizarSolicitudCotizaciones();
		this.cargarArchivos.getListaSolicitudesInhabilitados().actualizarListaNegra();
		this.cargarArchivos.getListaCotizantesRechazados().actualizarSolicitudCotizantesRechazados();
		this.cargarArchivos.getListaCotizantesAprobados().actualizarSolicitudCotizantesAprobados();
		/**
		 * Se recorre cada solicitud de cotizante.
		 */
		this.cargarArchivos.getSolicitudesCotizante().getSolicitudCotizantes().forEach(solicitudRegistro -> {
			/**
			 * Se consulta si el futuro cotizante existe en la lista de caracterizaciones
			 */
			Caracterizacion caracterizacion = this.cargarArchivos.getCaracterizaciones().obtenerCaracterizacion(solicitudRegistro.getPersona());
			/**
			 * Si el futuro cotizante no existe en la lista de caracterizaciones o existe pero el tipo de caracterizacion es embargado
			 */
			if(caracterizacion == null || caracterizacion.getTipoCaracterizacion() == TipoCaracterizacion.EMBARGAR) {
				/**
				 * El futuro cotizante cumple con las politicas de aceptacion?
				 */
				if(validarSolicitudCotizantePoliticas(solicitudRegistro)) {
					this.cargarArchivos.getListaCotizantesAprobados().agregarSolicitudCotizante(new SolicitudCotizanteAprobado(
							solicitudRegistro.getPersona().getTipoDocumento(),
							solicitudRegistro.getPersona().getDocumento(),
							solicitudRegistro.getPersona().getNombreCompleto(),
							solicitudRegistro.getPersona().getFechaNacimiento(),
							solicitudRegistro.getPersona().getDepartamentoNacimiento(),
							solicitudRegistro.getPersona().getCiudadNacimiento(),
							solicitudRegistro.getPersona().getDepartamentoResidencia(),
							solicitudRegistro.getPersona().getCiudadResidencia(),
							solicitudRegistro.esDeclarante()
							));
				} else {
					this.cargarArchivos.getListaCotizantesRechazados().agregarSolicitudCotizante(new SolicitudCotizanteRechazado(
							solicitudRegistro.getPersona().getTipoDocumento(),
							solicitudRegistro.getPersona().getDocumento(),
							solicitudRegistro.getPersona().getNombreCompleto(),
							solicitudRegistro.getPersona().getFechaNacimiento(),
							solicitudRegistro.getPersona().getDepartamentoNacimiento(),
							solicitudRegistro.getPersona().getCiudadNacimiento(),
							solicitudRegistro.getPersona().getDepartamentoResidencia(),
							solicitudRegistro.getPersona().getCiudadResidencia(),
							solicitudRegistro.esDeclarante()
							));
				}
			/**
			 * El futuro cotizante existe en la lista de caracterizaciones y esta inhabilitado
			 */
			} else {
				if(caracterizacion.getTipoCaracterizacion() == TipoCaracterizacion.INHABILITAR) 
					this.cargarArchivos.getListaSolicitudesInhabilitados().agregarListaNegra(new ListaNegra(caracterizacion.getTipoEntidad(), solicitudRegistro.getPersona(), caracterizacion.getTipoCaracterizacion(), FechaUtil.generarFechaFormato()));
			}
		});
	}
	
	/**
	 * Se validan politicas de aceptacion
	 * @param solicitudRegistro
	 */
	private boolean validarSolicitudCotizantePoliticas(SolicitudCotizante solicitudRegistro) {
		/**
		 * Si el futuro cotizante existe en la lista negra y la fecha de registro es inferior a seis meses
		 */
		if(this.cargarArchivos.getListaSolicitudesInhabilitados().existeSolicitudCotizanteInhabilitada(solicitudRegistro.getPersona())) 
			return false;
		/**
		 * Si el futuro cotizante es pre pensionado
		 */
		if(solicitudRegistro.getPrePensionado() == 1) 
			return false;
		/**
		 * El futuro cotizante es civil?
		 */
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.CIVIL) {
			/**
			 * Validar solicitud cotizantes civil
			 */
			return validarSolicitudCotizanteCivil(solicitudRegistro);
		} else {
			/**
			 * Validar solicitud cotizantes NO civil
			 */
			return validarSolicitudCotizanteNoCivil(solicitudRegistro);
		}
	}
	
	/**
	 * Se valida si la solicitud del cotizante cumple con las politicas para los NO civiles
	 * @param solicitudRegistro
	 * @return
	 */
	private boolean validarSolicitudCotizanteNoCivil(SolicitudCotizante solicitudRegistro) {
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.ARMADA) {
			if(solicitudRegistro.getCondecoracion() == 1)
				return true;
			else 
				return validarSolicitudCotizanteCivil(solicitudRegistro);
		}
		
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.INPEC) {
			if(solicitudRegistro.getHijosInpec() == 0) 
				return true;
			else 
				if(solicitudRegistro.getCondecoracion() == 1)
					return true;
				else 
					return validarSolicitudCotizanteCivil(solicitudRegistro);
		}
		
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.POLICIA) {
			if(solicitudRegistro.getFamiliaPolicia() == 1) 
				if(solicitudRegistro.getPersona().getEdad() > 18)
					return true;
				else 
					return validarSolicitudCotizanteCivil(solicitudRegistro);
		}
		
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.MINSALUD || solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.MININTERIOR) {
			if(solicitudRegistro.getObservacionDisciplinaria() == 0)
				return true;
			else 
				return false;
		}
				
		return false;
	}
	
	/**
	 * Se valida si la solicitud del cotizante cumple con las politicas para los civiles
	 * @param solicitudRegistro
	 * @return
	 */
	private boolean validarSolicitudCotizanteCivil(SolicitudCotizante solicitudRegistro) {
		if(solicitudRegistro.getPersona().getDepartamentoNacimiento().equals(solicitudRegistro.getPersona().getDepartamentoResidencia()) && solicitudRegistro.getPersona().getCiudadNacimiento().equals(solicitudRegistro.getPersona().getCiudadResidencia())) 
			return false;
		
		if(solicitudRegistro.getPersona().getEdad() > 40) {
			return false;
		} else {
			if(solicitudRegistro.getFondoOrigen() == FondosOrigen.PORVERNIR)
				if(solicitudRegistro.getNumeroSemanas() < 800)
					return true;
				else 
					return false;
			
			if(solicitudRegistro.getFondoOrigen() == FondosOrigen.PROTECCION)
				if(solicitudRegistro.getNumeroSemanas() < 590)
					return true;
				else 
					return false;
			
			if(solicitudRegistro.getFondoOrigen() == FondosOrigen.COLFONDOS)
				if(solicitudRegistro.getNumeroSemanas() < 300)
					return true;
				else 
					return false;
			
			if(solicitudRegistro.getFondoOrigen() == FondosOrigen.OLD_MUTUAL)
				if(solicitudRegistro.getNumeroSemanas() < 100)
					return true;
				else 
					return false;
			
			if(solicitudRegistro.getFondoOrigen() == FondosOrigen.EXTRANJERO)
				return true;
		}
		return false;
	}
	
}