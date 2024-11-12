package proyecto.proyectofinaled.csv;


import proyecto.proyectofinaled.Model.Ciudad;

import java.io.IOException;

public class CiudadDao extends ADao<Ciudad,String>{

    public CiudadDao() throws IOException {
        super("C:\\Users\\DiazJ\\Documents\\Universidad\\Estructura De Datos\\ProyectoFinal\\ProyectoFinal-ED\\src\\main\\resources\\Persistencia\\Archivos\\Departamentos.csv");
    }

}
