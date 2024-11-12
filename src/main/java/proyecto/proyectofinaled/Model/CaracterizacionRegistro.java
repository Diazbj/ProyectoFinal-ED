package proyecto.proyectofinaled.Model;

public class CaracterizacionRegistro {
    private String tipoDocumento;
    private String documento;
    private String nombreCompleto;
    private String caracterizacion;

    public CaracterizacionRegistro(String tipoDocumento, String documento, String nombreCompleto, String caracterizacion) {
        super();
    }

    public CaracterizacionRegistro(String tipoDocumento, String documento, String caracterizacion) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.caracterizacion = caracterizacion;
        this.nombreCompleto = caracterizacion;

    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCaracterizacion() {
        return caracterizacion;
    }

    public void setCaracterizacion(String caracterizacion) {
        this.caracterizacion = caracterizacion;
    }
}
