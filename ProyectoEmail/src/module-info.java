module ProyectoEmail {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;

    requires commons.email;
    requires javax.mail;

    requires org.assertj.core;

    exports es.abel.dam;
    exports es.abel.dam.view;
    exports es.abel.dam.models;
    exports es.abel.dam.logica;

    opens es.abel.dam.view to javafx.fxml;
}