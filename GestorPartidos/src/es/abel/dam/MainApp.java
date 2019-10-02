package es.abel.dam;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.PropertyPermission;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        Scene scene = new Scene();
        stage.setScene(scene);
        stage.show();
    }

    public void crearTable(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ObservableList<Partido> listaPartidos = FXCollections.observableArrayList();

        try {
            listaPartidos.add(new Partido("Santantder", "Oviedo", Division.SEGUNDA, new Resultado(13, 16), sdf.parse("13/10/2018")));
            listaPartidos.add(new Partido("Gijon", "Leon", Division.PRIMERA, new Resultado(21, 11), sdf.parse("27/11/2018")));
            listaPartidos.add(new Partido("Cadiz", "Madrid", Division.TERCERA, new Resultado(23, 17), sdf.parse("08/01/2019")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TableView tablaPartidos = new TableView(listaPartidos);

        TableColumn<String, Partido> columnaLocal = new TableColumn<>("Equipo Local");
        columnaLocal.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<String, Partido> columnaVisitante = new TableColumn<>("Equipo Visitante");
        columnaLocal.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    }

}
