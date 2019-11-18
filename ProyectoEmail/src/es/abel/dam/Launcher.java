package es.abel.dam;

import javafx.application.Application;
import javafx.css.Size;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/EmailMainWindow.fxml"));
        stage.setTitle("Email Controller");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

}
