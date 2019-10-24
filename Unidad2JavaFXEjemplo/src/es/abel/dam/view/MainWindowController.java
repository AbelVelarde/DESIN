package es.abel.dam.view;

import es.abel.dam.filters.FiltradoDivision;
import es.abel.dam.filters.FiltradoEquipo;
import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import es.abel.dam.utils.DateUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    FiltradoEquipo filtradoEquipo;
    FiltradoDivision filtradoDivision;

    Stage stage;

    @FXML
    private TableView<Partido> tablaPartidos;
    @FXML
    private TextField tfBusquedaEquipo;
    @FXML
    private ComboBox<String> cbDivision;
    @FXML
    private MenuItem MenuGuardarEn;

    @FXML
    void altaPartido(ActionEvent event) {
        cargarDialogo("FormularioPartidos.fxml", "Alta Partido", 700, 500).abrirDialogo(true);

        filtradoEquipo.filtrar(tfBusquedaEquipo.getText());
        filtradoDivision.filtrar(cbDivision.getValue());
    }

    @FXML
    private void modificarPartido(ActionEvent event) {
        FormularioPartidosController controller = (FormularioPartidosController) cargarDialogo("FormularioPartidos.fxml", "Alta Partido", 700, 500);
        controller.setPartidoEditar(tablaPartidos.getSelectionModel().getSelectedItem());
        controller.abrirDialogo(true);

        filtradoEquipo.filtrar(tfBusquedaEquipo.getText());
        filtradoDivision.filtrar(cbDivision.getValue());
    }

    @FXML
    private void borrarPartido(ActionEvent event){
        Partido partidoBorrar = tablaPartidos.getSelectionModel().getSelectedItem();
        Logica.getINSTANCE().borrarPartido(partidoBorrar);

        filtradoEquipo.filtrar(tfBusquedaEquipo.getText());
        filtradoDivision.filtrar(cbDivision.getValue());
    }

    @FXML
    private void salir(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void filtroDivision(ActionEvent event) {
        String divisionFiltrar = cbDivision.getValue();
        tablaPartidos.setItems(filtradoDivision.filtrar(divisionFiltrar));
    }

    @FXML
    private void guardarListaEn(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        stage = (Stage) tfBusquedaEquipo.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        System.out.println(file.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Logica.getINSTANCE().cargarLista(new File("listaPartidos.txt"));

        filtradoEquipo = new FiltradoEquipo(Logica.getINSTANCE().getListaPartidos());
        filtradoDivision = new FiltradoDivision(Logica.getINSTANCE().getListaPartidos());

        //Nos suscribimos a cambios en la propiedad text del textfield
        tfBusquedaEquipo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tablaPartidos.setItems(filtradoEquipo.filtrar(newValue));
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


