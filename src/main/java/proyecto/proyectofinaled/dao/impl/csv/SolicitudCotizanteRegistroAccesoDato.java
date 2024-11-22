package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.dto.SolicitudCotizanteDTO;

public class SolicitudCotizanteRegistroAccesoDato extends AccesoDatosCsv<SolicitudCotizanteDTO, String>{

	public SolicitudCotizanteRegistroAccesoDato(String rutaArchivo) throws IOException {
		super(rutaArchivo);
	}

}
