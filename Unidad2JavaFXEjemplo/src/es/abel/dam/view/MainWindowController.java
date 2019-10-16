package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Division;
import es.abel.dam.models.Partido;
import es.abel.dam.models.Resultado;
import es.abel.dam.utils.DateUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private MenuItem MenuAlta;
    @FXML
    private TableColumn TCLocal;
    @FXML
    private TableColumn TCVisitante;
    @FXML
    private TableColumn TCDivision;
    @FXML
    private TableColumn TCResultado;
    @FXML
    private TableColumn TCFecha;

    @FXML
    private TableView<Partido> tablaPartidos;

    @FXML
    void altaPersona(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Partido> listaPartidos = Logica.getINSTANCE().getListaPartidos();

        listaPartidos.add(new Partido("Madrid", "Barcelona", Division.PRIMERA, new Resultado(12,32), new Date()));
        listaPartidos.add(new Partido("Sporting", "Oviedo", Division.SEGUNDA, new Resultado(1,3), new Date()));
        listaPartidos.add(new Partido("Llanes", "Urraca", Division.TERCERA, new Resultado(0,4), new Date()));

        tablaPartidos.setItems(listaPartidos);
    }
}


