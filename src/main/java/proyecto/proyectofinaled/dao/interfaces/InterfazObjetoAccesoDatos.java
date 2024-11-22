package proyecto.proyectofinaled.dao.interfaces;

import java.util.List;

    public interface InterfazObjetoAccesoDatos<ClaseEntidad, TipoId> {

        public List<ClaseEntidad> obtenerTodos();
    }
