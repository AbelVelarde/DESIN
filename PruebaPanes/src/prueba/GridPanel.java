package prueba;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class GridPanel extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Label 1"),0,0,1,1);
        gridPane.add(new Label("Label 2"),3,1,3,1);
        gridPane.add(new Label("Label 3"),0,2,2,1);
        gridPane.add(new Label("Label 4"),1,0,1,1);
        gridPane.add(new Label("Label 5"),1,3,2,1);
        gridPane.add(new Label("Label 6"),1,2,1,1);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        ImageView iv = new ImageView(getClass().getResource("resources/ola.jpg").toExternalForm());
        gridPane.add(iv, 0,1,1,1);

        Scene scene = new Scene(gridPane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
