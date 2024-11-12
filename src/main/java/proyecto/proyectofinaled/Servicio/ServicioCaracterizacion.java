package proyecto.proyectofinaled.Servicio;

import proyecto.proyectofinaled.Model.Caracterizacion;
import proyecto.proyectofinaled.Model.CaracterizacionRegistro;
import proyecto.proyectofinaled.Model.Persona;
import proyecto.proyectofinaled.Model.TipoEntidad;
import proyecto.proyectofinaled.Utils.GestorArchivos;
import proyecto.proyectofinaled.Utils.LectorArchivosUtil;
import proyecto.proyectofinaled.csv.CaracterizacionDao;

import java.io.IOException;
import java.util.ArrayList;

public class ServicioCaracterizacion {
    private static final String DIRECTORIO="C:\\Users\\DiazJ\\Documents\\Universidad\\Estructura De Datos\\ProyectoFinal\\ProyectoFinal-ED\\src\\main\\resources\\Persistencia\\Archivos\\Caracterizaciones";
    private static final int TAMANO_NOMBRE_ARCHIVO = 14;

    private GestorArchivos gestorArchivos;
    private ArrayList<Caracterizacion> caracterizaciones;

    public ServicioCaracterizacion(GestorArchivos gestorArchivos) {
        this.gestorArchivos = gestorArchivos;
        this.caracterizaciones = cargarCaracterizaciones();
    }

    private ArrayList<Caracterizacion> cargarCaracterizaciones(){
        ArrayList<Caracterizacion> caracterizaciones = new ArrayList<>();

        for(String a: new LectorArchivosUtil().obtenerNombresObjetosEnDirectorio(DIRECTORIO)) {
            if(validarEstructuraNombreArchivo(a)) {
                try {
                    for(CaracterizacionRegistro c: new CaracterizacionDao(DIRECTORIO + "\\" + a).obtenerTodos()) {
                        if(validarEstructuraRegistroArchivo(c)) {
                            caracterizaciones.add(new Caracterizacion(
                                    TipoEntidad.getTipoEntidad(a.substring(0,  2)),
                                    (new Persona(this.gestorArchivos.getArchivosUtilitario().getTipoDocumento(c.getTipoDocumento()), c.getDocumento(), c.getNombreCompleto())),
                                    TipoCaracterizacion.getTipoCaracterizacion(c.getCaracterizacion())));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return caracterizaciones;
    }

    private boolean validarEstructuraNombreArchivo(String nombreArchivo) {
        if(nombreArchivo.length() > TAMANO_NOMBRE_ARCHIVO || !TipoEntidad.existe(nombreArchivo.substring(0,  2)) || !Herramientas.validarFormatoFecha(nombreArchivo.substring(2, 10))) {
            return false;
        }
        return true;
    }

    private boolean validarEstructuraRegistroArchivo(CaracterizacionRegistro caracterizacionRegistro) {
        if(!this.cargarArchivos.getArchivosUtilitario().existeTipoDocumento(caracterizacionRegistro.getTipoDocumento()) || !TipoCaracterizacion.existe(caracterizacionRegistro.getCaracterizacion())) {
            return false;
        }
        return true;
    }

    public TipoCaracterizacion existeCaracterizacion(Persona persona) {
        for(Caracterizacion c: this.caracterizaciones) {
            if(c.getPersona().getTipoDocumento().equals(persona.getTipoDocumento()) && c.getPersona().getDocumento().equals(persona.getDocumento())) {
                return c.getTipoCaracterizacion();
            }
        }
        return null;
    }

}
