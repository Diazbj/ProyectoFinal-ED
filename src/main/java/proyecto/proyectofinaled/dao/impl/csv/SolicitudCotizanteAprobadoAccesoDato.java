package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.dto.SolicitudCotizanteAprobadoDTO;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class SolicitudCotizanteAprobadoAccesoDato extends AccesoDatosCsv<SolicitudCotizanteAprobadoDTO, String>{

	public SolicitudCotizanteAprobadoAccesoDato() throws IOException {
		super(RutasConstantes.getDirectorioSolicitud() + "/listaSolicitudCotizantesAprobados.csv");
	}

}