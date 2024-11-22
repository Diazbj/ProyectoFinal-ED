package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.dto.CotizanteDTO;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class CotizanteRegistroAccesoDato extends AccesoDatosCsv<CotizanteDTO, String>{

	public CotizanteRegistroAccesoDato() throws IOException {
		super(RutasConstantes.getDirectorioSolicitud() + "/listaCotizantes.csv");
	}

}