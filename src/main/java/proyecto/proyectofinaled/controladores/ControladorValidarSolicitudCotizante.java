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
		System.out.println("Iniciando actualización de listas...");
		this.cargarArchivos.getCaracterizaciones().actualizarCaracterizaciones();
		this.cargarArchivos.getSolicitudesCotizante().actualizarSolicitudCotizaciones();
		this.cargarArchivos.getListaSolicitudesInhabilitados().actualizarListaNegra();
		this.cargarArchivos.getListaCotizantesRechazados().actualizarSolicitudCotizantesRechazados();
		this.cargarArchivos.getListaCotizantesAprobados().actualizarSolicitudCotizantesAprobados();
		System.out.println("Listas actualizadas exitosamente");

		System.out.println("Iniciando procesamiento de solicitudes de cotizantes...");
		this.cargarArchivos.getSolicitudesCotizante().getSolicitudCotizantes().forEach(solicitudRegistro -> {
			System.out.println("Procesando solicitud para: " + solicitudRegistro.getPersona().getNombreCompleto());
			
			Caracterizacion caracterizacion = this.cargarArchivos.getCaracterizaciones().obtenerCaracterizacion(solicitudRegistro.getPersona());
			
			if(caracterizacion == null || caracterizacion.getTipoCaracterizacion() == TipoCaracterizacion.EMBARGAR) {
				System.out.println("Validando políticas de aceptación...");
				if(validarSolicitudCotizantePoliticas(solicitudRegistro)) {
					System.out.println("Solicitud APROBADA - Agregando a lista de aprobados");
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
					System.out.println("Solicitud RECHAZADA - Agregando a lista de rechazados");
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
			} else {
				if(caracterizacion.getTipoCaracterizacion() == TipoCaracterizacion.INHABILITAR) {
					System.out.println("Cotizante INHABILITADO - Agregando a lista negra");
					this.cargarArchivos.getListaSolicitudesInhabilitados().agregarListaNegra(new ListaNegra(caracterizacion.getTipoEntidad(), solicitudRegistro.getPersona(), caracterizacion.getTipoCaracterizacion(), FechaUtil.generarFechaFormato()));
				}
			}
		});
		System.out.println("Procesamiento de solicitudes finalizado");
	}
	
	private boolean validarSolicitudCotizantePoliticas(SolicitudCotizante solicitudRegistro) {
		System.out.println("Verificando estado en lista negra...");
		if(this.cargarArchivos.getListaSolicitudesInhabilitados().existeSolicitudCotizanteInhabilitada(solicitudRegistro.getPersona())) {
			System.out.println("Cotizante en lista negra - Rechazado");
			return false;
		}
		
		System.out.println("Verificando estado de pre-pensionado...");
		if(solicitudRegistro.getPrePensionado() == 1) {
			System.out.println("Cotizante es pre-pensionado - Rechazado");
			return false;
		}
		
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.CIVIL) {
			System.out.println("Procesando solicitud como civil");
			return validarSolicitudCotizanteCivil(solicitudRegistro);
		} else {
			System.out.println("Procesando solicitud como NO civil");
			return validarSolicitudCotizanteNoCivil(solicitudRegistro);
		}
	}
	
	private boolean validarSolicitudCotizanteNoCivil(SolicitudCotizante solicitudRegistro) {
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.ARMADA) {
			System.out.println("Validando reglas para ARMADA");
			if(solicitudRegistro.getCondecoracion() == 1)
				return true;
			else 
				return validarSolicitudCotizanteCivil(solicitudRegistro);
		}
		
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.INPEC) {
			System.out.println("Validando reglas para INPEC");
			if(solicitudRegistro.getHijosInpec() == 0) 
				return true;
			else 
				if(solicitudRegistro.getCondecoracion() == 1)
					return true;
				else 
					return validarSolicitudCotizanteCivil(solicitudRegistro);
		}
		
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.POLICIA) {
			System.out.println("Validando reglas para POLICIA");
			if(solicitudRegistro.getFamiliaPolicia() == 1) 
				if(solicitudRegistro.getPersona().getEdad() > 18)
					return true;
				else 
					return validarSolicitudCotizanteCivil(solicitudRegistro);
		}
		
		if(solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.MINSALUD || solicitudRegistro.getInstitucionPublica() == InstitucionesPublicas.MININTERIOR) {
			System.out.println("Validando reglas para MINSALUD/MININTERIOR");
			if(solicitudRegistro.getObservacionDisciplinaria() == 0)
				return true;
			else 
				return false;
		}
				
		return false;
	}
	
	private boolean validarSolicitudCotizanteCivil(SolicitudCotizante solicitudRegistro) {
		System.out.println("Validando ubicación...");
		if(solicitudRegistro.getPersona().getDepartamentoNacimiento().equals(solicitudRegistro.getPersona().getDepartamentoResidencia()) && 
		   solicitudRegistro.getPersona().getCiudadNacimiento().equals(solicitudRegistro.getPersona().getCiudadResidencia())) {
			System.out.println("Mismo lugar de nacimiento y residencia - Rechazado");
			return false;
		}
		
		System.out.println("Validando edad y fondo de origen...");
		if(solicitudRegistro.getPersona().getEdad() > 40) {
			System.out.println("Edad mayor a 40 - Rechazado");
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