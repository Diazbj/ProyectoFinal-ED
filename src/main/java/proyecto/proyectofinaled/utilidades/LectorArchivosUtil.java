package proyecto.proyectofinaled.utilidades;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LectorArchivosUtil {

    public static final String SEPARADOR_CSV = ",";

    public static List<String> leerPrimeraLineaCsv(String rutaArchivo) throws IOException {
        if (rutaArchivo == null) {
            throw new IllegalArgumentException("La ruta del archivo no puede ser null");
        }
        
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            throw new FileNotFoundException("No se encuentra el archivo en: " + archivo.getAbsolutePath());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String primeraLinea = br.readLine();
            if (primeraLinea == null) {
                return new ArrayList<>();
            }
            return Arrays.asList(primeraLinea.split(","));
        }
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(String rutaArchivo)
            throws IOException {
        return leerTodasLasLineasCsv(rutaArchivo, true);
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(
            String rutaArchivo, boolean esSaltarPrimera) throws IOException {

        File archivo = new File(rutaArchivo);
        LinkedList<String[]> lineas = new LinkedList<>();

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea;
            boolean esPrimeraLinea = (esSaltarPrimera) ? true : false;
            while ((linea = lector.readLine()) != null) {

                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }

                String[] arreglo = linea.split(LectorArchivosUtil.SEPARADOR_CSV);
                lineas.add(arreglo);
            }
        }

        return lineas;
    }

}