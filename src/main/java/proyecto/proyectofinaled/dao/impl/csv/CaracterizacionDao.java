package proyecto.proyectofinaled.dao.impl.csv;

import proyecto.proyectofinaled.dto.CaracterizacionDTO;
import proyecto.proyectofinaled.dao.impl.csv.base.AccesoDatosCsv;

import java.io.IOException;

public class CaracterizacionDao extends AccesoDatosCsv<CaracterizacionDTO, String> {

    public CaracterizacionDao(String rutaArchivo) throws IOException {
        super(rutaArchivo);
    }
}
