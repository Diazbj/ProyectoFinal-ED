package proyecto.proyectofinaled;

import proyecto.proyectofinaled.Model.Cotizante;
import proyecto.proyectofinaled.Utils.ArchivoCSV;
import proyecto.proyectofinaled.Utils.SuperCache;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        // Ruta del archivo CSV (puedes modificarla según la ubicación de tu archivo)
        String rutaArchivo = "src/main/resources/Persistencia/Archivos/Datos";

        // Crear una instancia de ArchivoCSV
        ArchivoCSV archivoCSV = new ArchivoCSV(rutaArchivo);

        // Leer el archivo CSV y obtener la lista de cotizantes
        List<Cotizante> cotizantes = archivoCSV.leerArchivoCSV();

        // Mostrar los cotizantes leídos
//        System.out.println("Lista de Cotizantes:");
//        for (Cotizante cotizante : cotizantes) {
//            System.out.println("Nombre: " + cotizante.getNombre() + " " + cotizante.getApellido());
//            System.out.println("Cédula: " + cotizante.getCedula());
//            System.out.println("Teléfono: " + cotizante.getTelefono());
//            System.out.println("Email: " + cotizante.getEmail());
//            System.out.println("Fondo de Pensión: " + cotizante.getFondoPension());
//            System.out.println("Embargado: " + (cotizante.isEmbargado() ? "Sí" : "No"));
//            System.out.println("---------------------------------------------------");
//        }

        // Crear una instancia de SuperCache
        SuperCache superCache = new SuperCache();

        // Comprobar si los cotizantes ya están en caché
        if (!superCache.estaEnCache(rutaArchivo)) {
            // Leer el archivo CSV y obtener el mapa de cotizantes
            Map<String, Cotizante> cotizantesMap = archivoCSV.leerArchivoCSVMap();
            // Agregar los cotizantes a la caché
            superCache.agregarCotizantes(rutaArchivo, cotizantesMap);
        }

        // Obtener los cotizantes desde la caché
        Map<String, Cotizante> cotizantesDesdeCache = superCache.obtenerCotizantes(rutaArchivo);

        // Mostrar los cotizantes cargados en memoria caché
        System.out.println("Lista de Cotizantes desde la caché:");
//        for (Cotizante cotizante : cotizantesDesdeCache.values()) {
//            System.out.println("Nombre: " + cotizante.getNombre() + " " + cotizante.getApellido());
//            System.out.println("Cédula: " + cotizante.getCedula());
//            System.out.println("Teléfono: " + cotizante.getTelefono());
//            System.out.println("Email: " + cotizante.getEmail());
//            System.out.println("Fondo de Pensión: " + cotizante.getFondoPension());
//            System.out.println("Embargado: " + (cotizante.isEmbargado() ? "Sí" : "No"));
//            System.out.println("---------------------------------------------------");
//        }

        Cotizante cotizanteBuscado = cotizantesDesdeCache.get("1094935673");

        if (cotizanteBuscado != null) {
            // Mostrar detalles del cotizante encontrado
            System.out.println("\nCotizante encontrado:");
            System.out.println("Nombre: " + cotizanteBuscado.getNombre() + " " + cotizanteBuscado.getApellido());
            System.out.println("Cédula: " + cotizanteBuscado.getCedula());
            System.out.println("Teléfono: " + cotizanteBuscado.getTelefono());
            System.out.println("Email: " + cotizanteBuscado.getEmail());
            System.out.println("Fondo de Pensión: " + cotizanteBuscado.getFondoPension());
            System.out.println("Embargado: " + (cotizanteBuscado.isEmbargado() ? "Sí" : "No"));
        } else {
            System.out.println("\nCotizante con cédula " + "1094935673" + " no encontrado.");
        }

    }
}
