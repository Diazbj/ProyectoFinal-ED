package proyecto.proyectofinaled.csv;

import proyecto.proyectofinaled.Model.Caracterizacion;

import java.io.IOException;

public class CaracterizacionDao extends ADao<Caracterizacion, String> {

    public CaracterizacionDao(String rutaArchivo) throws IOException {
        super(rutaArchivo);
    }
}
