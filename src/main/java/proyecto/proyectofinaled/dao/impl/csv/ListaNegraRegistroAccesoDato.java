package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.dto.ListaNegraDTO;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class ListaNegraRegistroAccesoDato extends AccesoDatosCsv<ListaNegraDTO, String>{

	public ListaNegraRegistroAccesoDato() throws IOException {
		super(RutasConstantes.getDirectorioSolicitud()  + "/listaSolicitudInhabilitados.csv");
	}
	
}