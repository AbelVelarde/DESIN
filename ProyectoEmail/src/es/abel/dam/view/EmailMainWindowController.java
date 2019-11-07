package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.Mail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.*;

public class EmailMainWindowController extends BaseController implements Initializable {

    ObservableList<Mail> listaMails;

    @FXML
    private TableView<Mail> tablaMails;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarMail();
    }

    @FXML
    private void cargarLogin(){
        BaseController baseController = cargarVentana("EmailLoginWindow.fxml", "Login");
        baseController.abrirVentana(true);
    }

    private void cargarMail(){
        listaMails = Logica.getInstance().getListaMails();
        tablaMails.setItems(listaMails);
    }
}
