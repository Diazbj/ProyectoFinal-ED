package proyecto.proyectofinaled.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;   
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DirectorioUtil {
    
    public boolean crearDirectorio(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()) {
            return directorio.mkdirs();
        }
        return false;
    }

    public void moverArchivo(String rutaOrigen, String rutaDestino) throws IOException {
        try {
            File destino = new File(rutaDestino);
            if (destino.exists()) {
                if (destino.isDirectory()) {
                    File origen = new File(rutaOrigen);
                    String nombreArchivo = origen.getName();
                    Files.move(Paths.get(rutaOrigen), 
                              Paths.get(rutaDestino + File.separator + nombreArchivo),
                              java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.move(Paths.get(rutaOrigen), 
                              Paths.get(rutaDestino),
                              java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                Files.move(Paths.get(rutaOrigen), Paths.get(rutaDestino));
            }
        } catch (NoSuchFileException e) {
            System.out.println("Archivo no encontrado: " + rutaOrigen);
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

    private void comprimirDirectorioRecursivo(File archivo, String nombreArchivo, ZipOutputStream zipOut)
            throws IOException {
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

    public boolean limpiarCarpeta(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);

        if (!directorio.exists() || !directorio.isDirectory()) {
            return false;
        }

        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    limpiarCarpeta(archivo.getAbsolutePath());
                }
                archivo.delete();
            }
        }
        return true;
    }

}
