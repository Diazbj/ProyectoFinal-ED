package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.modelos.dominio.TipoDocumento;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class TipoDocumentoAccesoDato extends AccesoDatosCsv<TipoDocumento, String>{

	public TipoDocumentoAccesoDato() throws IOException {
		super(RutasConstantes.getDirectorioSolicitudUtilitario() + "/tiposDocumento.csv");
	}

}
