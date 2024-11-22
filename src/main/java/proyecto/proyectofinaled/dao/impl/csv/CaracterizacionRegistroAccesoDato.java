package proyecto.proyectofinaled.dao.impl.csv;

import java.io.IOException;

import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;
import proyecto.proyectofinaled.dto.CaracterizacionDTO;

public class CaracterizacionRegistroAccesoDato extends AccesoDatosCsv<CaracterizacionDTO, String>{

	public CaracterizacionRegistroAccesoDato(String rutaArchivo) throws IOException {
		super(rutaArchivo);
	}

}
