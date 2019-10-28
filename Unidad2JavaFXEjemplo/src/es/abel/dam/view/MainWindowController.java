package es.abel.dam.view;

import es.abel.dam.filters.Filtrado;
import es.abel.dam.logica.Logica;
import es.abel.dam.models.Partido;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    Filtrado filtrado;

    Stage stage;

    @FXML
    private TableView<Partido> tablaPartidos;
    @FXML
    private TextField tfBusquedaEquipo;
    @FXML
    private ComboBox<String> cbDivision;
    @FXML
    private Button btnGuardar;

    @FXML
    void altaPartido(ActionEvent event) {
        cargarDialogo("FormularioPartidos.fxml", "Alta Partido", 700, 500).abrirDialogo(true);

        actualizarTabla();
    }

    @FXML
    private void modificarPartido(ActionEvent event) {
        Partido partidoEditar = tablaPartidos.getSelectionModel().getSelectedItem();

        if(partidoEditar != null){
            FormularioPartidosController controller = (FormularioPartidosController) cargarDialogo("FormularioPartidos.fxml", "Alta Partido", 700, 500);
            controller.setPartidoEditar(partidoEditar);
            controller.abrirDialogo(true);

            actualizarTabla();
        }
        else{
            Alerts.alertaNoSelec();
        }

    }

    @FXML
    private void borrarPartido(ActionEvent event){
        Partido partidoBorrar = tablaPartidos.getSelectionModel().getSelectedItem();

        if(partidoBorrar != null){
            boolean confirm = Alerts.alertaBorradoConfim();
            if(confirm){
                Logica.getINSTANCE().borrarPartido(partidoBorrar);
                actualizarTabla();
            }
        }
        else{
            Alerts.alertaNoSelec();
        }


    }

    @FXML
    private void salir(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void filtroDivision(ActionEvent event) {
        String divisionFiltrar = cbDivision.getValue();
        tablaPartidos.setItems(filtrado.filtrar(divisionFiltrar, tfBusquedaEquipo.getText()));
    }

    @FXML
    private void guardarListaEn(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        stage = (Stage) tfBusquedaEquipo.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        Logica.getINSTANCE().guardarLista(file);

        tablaPartidos.setItems(Logica.getINSTANCE().getListaPartidos());
    }

    @FXML
    private void cargarListaDe(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        stage = (Stage) tfBusquedaEquipo.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        Logica.getINSTANCE().cargarLista(file);

        tablaPartidos.setItems(Logica.getINSTANCE().getListaPartidos());
        filtrado = new Filtrado(Logica.getINSTANCE().getListaPartidos());
        actualizarTabla();
    }

    private void actualizarTabla(){
        filtrado.filtrar(cbDivision.getValue(), tfBusquedaEquipo.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Logica.getINSTANCE().cargarLista(new File("listaPartidos.txt"));

        filtrado = new Filtrado(Logica.getINSTANCE().getListaPartidos());

        //Nos suscribimos a cambios en la propiedad text del textfield
        tfBusquedaEquipo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tablaPartidos.setItems(filtrado.filtrar(cbDivision.getValue(),newValue));
            }
        });

//        ObservableList<Partido> listaPartidos = Logica.getINSTANCE().getListaPartidos();
//
//        listaPartidos.add(new Partido("Madrid", "Barcelona", Division.PRIMERA, new Resultado(12, 32), new Date()));
//        listaPartidos.add(new Partido("Sporting", "Oviedo", Division.SEGUNDA, new Resultado(1, 3), new Date()));
//        listaPartidos.add(new Partido("Llanes", "Urraca", Division.TERCERA, new Resultado(0, 4), new Date()));

        tablaPartidos.setItems(Logica.getINSTANCE().getListaPartidos());

        cbDivision.setValue("Todas");
    }
}


