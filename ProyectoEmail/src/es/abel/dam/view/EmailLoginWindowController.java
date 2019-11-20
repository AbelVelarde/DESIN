package es.abel.dam.view;

import es.abel.dam.logica.Logica;
import es.abel.dam.models.MailAccount;
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

    private MailAccount mailAccount;

    public MailAccount getMailAccount(){
        return mailAccount;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfCorreo.setText("sandierparapromociones@gmail.com");
        pfPassword.setText("abelvelarde97");
    }

    @FXML
    public void añadirMail(){
        mailAccount = new MailAccount(tfCorreo.getText(), pfPassword.getText());
        Logica.getInstance().setAccount(mailAccount);
        getStage().close();
    }

    @FXML
    private void cancelar(){
        getStage().close();
    }

}
