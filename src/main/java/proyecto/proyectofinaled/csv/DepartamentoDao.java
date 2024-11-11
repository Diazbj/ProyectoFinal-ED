package proyecto.proyectofinaled.csv;



import proyecto.proyectofinaled.Model.Departamento;

import java.io.IOException;

public class DepartamentoDao extends ADao<Departamento, String> {

    public DepartamentoDao() throws IOException {
        super("C:\\Users\\DiazJ\\Documents\\Universidad\\Estructura De Datos\\colpensionex\\colpensionex\\src\\main\\resources\\persistenciacsv\\esquemas\\departamento.csv");
    }
}
