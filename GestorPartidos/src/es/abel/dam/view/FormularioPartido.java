package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FormularioPartido extends Stage {

    public FormularioPartido() {
        GridPane gridFormulario = new GridPane();

        gridFormulario.setHgap(10d);
        gridFormulario.setVgap(10d);

        Label labelLocal = new Label("Nombre local:");
        TextField textFieldLocal = new TextField();
        textFieldLocal.setPromptText("Introduzca nombre del equipo local");

        gridFormulario.add(labelLocal, 0,0, 1, 1);
        gridFormulario.add(textFieldLocal, 1,0, 1, 1);


        Label labelVisitante = new Label("Nombre visitante:");
        TextField textFieldVisitante = new TextField();
        textFieldVisitante.setPromptText("Introduzca nombre del equipo visitante");

        gridFormulario.add(labelVisitante, 0,1, 1, 1);
        gridFormulario.add(textFieldVisitante, 1,1, 1, 1);


        Label labelDivision = new Label("Division:");
        ObservableList<Division> listaDivisiones = FXCollections.observableArrayList();
        listaDivisiones.add(Division.PRIMERA);
        listaDivisiones.add(Division.SEGUNDA);
        listaDivisiones.add(Division.TERCERA);
        ComboBox<Division> comboDivision = new ComboBox(listaDivisiones);
        comboDivision.setPromptText("Elija division");

        gridFormulario.add(labelDivision, 0, 2, 1, 1);
        gridFormulario.add(comboDivision, 1, 2, 1, 1);


        Label labelResultado = new Label("Resultado del partido:");
        TextField tfResultadoLocal = new TextField();
        TextField tfResultadoVisitante = new TextField();
        tfResultadoLocal.setPromptText("Equipo local");
        tfResultadoVisitante.setPromptText("Equipo visitante");
        Label lbguion = new Label(" - ");
        HBox hboxResultado = new HBox(10, tfResultadoLocal, lbguion, tfResultadoVisitante);

        gridFormulario.add(labelResultado, 0, 3, 1, 1);
        gridFormulario.add(hboxResultado, 1, 3, 1, 1);


        Label labelFecha = new Label("Fecha del partido:");
        DatePicker fechaPartido = new DatePicker(LocalDate.now());

        gridFormulario.add(labelFecha, 0, 4, 1, 1);
        gridFormulario.add(fechaPartido, 1, 4, 1, 1);

        Button btnAceptar = new Button("Aceptar");

        gridFormulario.add(btnAceptar, 0, 5, 1, 1);

        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String local = textFieldLocal.getText();
                String visitante = textFieldVisitante.getText();
                Division division = comboDivision.getSelectionModel().getSelectedItem();
                Resultado resultado = new Resultado(Integer.parseInt(tfResultadoLocal.getText()), Integer.parseInt(tfResultadoVisitante.getText()));
                Date fecha = new Date();
                try {
                    fecha = sdf.parse(fechaPartido.getValue().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Partido partido = new Partido(local, visitante, division, resultado, fecha);
                Logica.getINSTANCE().añadirPartido(partido);

                close();
            }
        });

        Scene scene = new Scene(gridFormulario, 650, 400);
        setTitle("Alta Partido");
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
    }

}
