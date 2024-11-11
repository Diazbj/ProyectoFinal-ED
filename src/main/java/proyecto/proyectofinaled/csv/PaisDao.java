package proyecto.proyectofinaled.csv;



import proyecto.proyectofinaled.Model.Pais;

import java.io.IOException;

public class PaisDao extends ADao<Pais, Integer> {

    public PaisDao() throws IOException {
        super("C:\\Users\\DiazJ\\Documents\\Universidad\\Estructura De Datos\\colpensionex\\colpensionex\\src\\main\\resources\\persistenciacsv\\esquemas\\pais.csv");
    }
}
