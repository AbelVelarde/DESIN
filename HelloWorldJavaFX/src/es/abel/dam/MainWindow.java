package es.abel.dam;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

    /*
    @Override
    public void start(Stage stage) throws Exception {
        Label label1 = new Label("Hello World");
        Label label2 = new Label("Hola Mundo");
        Label label3 = new Label("Hallo Welt");

        Button button = new Button("Say Hello");


        HBox principal = new HBox(); //Organiza n elementos de forma horizontal (vbox de manera vertical)

        VBox vbox = new VBox(10, label1, label2);

        principal.getChildren().addAll(vbox, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                label1.setText("Hola Bryan");
                vbox.getChildren().add(label3);
            }
        });

        Scene scene = new Scene(principal, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
     */

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Nombre:");
        Label label2 = new Label();
        Button button = new Button("Aceptar");
        TextField textField = new TextField();
        textField.setPromptText("Introduzca su nombre");

        VBox principal = new VBox(10, label, textField, button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                principal.getChildren().add(new Label("Hola, " + textField.getText()));
            }
        });

        Scene scene = new Scene(principal, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
