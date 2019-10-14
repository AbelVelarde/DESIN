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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;

public class MainApp extends Application {

    //TODO: implementar filtrado

    TableView tablaPartidos;
    ComboBox<String> comboFiltrado;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        cargarListaPartidos();
        crearTabla();
        crearComboFiltrado();

        Button btnAñadirPartido = new Button("Añadir Partido");
        Button btnEditarPartido = new Button("Editar Partido");
        Button btnBorrarPartido = new Button("Borrar Partido");

        ImageView imagenRugby = new ImageView(getClass().getResource("resources/AllBlacks.jpg").toExternalForm());
        imagenRugby.setPreserveRatio(true);
        imagenRugby.setFitHeight(130);

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

        HBox hboxFiltrado = new HBox(10, new Label("Seleccione Division para filtrar: "), comboFiltrado);
        HBox hboxBotones = new HBox(10, btnAñadirPartido, btnEditarPartido, btnBorrarPartido);

        AnchorPane contenedorPrincipal = new AnchorPane();

        contenedorPrincipal.getChildren().addAll(hboxFiltrado, tablaPartidos, imagenRugby ,hboxBotones);

        AnchorPane.setTopAnchor(hboxFiltrado, 20d);
        AnchorPane.setLeftAnchor(hboxFiltrado, 20d);

        AnchorPane.setTopAnchor(tablaPartidos, 60d);
        AnchorPane.setRightAnchor(tablaPartidos, 20d);
        AnchorPane.setLeftAnchor(tablaPartidos, 20d);
        AnchorPane.setBottomAnchor(tablaPartidos, 160d);

        AnchorPane.setBottomAnchor(hboxBotones, 20d);
        AnchorPane.setLeftAnchor(hboxBotones, 20d);

        AnchorPane.setRightAnchor(imagenRugby,20d);
        AnchorPane.setBottomAnchor(imagenRugby, 20d);

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
    }

    /**
     * Metodo que crea la tabla de partidos.
     */
    public void crearTabla() {
        ObservableList<Partido> listaPartidos = Logica.getINSTANCE().getListaPartidos();

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

    private void crearComboFiltrado(){
        ObservableList<String> listaDivisiones = FXCollections.observableArrayList();
        listaDivisiones.add("Todas");
        listaDivisiones.add("Primera");
        listaDivisiones.add("Segunda");
        listaDivisiones.add("Tercera");

        comboFiltrado = new ComboBox<>(listaDivisiones);
        comboFiltrado.getSelectionModel().select(0);

        ObservableList listaAuxiliar = FXCollections.observableArrayList(Logica.getINSTANCE().getListaPartidos());

        comboFiltrado.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList listaFiltrada = FXCollections.observableArrayList();
                if(comboFiltrado.getValue().equalsIgnoreCase("Todas")){
                    listaFiltrada.removeAll(listaFiltrada);
                    tablaPartidos.getItems().removeAll(tablaPartidos.getItems());
                    tablaPartidos = new TableView(Logica.getINSTANCE().getListaPartidos());
                }
                else {
                    listaFiltrada.removeAll(listaFiltrada);
                    for (Object partido : Logica.getINSTANCE().getListaPartidos()) {
                        if (((Partido) partido).getDivision().toString().equalsIgnoreCase(comboFiltrado.getValue())) {
                            listaFiltrada.add(partido);
                        }
                    }
                    tablaPartidos.getItems().removeAll(tablaPartidos.getItems());
                    tablaPartidos.getItems().addAll(listaFiltrada);
                }
            }
        });
    }

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

    /**
     * Metodo que permite cargar la lista de partido desde fichero.
     */
    private void cargarListaPartidos(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("ListaPartidos.txt"));

            ArrayList<Partido> listaInput = null;
            if(ois.available() == -1){
                listaInput = (ArrayList<Partido>) ois.readObject();
            }

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
