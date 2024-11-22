package proyecto.proyectofinaled.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LectorArchivosUtil {

    public static final String SEPARADOR_CSV = ",";

    public static String[] leerPrimeraLineaCsv(String rutaArchivo)
            throws IOException
    {
        File archivo = new File(rutaArchivo);

        try(BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))
        ) {
            String primeraLinea = lector.readLine();

            if(primeraLinea != null) {
                return primeraLinea.split(LectorArchivosUtil.SEPARADOR_CSV);
            }
        }

        return null;
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(String rutaArchivo)
            throws IOException {
        return leerTodasLasLineasCsv(rutaArchivo,true);
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(
            String rutaArchivo, boolean esSaltarPrimera
    ) throws IOException {

        File archivo = new File(rutaArchivo);
        LinkedList<String[]> lineas = new LinkedList<>();

        try(BufferedReader lector = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))
        ) {
            String linea;
            boolean esPrimeraLinea = (esSaltarPrimera) ? true: false;
            while( (linea = lector.readLine()) != null  ) {

                if(esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }

                String[] arreglo = linea.split(LectorArchivosUtil.SEPARADOR_CSV);
                lineas.add( arreglo );
            }
        }

        return lineas;
    }

    public boolean crearDirectorio(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()) {
            return directorio.mkdirs();
        }
        return false;
    }

    public boolean moverArchivo(String rutaArchivoOrigen, String rutaDirectorioDestino) {
        Path origenPath = Paths.get(rutaArchivoOrigen);
        Path destinoPath = Paths.get(rutaDirectorioDestino).resolve(origenPath.getFileName());

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean comprimirDirectorio(String rutaDirectorio, String rutaArchivoZip) {
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists() || !directorio.isDirectory()) {
            return false;
        }

        try (FileOutputStream fos = new FileOutputStream(rutaArchivoZip);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            comprimirDirectorioRecursivo(directorio, directorio.getName(), zipOut);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void comprimirDirectorioRecursivo(File archivo, String nombreArchivo, ZipOutputStream zipOut) throws IOException {
        if (archivo.isDirectory()) {
            if (nombreArchivo.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(nombreArchivo));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(nombreArchivo + "/"));
                zipOut.closeEntry();
            }
            for (File hijo : archivo.listFiles()) {
                comprimirDirectorioRecursivo(hijo, nombreArchivo + "/" + hijo.getName(), zipOut);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(archivo)) {
                ZipEntry zipEntry = new ZipEntry(nombreArchivo);
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            }
        }
    }
}
