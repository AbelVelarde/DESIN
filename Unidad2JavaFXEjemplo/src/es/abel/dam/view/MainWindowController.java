package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Partido;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private MenuItem menuAlta;

    @FXML
    private TableView<Partido> tablaPartidos;

    @FXML
    void altaPersona(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaPartidos.setItems(Logica.getINSTANCE().getListaPartidos());
    }
}


