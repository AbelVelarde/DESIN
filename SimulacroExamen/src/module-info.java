module SimulacroExamen {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    exports abel.simulacro;
    exports abel.simulacro.logica;
    exports abel.simulacro.views;
    exports abel.simulacro.models;

    opens abel.simulacro.views to javafx.fxml;

}