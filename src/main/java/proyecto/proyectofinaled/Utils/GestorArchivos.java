package proyecto.proyectofinaled.Utils;

import proyecto.proyectofinaled.Servicio.ServicioCaracterizacion;

import java.io.IOException;

public class GestorArchivos {
    private ServicioCaracterizacion caracterizaciones;

    public GestorArchivos() throws IOException {
        this.caracterizaciones =new ServicioCaracterizacion(this);
    }

    public ServicioCaracterizacion getCaracterizaciones() {
        return caracterizaciones;
    }

    public void setCaracterizaciones(ServicioCaracterizacion caracterizaciones) {
        this.caracterizaciones = caracterizaciones;
    }
}
