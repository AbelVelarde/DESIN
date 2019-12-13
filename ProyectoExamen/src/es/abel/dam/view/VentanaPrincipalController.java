package es.abel.dam.view;

import es.abel.dam.filters.Filter;
import es.abel.dam.logica.Logica;
import es.abel.dam.models.Via;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaPrincipalController extends BaseController implements Initializable {

    @FXML
    private TableView<Via> tablaVias;
    @FXML
    private Button btnAlta;
    @FXML
    private ComboBox<String> cbFiltroUno;
    @FXML
    private ComboBox<String> cbFiltroDos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaVias.setItems(Logica.getInstance().getListaVias());

        cbFiltroUno.setItems(Logica.getInstance().getListaDificultades());
        cbFiltroDos.setItems(Logica.getInstance().getListaDificultades());
        cbFiltroUno.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                cbFiltroDos.setItems(Logica.getInstance().getListaDificultadesDos(newValue));
            }
        });
    }

    @FXML
    private void abrirAlta(){
        BaseController controller = cargarVentana("Alta.fxml", "Alta Vias");
        controller.abrirVentana(true);

        filtrar();
    }

    @FXML
    private void filtrar(){
        Filter filter = new Filter(Logica.getInstance().getListaVias());

        String difMenor = cbFiltroUno.getSelectionModel().getSelectedItem();
        String difMayor = cbFiltroDos.getSelectionModel().getSelectedItem();

        if(difMenor == null){
            difMenor = "6a";
        }
        if(difMayor == null){
            difMayor = "7c";
        }

        ArrayList<String> dificultades = Logica.getInstance().getDificultades(difMenor, difMayor);

        tablaVias.setItems(filter.filtrar(dificultades));

    }
}
