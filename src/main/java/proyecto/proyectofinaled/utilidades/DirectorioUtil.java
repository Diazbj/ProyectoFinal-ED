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
        System.out.println("Intentando crear directorio: " + rutaDirectorio);
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()) {
            boolean creado = directorio.mkdirs();
            System.out.println("Directorio creado: " + creado);
            return creado;
        }
        System.out.println("El directorio ya existe");
        return false;
    }

    public void moverArchivo(String rutaOrigen, String rutaDestino) throws IOException {
        System.out.println("Moviendo archivo de " + rutaOrigen + " a " + rutaDestino);
        try {
            File destino = new File(rutaDestino);
            if (destino.exists()) {
                if (destino.isDirectory()) {
                    File origen = new File(rutaOrigen);
                    String nombreArchivo = origen.getName();
                    System.out.println("Moviendo archivo a directorio existente: " + rutaDestino);
                    Files.move(Paths.get(rutaOrigen), 
                              Paths.get(rutaDestino + File.separator + nombreArchivo),
                              java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("Reemplazando archivo existente en destino");
                    Files.move(Paths.get(rutaOrigen), 
                              Paths.get(rutaDestino),
                              java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                System.out.println("Moviendo archivo a nueva ubicación");
                Files.move(Paths.get(rutaOrigen), Paths.get(rutaDestino));
            }
            System.out.println("Archivo movido exitosamente");
        } catch (NoSuchFileException e) {
            System.out.println("Archivo no encontrado: " + rutaOrigen);
        }
    }

    public boolean comprimirDirectorio(String rutaDirectorio, String rutaArchivoZip) {
        System.out.println("Iniciando compresión del directorio: " + rutaDirectorio);
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("El directorio no existe o no es un directorio válido");
            return false;
        }

        try (FileOutputStream fos = new FileOutputStream(rutaArchivoZip);
                ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            System.out.println("Comprimiendo archivos...");
            comprimirDirectorioRecursivo(directorio, directorio.getName(), zipOut);
            System.out.println("Compresión completada exitosamente");
            return true;

        } catch (IOException e) {
            System.out.println("Error durante la compresión: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void comprimirDirectorioRecursivo(File archivo, String nombreArchivo, ZipOutputStream zipOut)
            throws IOException {
        if (archivo.isDirectory()) {
            System.out.println("Procesando directorio: " + nombreArchivo);
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
            System.out.println("Comprimiendo archivo: " + nombreArchivo);
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
        System.out.println("Obteniendo lista de archivos en: " + rutaDirectorio);
        List<String> nombres = new ArrayList<>();
        File directorio = new File(rutaDirectorio);
        if (directorio.exists() && directorio.isDirectory()) {
            for (File archivo : directorio.listFiles()) {
                nombres.add(archivo.getName());
            }
        }
        System.out.println("Se encontraron " + nombres.size() + " archivos");
        return nombres;
    }

    public boolean limpiarCarpeta(String rutaDirectorio) {
        System.out.println("Iniciando limpieza de carpeta: " + rutaDirectorio);
        File directorio = new File(rutaDirectorio);

        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("El directorio no existe o no es un directorio válido");
            return false;
        }

        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            System.out.println("Eliminando " + archivos.length + " archivos/directorios");
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("Limpiando subdirectorio: " + archivo.getName());
                    limpiarCarpeta(archivo.getAbsolutePath());
                }
                boolean eliminado = archivo.delete();
                System.out.println("Archivo " + archivo.getName() + " eliminado: " + eliminado);
            }
        }
        System.out.println("Limpieza completada");
        return true;
    }

    public boolean crearArchivo(String rutaArchivo) {
        System.out.println("Intentando crear archivo: " + rutaArchivo);
        File archivo = new File(rutaArchivo);
        
        // Crear directorios padre si no existen
        File directorioPadre = archivo.getParentFile();
        if (directorioPadre != null && !directorioPadre.exists()) {
            boolean directorioCreado = directorioPadre.mkdirs();
            if (!directorioCreado) {
                System.out.println("No se pudo crear el directorio padre");
                return false;
            }
        }
        
        try {
            boolean creado = archivo.createNewFile();
            System.out.println("Archivo creado: " + creado);
            return creado;
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            return false;
        }
    }

}
