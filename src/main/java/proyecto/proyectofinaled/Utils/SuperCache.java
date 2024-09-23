package proyecto.proyectofinaled.Utils;

import proyecto.proyectofinaled.Model.Cotizante;
import java.util.HashMap;
import java.util.Map;

public class SuperCache {
    // Map que contendrá los cotizantes de cada archivo CSV leído
    private Map<String, Map<String, Cotizante>> cache;

    public SuperCache() {
        this.cache = new HashMap<>();
    }

    // Método para añadir cotizantes de un archivo CSV a la caché
    public void agregarCotizantes(String nombreArchivo, Map<String, Cotizante> cotizantes) {
        cache.put(nombreArchivo, cotizantes);
    }

    // Método para obtener el mapa de cotizantes por nombre de archivo
    public Map<String, Cotizante> obtenerCotizantes(String nombreArchivo) {
        return cache.get(nombreArchivo);
    }

    // Método para verificar si un archivo ya ha sido cargado en caché
    public boolean estaEnCache(String nombreArchivo) {
        return cache.containsKey(nombreArchivo);
    }

    // Método para buscar un cotizante por cédula en un archivo específico
    public Cotizante buscarCotizantePorCedula(String nombreArchivo, String cedula) {
        Map<String, Cotizante> cotizantes = cache.get(nombreArchivo);
        if (cotizantes != null) {
            return cotizantes.get(cedula); // Devuelve el cotizante si existe
        }
        return null; // Si no existe, devuelve null
    }
}
