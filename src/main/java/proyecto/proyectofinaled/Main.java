package proyecto.proyectofinaled;

import proyecto.proyectofinaled.Util.ArchivoCSV;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArchivoCSV lector=new ArchivoCSV("src/main/resources/Persistencia/Archivos/Datos");

        List<String[]> datos=lector.leerArchivoCSV();


        for (String[] dato : datos) {
            for (String nombre : dato){
                System.out.print(nombre + " ");
            }
            System.out.println();
        }

    }
}
