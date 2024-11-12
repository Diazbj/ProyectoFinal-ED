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

    public List<String> obtenerNombresObjetosEnDirectorio(String rutaDirectorio) {
        List<String> nombres = new ArrayList<>();
        File directorio = new File(rutaDirectorio);
        if (directorio.exists() && directorio.isDirectory()) {
            for (File archivo : directorio.listFiles()) {
                nombres.add(archivo.getName());
            }
        }
        return nombres;
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
