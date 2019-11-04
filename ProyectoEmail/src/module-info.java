module ProyectoEmail {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    exports es.abel.dam;
    exports es.abel.dam.view;


    opens es.abel.dam.view to javafx.fxml;
}