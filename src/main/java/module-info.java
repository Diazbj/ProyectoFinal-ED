module proyecto.proyectofinaled {
    requires javafx.controls;
    requires javafx.fxml;


    opens proyecto.proyectofinaled to javafx.fxml;
    exports proyecto.proyectofinaled;
}