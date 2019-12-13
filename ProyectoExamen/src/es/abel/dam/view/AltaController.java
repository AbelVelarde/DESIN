package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Via;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AltaController extends BaseController implements Initializable {

    @FXML
    private ComboBox<String> cbDificultad;
    @FXML
    private TextField tfNombre;
    @FXML
    private Button btnAceptar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbDificultad.setItems(Logica.getInstance().getListaDificultades());
    }

    @FXML
    private void aceptar(){
        String nombre = tfNombre.getText();
        String dificultad = cbDificultad.getSelectionModel().getSelectedItem();

        Via via = new Via(nombre, dificultad);

        Logica.getInstance().añadirVia(via);

        getStage().close();
    }


}
