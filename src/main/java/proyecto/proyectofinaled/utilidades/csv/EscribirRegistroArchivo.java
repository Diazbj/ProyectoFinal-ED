package proyecto.proyectofinaled.utilidades.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class EscribirRegistroArchivo<T> {
    private String rutaArchivo;

    public EscribirRegistroArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void escribirObjeto(T objeto) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String[] valores = convertirObjetoAArreglo(objeto);

            StringBuilder linea = new StringBuilder();
            for (int i = 0; i < valores.length; i++) {
                linea.append(valores[i]);
                if (i < valores.length - 1) {
                    linea.append(","); 
                }
            }
            escritor.newLine();
            escritor.write(linea.toString()); 
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    private String[] convertirObjetoAArreglo(T objeto) {
        Field[] campos = objeto.getClass().getDeclaredFields();
        String[] valores = new String[campos.length];

        try {
            for (int i = 0; i < campos.length; i++) {
                campos[i].setAccessible(true);
                Object valor = campos[i].get(objeto);
                valores[i] = valor != null ? valor.toString() : "";
            }
        } catch (IllegalAccessException e) {
            System.err.println("Error al acceder a los atributos del objeto: " + e.getMessage());
        }

        return valores;
    }
    
    public void actualizarListaCotizantes() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            String[] valores = {"tipoDocumento","numeroDocumento","nombreCompleto","fechaNacimiento","departamentoNacimiento","ciudadNacimiento","departamentoResidencia","ciudadResidencia","declaraRenta"};

            StringBuilder linea = new StringBuilder();
            for (int i = 0; i < valores.length; i++) {
                linea.append(valores[i]);
                if (i < valores.length - 1) {
                    linea.append(","); 
                }
            }
            escritor.write(linea.toString()); 
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
}