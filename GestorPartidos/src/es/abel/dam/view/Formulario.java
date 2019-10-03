package es.abel.dam.view;

import es.abel.dam.models.Division;
import es.abel.dam.models.Resultado;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Formulario extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label labelLocal = new Label("Nombre local:");
        TextField tfNombreLocal = new TextField();
        tfNombreLocal.setPromptText("Introduzca nombre del equipo local");
        VBox vboxLocal = new VBox(10, labelLocal,  tfNombreLocal);

        Label labelVisitante = new Label("Nombre visitante:");
        TextField tfNombreVisitante = new TextField();
        tfNombreVisitante.setPromptText("Introduzca nombre del equipo visitante");
        VBox vboxVisitante = new VBox(10, labelVisitante, tfNombreVisitante);

        Label labelDivision = new Label("Division:");
        ObservableList<Division> listaDivisiones = FXCollections.observableArrayList();
        listaDivisiones.add(Division.PRIMERA);
        listaDivisiones.add(Division.SEGUNDA);
        listaDivisiones.add(Division.TERCERA);
        ComboBox cbDivision = new ComboBox(listaDivisiones);
        VBox vboxDivision = new VBox(10, labelDivision, cbDivision);

        Label labelResultado = new Label("Resultado del partido:");
        TextField tfResultadoLocal = new TextField();
        TextField tfResultadoVisitante = new TextField();
        tfResultadoLocal.setPromptText("Equipo local");
        tfResultadoVisitante.setPromptText("Equipo visitante");
        Label lbguion = new Label(" - ");
        HBox hboxResultado = new HBox(10, tfResultadoLocal, lbguion, tfResultadoVisitante);
        VBox vboxResultado = new VBox(10, labelResultado, hboxResultado);

        Label labelFecha = new Label("Fecha del partido:");
        DatePicker dp = new DatePicker();
        VBox vboxFecha = new VBox(10, labelFecha, dp);

        Button btnAceptar = new Button("Aceptar");

        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String local = tfNombreLocal.getText();
                String visitante = tfNombreVisitante.getText();
                Division division = (Division) cbDivision.getSelectionModel().getSelectedItem();
                Resultado resultado = new Resultado(Integer.parseInt(tfResultadoLocal.getText()), Integer.parseInt(tfResultadoVisitante.getText()));


            }
        });

        VBox formulario = new VBox(10, vboxLocal, vboxVisitante, vboxDivision, vboxResultado, vboxFecha, btnAceptar);
        TitledPane principal = new TitledPane("Formulario", formulario);

        Scene scene = new Scene(principal,900, 750);
        stage.setScene(scene);
        stage.show();
    }
}
