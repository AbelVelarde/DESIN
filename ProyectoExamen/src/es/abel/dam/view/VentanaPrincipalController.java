package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Via;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaPrincipalController extends BaseController implements Initializable {

    @FXML
    private TableView<Via> tablaVias;
    @FXML
    private Button btnAlta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaVias.setItems(Logica.getInstance().getListaVias());
    }

    @FXML
    private void abrirAlta(){
        BaseController controller = cargarVentana("Alta.fxml", "Alta Vias");
        controller.abrirVentana(true);
    }
}
