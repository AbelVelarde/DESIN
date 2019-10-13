package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainApp extends Application {

    //TODO: meter imagen
    //TODO: Refactorizar codigo
    //TODO: implementar filtrado

    TableView tablaPartidos;
    //ComboBox<String> comboFiltrado;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        cargarListaPartidos();
        crearTabla();

        Button btnAñadirPartido = new Button("Añadir Partido");
        Button btnEditarPartido = new Button("Editar Partido");
        Button btnBorrarPartido = new Button("Borrar Partido");

        btnAñadirPartido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                añadirPartido();
            }
        });

        btnEditarPartido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                editarPartido();
            }
        });

        btnBorrarPartido.setOnAction(new EventHandler<ActionEvent>() {
            //TODO: borrado multiple (Ampliacion)
            @Override
            public void handle(ActionEvent actionEvent) {
                borrarPartido();
            }
        });

        //HBox hboxFiltrado = new HBox(10, new Label("Escoja division para filtrar: "), comboFiltrado);
        HBox hboxBotones = new HBox(10, btnAñadirPartido, btnEditarPartido, btnBorrarPartido);

        AnchorPane contenedorPrincipal = new AnchorPane();

        contenedorPrincipal.getChildren().addAll( tablaPartidos, hboxBotones);

        AnchorPane.setTopAnchor(tablaPartidos, 20d);
        AnchorPane.setRightAnchor(tablaPartidos, 20d);
        AnchorPane.setLeftAnchor(tablaPartidos, 20d);
        AnchorPane.setBottomAnchor(tablaPartidos, 100d);

        AnchorPane.setBottomAnchor(hboxBotones, 20d);
        AnchorPane.setLeftAnchor(hboxBotones, 20d);

        Scene scene = new Scene(contenedorPrincipal, 600, 450);
        stage.setTitle("Gestor de Partidos");

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                guardarListaPartidos();
            }
        });
        stage.setScene(scene);
        stage.show();


        //TODO: guardar y cargar a disco
        //list<Partido> listaPartidos = new ArrayList<>(listaPersonasFX) <- guardar
        //ObservableList<Partido> listaPartidosFX = FXCollections.obsevableList(listaPartidos)
    }

    /**
     * Metodo que crea la tabla de partidos.
     */
    public void crearTabla() {
//        //TODO: borrar linea despues de implementar la lectura de fichero
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
      ObservableList<Partido> listaPartidos = Logica.getINSTANCE().getListaPartidos();
//
//        //TODO: Borrar despues de implementar la lectura de fichero;
//        try {
//            listaPartidos.add(new Partido("Santander", "Oviedo", Division.SEGUNDA, new Resultado(13, 16), sdf.parse("13/10/2018")));
//            listaPartidos.add(new Partido("Gijon", "Leon", Division.PRIMERA, new Resultado(21, 11), sdf.parse("27/11/2018")));
//            listaPartidos.add(new Partido("Cadiz", "Madrid", Division.TERCERA, new Resultado(23, 17), sdf.parse("08/01/2019")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        tablaPartidos = new TableView(listaPartidos);

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

        tablaPartidos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tablaPartidos.getColumns().addAll(columnaLocal, columnaVisitante, columnaDivision, columnaResultado, columnaFecha);
    }

/*
    private void crearComboFiltro(){
        comboFiltrado = new ComboBox();
        ObservableList<String> listaDivisiones = FXCollections.observableArrayList();

        listaDivisiones.add("Todas");
        listaDivisiones.add("Primera");
        listaDivisiones.add("Segunda");
        listaDivisiones.add("Tercera");
        comboFiltrado = new ComboBox<> (listaDivisiones);

        comboFiltrado.getSelectionModel().select(0);

        comboFiltrado.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                Logica.getINSTANCE().filtrarPorDivision(comboFiltrado.getSelectionModel().getSelectedItem());
            }
        });
    }
*/
    /**
     * Crea una nueva ventana de formulario para añadir un partdo.
     */
    private void añadirPartido() {
        FormularioPartido formularioPartido = new FormularioPartido();
        formularioPartido.show();
    }

    /**
     * Crea un nuevo formulario para editar un partido.
     */
    private void editarPartido() {
        Partido editarPartido = (Partido) tablaPartidos.getSelectionModel().getSelectedItem();
        int id = tablaPartidos.getSelectionModel().getSelectedIndex();
        FormularioPartido formularioPartido = new FormularioPartido(editarPartido, id);
        formularioPartido.show();
    }

    /**
     * Llama al metodo de logica que permite borrar un partido.
     */
    private void borrarPartido() {
        int idPartido = tablaPartidos.getSelectionModel().getSelectedIndex();
        if (idPartido >= 0) {
            Boolean confirm = Alerts.alertaBorradoConfim();
            if (confirm) {
                Logica.getINSTANCE().borrarPartido(idPartido);
            }
        } else {
            Alerts.alertaBorradoNoSelec();
        }
    }

    /**
     * Metodo que permite guardar a disco la lista de partidos al cerrar la applicacion.
     */
    private void guardarListaPartidos(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("ListaPartidos.txt"));

            ArrayList<Partido> listaOutput = new ArrayList<>(Logica.getINSTANCE().getListaPartidos());

            oos.writeObject(listaOutput);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cargarListaPartidos(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("ListaPartidos.txt"));

            ArrayList<Partido> listaInput = (ArrayList<Partido>) ois.readObject();

            Logica.getINSTANCE().setListaPartidos(listaInput);

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
