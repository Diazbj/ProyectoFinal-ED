package proyecto.proyectofinaled.Utils;

import proyecto.proyectofinaled.Model.Cotizante;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ArchivoCSV {

    private String nombreArchivo;

    public ArchivoCSV(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public LinkedList<Cotizante> leerArchivoCSV() {
        LinkedList<Cotizante> cotizantes = new LinkedList<>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {

            while ((linea = br.readLine()) != null) {
                // Dividimos la línea en los datos separados por coma
                String[] datos = linea.split(",");

                // Convertimos el último dato "Embargado" o "No embargado" en booleano
                boolean embargado = datos[6].equalsIgnoreCase("Embargado");

                // Creamos un objeto Cotizante con los datos
                Cotizante cotizante = new Cotizante(
                        datos[0], // Nombre
                        datos[1], // Apellido
                        datos[2], // Cédula
                        datos[3], // Teléfono
                        datos[4], // Email
                        datos[5], // Fondo de Pensión
                        embargado  // Estado de embargo
                );

                // Agregamos el cotizante a la lista
                cotizantes.add(cotizante);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cotizantes;
    }

    public Map<String, Cotizante> leerArchivoCSVMap() {
        Map<String, Cotizante> cotizantes = new HashMap<>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                // Crear un objeto Cotizante
                Cotizante cotizante = new Cotizante(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6].equalsIgnoreCase("Embargado"));
                // Añadir el cotizante al mapa usando la cédula como clave
                cotizantes.put(cotizante.getCedula(), cotizante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cotizantes;
    }
}
