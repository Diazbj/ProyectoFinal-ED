package proyecto.proyectofinaled.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoCSV {

    private String nombreArchivo;

    public ArchivoCSV(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public List<String[]> leerArchivoCSV() {
        List<String[]> datos = new ArrayList<>();
        String linea;

        try(BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {

            while ((linea=br.readLine())!=null){
                String[] dato=linea.split(",");
                datos.add(dato);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return datos;
    }

}
