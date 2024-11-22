package proyecto.proyectofinaled.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FechaUtil {

    public static boolean validarFormatoFecha(String fecha) {
        if (fecha == null || (fecha.length() != 7 && fecha.length() != 8)) {
            return false;
        }
        
        if (fecha.length() == 7) {
            if (Character.isDigit(fecha.charAt(1))) {
                fecha = fecha.substring(0, 2) + "0" + fecha.substring(2);
            } else {
                fecha = "0" + fecha;
            }
        }
        
        String regex = "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[0-2])(\\d{4})$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fecha);

        if (matcher.matches()) {
            int dia = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(2, 4));
            int año = Integer.parseInt(fecha.substring(4, 8));

            if (mes == 2) {
                if (esAñoBisiesto(año)) {
                    return dia <= 29; 
                } else {
                    return dia <= 28; 
                }
            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) { 
                return dia <= 30;
            } else { 
                return dia <= 31;
            }
        }
        return false;
    }
    
    private static boolean esAñoBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }
    
    public static boolean esNumero(String valor) {
	    if (valor == null || valor.isEmpty()) {
	        return false;
	    }
	    
	    try {
	        Double.parseDouble(valor);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
    
    public static String generarFechaFormato() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyy");
        String fechaFormateada = fechaActual.format(formato);
        return fechaFormateada;
    }
    
    public static String obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        return fechaActual.format(formatter);
    }
    
    public static boolean fechaInhabilitadoSeisMeses(String fechaRegistro) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate fechaRegistroDate = LocalDate.parse(fechaRegistro, formatter);

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaHaceSeisMeses = fechaActual.minusMonths(6);

        if (fechaRegistroDate.isBefore(fechaHaceSeisMeses) || fechaRegistroDate.isEqual(fechaHaceSeisMeses)) {
            return false;
        }
        
        return true;
    }
}