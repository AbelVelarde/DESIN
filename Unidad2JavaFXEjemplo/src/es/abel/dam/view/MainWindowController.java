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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    FiltradoEquipo filtradoEquipo;
    FiltradoDivision filtradoDivision;

    @FXML
    private TableView<Partido> tablaPartidos;
    @FXML
    private TextField tfBusquedaEquipo;
    @FXML
    private ComboBox<String> cbDivision;

    @FXML
    void altaPartido(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("FormularioPartidos.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Formulario Partidos");
            stage.setScene(new Scene(root, 700, 500));
            stage.showAndWait();
            filtradoEquipo.filtrar(tfBusquedaEquipo.getText());
            filtradoDivision.filtrar(cbDivision.getValue());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void modificarPartido(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioPartidos.fxml"));
            Parent root = fxmlLoader.load();
            FormularioPartidosController controller = fxmlLoader.getController();
            controller.setPartidoEditar(tablaPartidos.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Formulario Partidos");
            stage.setScene(new Scene(root, 700, 500));
            stage.showAndWait();
            filtradoEquipo.filtrar(tfBusquedaEquipo.getText());
            filtradoDivision.filtrar(cbDivision.getValue());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void salir(ActionEvent event){
        System.exit(0);
    }

    @FXML
    private void filtroDivision(ActionEvent event){
        String divisionFiltrar = cbDivision.getValue();
        tablaPartidos.setItems(filtradoDivision.filtrar(divisionFiltrar));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filtradoEquipo = new FiltradoEquipo(Logica.getINSTANCE().getListaPartidos());
        filtradoDivision = new FiltradoDivision(Logica.getINSTANCE().getListaPartidos());

        //Nos suscribimos a cambios en la propiedad text del textfield
        tfBusquedaEquipo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                tablaPartidos.setItems(filtradoEquipo.filtrar(newValue));
            }
        });

        ObservableList<Partido> listaPartidos = Logica.getINSTANCE().getListaPartidos();

        listaPartidos.add(new Partido("Madrid", "Barcelona", Division.PRIMERA, new Resultado(12,32), new Date()));
        listaPartidos.add(new Partido("Sporting", "Oviedo", Division.SEGUNDA, new Resultado(1,3), new Date()));
        listaPartidos.add(new Partido("Llanes", "Urraca", Division.TERCERA, new Resultado(0,4), new Date()));

        tablaPartidos.setItems(listaPartidos);

        cbDivision.setValue("Todas");
    }
}


