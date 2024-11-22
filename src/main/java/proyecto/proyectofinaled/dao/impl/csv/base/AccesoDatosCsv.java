package proyecto.proyectofinaled.dao.impl.csv.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import proyecto.proyectofinaled.dao.interfaces.InterfazObjetoAccesoDatos;
import proyecto.proyectofinaled.utilidades.csv.GestorDeEntidad;

public abstract class AccesoDatosCsv<ClaseEntidad,TipoId> implements InterfazObjetoAccesoDatos<ClaseEntidad, TipoId> {
    private Class<ClaseEntidad> claseEntidad;
    protected GestorDeEntidad gestorDeEntidad;

    @SuppressWarnings("unchecked")
    public AccesoDatosCsv(String rutaArchivo) throws IOException {

        this.claseEntidad = (Class<ClaseEntidad>) (
                (ParameterizedType) getClass().getGenericSuperclass()
        ).getActualTypeArguments()[0];

        this.gestorDeEntidad = new GestorDeEntidad(rutaArchivo);
    }

    @Override
    public List<ClaseEntidad> obtenerTodos() {
        return this.gestorDeEntidad.obtenerTodos(this.claseEntidad);
    }
}
