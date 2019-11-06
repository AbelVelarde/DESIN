package es.abel.dam.view;

import es.abel.dam.models.Mail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.*;

public class EmailMainWindowController implements Initializable {

    ObservableList<Message> lista;
    ObservableList<Mail> listaMails;

    @FXML
    private TableView<Mail> tablaMails;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarMail();
    }

    private void cargarMail(){
        try{
            Properties prop = new Properties();

            Session emailSesion = Session.getDefaultInstance(prop, null);

            Store store = emailSesion.getStore("imaps");

            store.connect("smtp.gmail.com", "sandierparapromociones@gmail.com", "abelvelarde97");

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();

            listaMails = FXCollections.observableArrayList();
            for (Message mensaje: messages) {
                listaMails.add(new Mail(mensaje));
            }

            tablaMails.setItems(listaMails);

            emailFolder.close(true);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
