package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaExamenController extends BaseController implements Initializable {

    @FXML
    private ComboBox<Mail> cbExamen;
    @FXML
    private Button btnOk;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbExamen.setItems(Logica.getInstance().getExamenInboxMails());
    }

    @FXML
    private void mostrarAsunto(){
        Mail mail = cbExamen.getSelectionModel().getSelectedItem();

        Alert asunto = new Alert(Alert.AlertType.INFORMATION);
        asunto.setTitle("Asunto");
        asunto.setHeaderText("Asunto del Mensaje: ");
        asunto.setContentText(mail.getAsunto());
        asunto.showAndWait();
    }
}
