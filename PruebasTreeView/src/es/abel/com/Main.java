package es.abel.com;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        TreeItem raiz = new TreeItem<String>("Libros");

        TreeItem cf = new TreeItem<String>("Ciencia Ficcion");
        TreeItem np = new TreeItem<String>("Novela Policiaca");
        TreeItem inf = new TreeItem<String>("Infantil");

        raiz.getChildren().addAll(cf, np, inf);

        TreeItem vct = new TreeItem<String>("Viaje al Centro de la Tierra");
        TreeItem sph = new TreeItem<String>("SuperHeroes");

        cf.getChildren().addAll(vct, sph);

        TreeItem sh = new TreeItem<String>("Sherlock Holmes");
        TreeItem hp = new TreeItem<String>("Hercules Poirot");
        TreeItem dc = new TreeItem<String>("Bruce Wayne");

        np.getChildren().addAll(sh, hp, dc);

        TreeItem pato = new TreeItem<String>("El Patito Feo");
        TreeItem cerdo = new TreeItem<String>("Los Tres Cerditos");

        inf.getChildren().addAll(pato, cerdo);

        TreeView<String> treeViewLibros = new TreeView<String>(raiz);

        VBox vbox = new VBox();
        vbox.getChildren().add(treeViewLibros);

        Scene scene = new Scene(vbox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }


}
