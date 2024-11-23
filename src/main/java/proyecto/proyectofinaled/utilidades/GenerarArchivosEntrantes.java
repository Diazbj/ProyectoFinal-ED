package proyecto.proyectofinaled.utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarArchivosEntrantes {

    private final String[] TIPOS_DOCUMENTO = {
            "CC", // Cédula de Ciudadanía
            "TI", // Tarjeta de Identidad
            "CE" // Cédula de Extranjería
    };

    public void generarArchivosEntrantes() {
        // Primero generamos los archivos CSV iniciales
        generarArchivosCsvIniciales();
        
        int totalArchivos = 300;
        int registrosPorArchivo = 100;
        String ruta = System.getProperty("user.dir") + "/src/main/resources/SolicitudesEntrantes/";
        Random random = new Random();
        File newFile = new File(ruta);
        try {
            if (!newFile.exists()) {
                if (!newFile.mkdirs()) {
                    System.err.println("Error al crear el directorio: " + ruta);
                    return;
                }
            }
        } catch (SecurityException e) {
            System.err.println("Error de permisos al crear el directorio: " + ruta);
            e.printStackTrace();
            return;
        }
        for (int i = 1; i <= totalArchivos; i++) {
            String fileName = ruta + "SolicitudEntrante#" + i + ".csv";

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(
                        "tipoDocumento,numeroDocumento,nombreCompleto,fechaNacimiento,departamentoNacimiento,ciudadNacimiento,departamentoResidencia,ciudadResidencia,fondoOrigen,prePensionado,institucionPublica,condecoracion,hijosInpec,familiaPolicia,observacionDisciplinaria,numeroSemanas,declaraRenta\n");

                for (int j = 0; j < registrosPorArchivo; j++) {
                    String registro = generarRegistro(random, j % 10 == 0); // Genera basura en 10% de los casos
                    writer.write(registro + "\n");
                }
            } catch (IOException e) {
                System.err.println("Error al generar el archivo: " + fileName);
                e.printStackTrace();
            }
        }
    }

    private void generarArchivosCsvIniciales() {
        String rutaBase = System.getProperty("user.dir") + "/src/main/resources/";
        
        try {
            // Crear directorio si no existe
            File dirUtilitario = new File(rutaBase);
            if (!dirUtilitario.exists()) {
                if (!dirUtilitario.mkdirs()) {
                    System.err.println("Error al crear el directorio: " + rutaBase);
                    return;
                }
            }

            // Generar listaCotizantes.csv (vacío con headers)
            try (FileWriter writer = new FileWriter(rutaBase + "listaCotizantes.csv")) {
                writer.write("tipoDocumento,numeroDocumento,nombreCompleto,fechaNacimiento,departamentoNacimiento,ciudadNacimiento,departamentoResidencia,ciudadResidencia");
            }

            // Generar listaSolicitudInhabilitados.csv (vacío con headers)
            try (FileWriter writer = new FileWriter(rutaBase + "listaSolicitudInhabilitados.csv")) {
                writer.write("tipoEntidad,tipoDocumento,documento,nombreCompleto,tipoCaracterizacion,fechaRegistro");
            }

            // Generar listaSolicitudCotizantesAprobados.csv (vacío con headers)
            try (FileWriter writer = new FileWriter(rutaBase + "listaSolicitudCotizantesAprobados.csv")) {
                writer.write("tipoDocumento,numeroDocumento,nombreCompleto,fechaNacimiento,departamentoNacimiento,ciudadNacimiento,departamentoResidencia,ciudadResidencia");
            }

            // Generar listaSolicitudCotizantesRechazados.csv (vacío con headers)
            try (FileWriter writer = new FileWriter(rutaBase + "listaSolicitudCotizantesRechazados.csv")) {
                writer.write("tipoDocumento,numeroDocumento,nombreCompleto,fechaNacimiento,departamentoNacimiento,ciudadNacimiento,departamentoResidencia,ciudadResidencia");
            }

            // Generar y llenar tiposDocumento.csv
            try (FileWriter writer = new FileWriter(rutaBase + "tiposDocumento.csv")) {
                writer.write("codigoTipoDocumento,nombreTipoDocumento\n");
                writer.write("CC,Cedula de ciudadanía\n");
                writer.write("TI,Tarjeta de identidad\n");
                writer.write("CE,Cédula de extranjería");
            }

        } catch (IOException e) {
            System.err.println("Error al generar los archivos CSV iniciales");
            e.printStackTrace();
        }
    }

    private String generarRegistro(Random random, boolean generarBasura) {
        String tipoDocumento = TIPOS_DOCUMENTO[random.nextInt(TIPOS_DOCUMENTO.length)];
        String numeroDocumento = generarNumeroDocumento(random);
        String nombreCompleto = generarNombreCompleto(random);
        String fechaNacimiento = generarFechaNacimiento(random, generarBasura);
        int departamentoNacimiento = generarEntero(random, 1, 100);
        int ciudadNacimiento = generarEntero(random, 1, 100);
        int departamentoResidencia = generarEntero(random, 1, 100);
        int ciudadResidencia = generarEntero(random, 1, 100);
        int fondoOrigen = generarEntero(random, 1, 6);
        int institucionPublica = generarEntero(random, 1, 6);
        int prePensionado = random.nextInt(2);
        int condecoracion = random.nextInt(2);
        int hijosInpec = random.nextInt(2);
        int familiaPolicia = random.nextInt(2);
        int observacionDisciplinaria = random.nextInt(2);
        int numeroSemanas = generarEntero(random, 1, 1000);
        int declaraRenta = random.nextInt(2);

        return String.join(",",
                tipoDocumento,
                numeroDocumento,
                nombreCompleto,
                fechaNacimiento,
                String.valueOf(departamentoNacimiento),
                String.valueOf(ciudadNacimiento),
                String.valueOf(departamentoResidencia),
                String.valueOf(ciudadResidencia),
                String.valueOf(fondoOrigen),
                String.valueOf(prePensionado),
                String.valueOf(institucionPublica),
                String.valueOf(condecoracion),
                String.valueOf(hijosInpec),
                String.valueOf(familiaPolicia),
                String.valueOf(observacionDisciplinaria),
                String.valueOf(numeroSemanas),
                String.valueOf(declaraRenta));
    }

    private String generarNumeroDocumento(Random random) {
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }

    private String generarNombreCompleto(Random random) {
        String[] nombres = { "Juan", "María", "Carlos", "Ana", "Luis", "Sofía", "Miguel", "Laura", "Daniela", "David",
                "Melissa", "Marcela", "Gabriela", "Manuela" };
        String[] apellidos = { "García", "Rodríguez", "Martínez", "Hernández", "López", "González", "Cifuentes",
                "Murillo", "Perez", "Castaneda", "Cartagena", "Hurtado" };
        return nombres[random.nextInt(nombres.length)] + " " + apellidos[random.nextInt(apellidos.length)];
    }

    private String generarFechaNacimiento(Random random, boolean generarBasura) {
        int year = generarBasura ? generarEntero(random, 1900, 2024) : generarEntero(random, 1924, 2009);
        int month = generarEntero(random, 1, 12);
        int day = generarEntero(random, 1, 28);
        return String.format("%02d%02d%04d", day, month, year);
    }

    private int generarEntero(Random random, int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
