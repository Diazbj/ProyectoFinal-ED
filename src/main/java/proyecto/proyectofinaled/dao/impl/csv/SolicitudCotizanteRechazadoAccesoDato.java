package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.dto.SolicitudCotizanteRechazadoDTO;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class SolicitudCotizanteRechazadoAccesoDato extends AccesoDatosCsv<SolicitudCotizanteRechazadoDTO, String>{

	public SolicitudCotizanteRechazadoAccesoDato() throws IOException {
		super(RutasConstantes.getDirectorioSolicitud() + "/listaSolicitudCotizantesRechazados.csv");
	}

}
