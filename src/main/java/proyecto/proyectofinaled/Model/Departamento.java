package proyecto.proyectofinaled.Model;

public class Departamento {

    private Integer idDepa;
    private Integer idPais;
    private String nombre;

    public Departamento() {
        this.idDepa = null;
        this.idPais = null;
        this.nombre = null;
    }

    public Departamento(Integer idDepa, Integer idPais, String nombre) {
        this.idDepa = idDepa;
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public Integer getIdDepa() {
        return idDepa;
    }

    public void setIdDepa(Integer idDepa) {
        this.idDepa = idDepa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "idDepa=" + idDepa +
                ", idPais=" + idPais +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
