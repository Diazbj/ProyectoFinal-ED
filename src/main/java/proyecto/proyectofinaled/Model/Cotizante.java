package proyecto.proyectofinaled.Model;

public class Cotizante {

    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;
    private String email;
    private String fondoPension;
    //private String ciudad;
    private boolean embargado;

    public Cotizante(String nombre, String apellido, String cedula, String telefono, String email, String fondoPension, boolean embargado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.email = email;
        this.fondoPension = fondoPension;
        this.embargado = embargado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFondoPension() {
        return fondoPension;
    }

    public void setFondoPension(String fondoPension) {
        this.fondoPension = fondoPension;
    }

    public boolean isEmbargado() {
        return embargado;
    }

    public void setEmbargado(boolean embargado) {
        this.embargado = embargado;
    }
}
