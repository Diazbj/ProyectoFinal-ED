package proyecto.proyectofinaled.csv;


import proyecto.proyectofinaled.Model.Ciudad;

import java.io.IOException;

public class CiudadDao extends ADao<Ciudad,String>{

    public CiudadDao() throws IOException {
        super("C:\\Users\\DiazJ\\Documents\\Universidad\\Estructura De Datos\\colpensionex\\colpensionex\\src\\main\\resources\\persistenciacsv\\esquemas\\ciudad.csv");
    }

}
