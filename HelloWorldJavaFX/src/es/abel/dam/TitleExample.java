package es.abel.dam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.invoke.VarHandle;

public class TitleExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label titulo = new Label("Contenido del titulo");
        Label subTitulo = new Label("Contenido del subtitulo");
        TitledPane panelSubTitulo = new TitledPane("SubTitulo", subTitulo);
        VBox vbox = new VBox(titulo, panelSubTitulo);
        TitledPane panelTitulo = new TitledPane("Titulo", vbox);

        Scene scene = new Scene(panelTitulo, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
