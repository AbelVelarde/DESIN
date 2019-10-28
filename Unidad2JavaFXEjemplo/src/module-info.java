module Unidad2JavaFXEjemplo {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    exports es.abel.dam;
    exports es.abel.dam.models;
    exports es.abel.dam.logica;
    exports es.abel.dam.view;
    exports es.abel.dam.utils;

    opens es.abel.dam.view to javafx.fxml;

}