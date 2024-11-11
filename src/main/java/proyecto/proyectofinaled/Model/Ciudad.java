package proyecto.proyectofinaled.Model;

public class Ciudad {
    private Integer idCiud;
    private Integer idDepa;
    private String nombre;

    public Ciudad() {
        this.idCiud = null;
        this.idDepa = null;
        this.nombre = null;
    }
    public Ciudad(Integer idCiud, Integer idDepa, String nombre) {
        this.idCiud = idCiud;
        this.idDepa = idDepa;
        this.nombre = nombre;
    }

    public Integer getIdCiud() {
        return idCiud;
    }

    public void setIdCiud(Integer idCiud) {
        this.idCiud = idCiud;
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
}
