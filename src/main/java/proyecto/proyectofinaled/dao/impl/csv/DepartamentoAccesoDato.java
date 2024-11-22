package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.modelos.dominio.Departamento;
import proyecto.proyectofinaled.utilidades.RutasConstantes;

public class DepartamentoAccesoDato extends AccesoDatosCsv<Departamento, String>{

	public DepartamentoAccesoDato() throws IOException {
		super(RutasConstantes.getDirectorioSolicitudUtilitario() + "/departamentosColombia.csv");
	}	

}