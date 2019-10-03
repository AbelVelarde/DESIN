package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainApp extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        TableView tablaPartidos = crearTabla();

        Button btnAñadirPartido = new Button("AñadirPartido");


        btnAñadirPartido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FormularioPartido formularioPartido = new FormularioPartido();
                formularioPartido.show();
            }
        });

        VBox ventanaPrincipal = new VBox(tablaPartidos, btnAñadirPartido);

        Scene scene = new Scene(ventanaPrincipal, 450, 300);
        stage.setTitle("Gestor de Partidos");
        stage.setScene(scene);
        stage.show();
    }

    public TableView crearTabla(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ObservableList<Partido> listaPartidos = Logica.getINSTANCE().getListaPartidos();

        try {
            listaPartidos.add(new Partido("Santander", "Oviedo", Division.SEGUNDA, new Resultado(13, 16), sdf.parse("13/10/2018")));
            listaPartidos.add(new Partido("Gijon", "Leon", Division.PRIMERA, new Resultado(21, 11), sdf.parse("27/11/2018")));
            listaPartidos.add(new Partido("Cadiz", "Madrid", Division.TERCERA, new Resultado(23, 17), sdf.parse("08/01/2019")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TableView tablaPartidos = new TableView(listaPartidos);

        TableColumn<String, Partido> columnaLocal = new TableColumn<>("Equipo Local");
        columnaLocal.setCellValueFactory(new PropertyValueFactory<>("local"));

        TableColumn<String, Partido> columnaVisitante = new TableColumn<>("Equipo Visitante");
        columnaVisitante.setCellValueFactory(new PropertyValueFactory<>("visitante"));

        TableColumn<String, Partido> columnaDivision = new TableColumn<>("Division");
        columnaDivision.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<String, Partido> columnaResultado = new TableColumn<>("Resultado");
        columnaResultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));

        TableColumn<String, Partido> columnaFecha = new TableColumn<>("Fecha");
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaFormateada"));

        tablaPartidos.getColumns().addAll(columnaLocal, columnaVisitante, columnaDivision, columnaResultado, columnaFecha);

        return tablaPartidos;
    }

}
