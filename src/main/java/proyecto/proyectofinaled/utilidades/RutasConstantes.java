package proyecto.proyectofinaled.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RutasConstantes {
    private static final Properties properties = new Properties();
    
    static {
        try (InputStream input = RutasConstantes.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se puede encontrar application.properties en el classpath");
            }
            properties.load(input);
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
}