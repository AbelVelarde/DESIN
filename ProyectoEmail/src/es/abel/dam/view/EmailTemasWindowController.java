package es.abel.dam.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailTemasWindowController extends BaseController implements Initializable {

    @FXML
    ComboBox<String> cbTemas;

    @FXML
    VBox vboxMuestra;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTemas.getItems().addAll(Application.STYLESHEET_CASPIAN, Application.STYLESHEET_MODENA);
        cbTemas.getSelectionModel().select(Application.STYLESHEET_MODENA);

        cbTemas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                Application.setUserAgentStylesheet(newValue);
            }
        });
    }

    @FXML
    private void accept(){
        getStage().close();
    }

    @FXML
    private void cancel(){
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        getStage().close();
    }


}
