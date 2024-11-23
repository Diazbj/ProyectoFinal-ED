package proyecto.proyectofinaled.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RutasConstantes {
    private static final Properties properties = new Properties();
    
    static {
        try {
            // Agregar logs de diagnóstico
            ClassLoader classLoader = RutasConstantes.class.getClassLoader();
            System.out.println("Buscando application.properties en el classpath...");
            
            try (InputStream input = classLoader.getResourceAsStream("application.properties")) {
                if (input == null) {
                    // Intentar listar recursos disponibles para diagnóstico
                    System.out.println("No se pudo encontrar application.properties");
                    throw new IllegalStateException("No se puede encontrar application.properties en el classpath");
                }
                System.out.println("Archivo application.properties encontrado exitosamente");
                properties.load(input);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error al cargar application.properties", e);
        }
    }

    public static String getDirectorioSolicitudCaracterizaciones() {
        return properties.getProperty("directorio.caracterizaciones");
    }

    public static String getDirectorioSolicitudProceso() {
        return properties.getProperty("directorio.proceso");
    }

    public static String getDirectorioSolicitudEntrantes() {
        return properties.getProperty("directorio.entrantes");
    }

    public static String getDirectorioSolicitudProcesadas() {
        return properties.getProperty("directorio.procesadas");
    }

    public static String getDirectorioSolicitudUtilitario() {
        return properties.getProperty("directorio.utilitario");
    }

    public static String getDirectorioSolicitud() {
        return properties.getProperty("directorio.base");
    }

    public static int getTrasladosAprobadosXDia() {
        return Integer.parseInt(properties.getProperty("traslados.max.dia"));
    }

    public static String getArchivoTiposDocumento() {
        return properties.getProperty("directorio.utilitario") + "/" + 
               properties.getProperty("archivo.tipos.documento");
    }
}