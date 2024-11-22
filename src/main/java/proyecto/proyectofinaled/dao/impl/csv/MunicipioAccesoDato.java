package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.modelos.dominio.Municipio;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class MunicipioAccesoDato extends AccesoDatosCsv<Municipio, String>{

	public MunicipioAccesoDato() throws IOException {
		super(RutasConstantes.getDirectorioSolicitudUtilitario() + "/municipiosColombia.csv");	
	}

}