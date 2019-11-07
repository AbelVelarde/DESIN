package es.abel.dam.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailLoginWindowController extends BaseController implements Initializable {

    @FXML
    private TextField tfCorreo;
    @FXML
    private PasswordField pfPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfCorreo.setText("sandierparapromociones@gmail.com");
        pfPassword.setText("abelvelarde97");


    }
}
