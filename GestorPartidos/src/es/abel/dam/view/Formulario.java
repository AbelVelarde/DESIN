package es.abel.dam.view;

import es.abel.dam.models.Division;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Formulario extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label labelLocal = new Label("Nombre local:");
        Label lbLocal = new Label();
        TextField tfNombreLocal = new TextField();
        tfNombreLocal.setPromptText("Introduzca nombre del equipo local");
        Button btnAceptarLocal = new Button("Aceptar");

        btnAceptarLocal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lbLocal.setText("Equipo Local: " + tfNombreLocal.getText());
            }
        });

        VBox vboxLocal = new VBox(10, labelLocal,  tfNombreLocal, btnAceptarLocal, lbLocal);

        Label labelVisitante = new Label("Nombre visitante:");
        Label lbVisitante = new Label();
        TextField tfNombreVisitante = new TextField();
        tfNombreVisitante.setPromptText("Introduzca nombre del equipo visitante");
        Button btnAceptarVisitante = new Button("Aceptar");

        btnAceptarVisitante.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lbVisitante.setText("Equipo Visitante: " + tfNombreVisitante.getText());
            }
        });

        VBox vboxVisitante = new VBox(10, labelVisitante, tfNombreVisitante, btnAceptarVisitante, lbVisitante);

        Label labelDivision = new Label("Division:");
        Label lbDivision = new Label();
        ObservableList<Division> listaDivisiones = FXCollections.observableArrayList();
        listaDivisiones.add(Division.PRIMERA);
        listaDivisiones.add(Division.SEGUNDA);
        listaDivisiones.add(Division.TERCERA);
        ComboBox cbDivision = new ComboBox(listaDivisiones);
        Button btnDivision = new Button("Aceptar");

        btnDivision.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Division div = (Division) cbDivision.getSelectionModel().getSelectedItem();
                lbDivision.setText(div.toString().toLowerCase() + " division");
            }
        });

        VBox vboxDivision = new VBox(10, labelDivision, cbDivision, btnDivision, lbDivision);

        Label labelResultado = new Label("Resultado del partido:");
        Label lbResultado = new Label();
        TextField tfResultado = new TextField();
        tfResultado.setPromptText("Introduzca el resultado del partido");
        Button btnAceptarResultado = new Button("Aceptar");

        btnAceptarResultado.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lbResultado.setText("Resultado: " + tfResultado.getText());
            }
        });

        VBox vboxResultado = new VBox(10, labelResultado, tfResultado, btnAceptarResultado, lbResultado);

        Label labelFecha = new Label("Fecha del partido:");
        Label lbFecha = new Label();
        DatePicker dp = new DatePicker();
        Button btnAceptarFecha = new Button("Aceptar");

        btnAceptarFecha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LocalDate ld = dp.getValue();
                lbFecha.setText("Fecha: " + ld.toString());
            }
        });

        VBox vboxFecha = new VBox(10, labelFecha, dp, btnAceptarFecha, lbFecha);

        VBox formulario = new VBox(10, vboxLocal, vboxVisitante, vboxDivision, vboxResultado, vboxFecha);
        TitledPane principal = new TitledPane("Formulario", formulario);

        Scene scene = new Scene(principal,900, 750);
        stage.setScene(scene);
        stage.show();
    }
}
