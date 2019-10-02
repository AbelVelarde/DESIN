package es.abel.dam;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Componentes3 extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        DatePicker dp = new DatePicker();

        Accordion acordeon = new Accordion();

        SplitPane sp = new SplitPane();

        TitledPane titled1 = new TitledPane("Titulo 1 ", new Label("Label uno"));
        TitledPane titled2 = new TitledPane("Titulo 2 ", new Label("Label dos"));
        TitledPane titled3 = new TitledPane("Titulo 3 ", new Label("Label tres"));

        acordeon.getPanes().add(titled1);
        acordeon.getPanes().add(titled2);
        acordeon.getPanes().add(titled3);

        sp.getItems().add(titled1);
        sp.getItems().add(titled2);
        sp.getItems().add(titled3);

        sp.setOrientation(Orientation.VERTICAL);

        Button btn = new Button("Aceptar");
        Label lb = new Label();
        VBox vbox = new VBox();

        ObservableList<String> lista = FXCollections.observableArrayList();
        lista.add("Juan");
        lista.add("Maria");
        lista.add("Antonio");
        ComboBox<String> combo = new ComboBox<String>(lista);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String c = combo.getSelectionModel().getSelectedItem();
                lb.setText(c);
            }
        });

        vbox.getChildren().addAll(combo, btn, lb);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
